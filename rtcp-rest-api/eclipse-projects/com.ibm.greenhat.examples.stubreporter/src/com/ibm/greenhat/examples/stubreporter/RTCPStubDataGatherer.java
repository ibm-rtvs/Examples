/**
 * Copyright 2014 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibm.greenhat.examples.stubreporter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibm.greenhat.examples.stubreporter.model.Component;
import com.ibm.greenhat.examples.stubreporter.model.Domain;
import com.ibm.greenhat.examples.stubreporter.model.Environment;
import com.ibm.greenhat.examples.stubreporter.model.Operation;
import com.ibm.greenhat.examples.stubreporter.model.Server;
import com.ibm.greenhat.examples.stubreporter.model.StubDefinition;
import com.ibm.greenhat.examples.stubreporter.model.StubInstance;

/**
 * Gathers data from RTCP and creates a model and then outputs the XML
 */
public class RTCPStubDataGatherer {

    private final String baseURL;
    private final Server server;
    private final DocumentBuilder dBuilder;

    public RTCPStubDataGatherer(final Server server, final DocumentBuilder dBuilder) throws Exception {
        String urlWithTrailingSlash = server.getUrl();
        if (!urlWithTrailingSlash.endsWith("/")) {
            urlWithTrailingSlash += "/";
        }
        baseURL = urlWithTrailingSlash;
        this.server = server;
        this.dBuilder = dBuilder;
    }

    /**
     * Loop through all the domains and environments and get stub information from RTCP
     */
    public void populate() throws Exception {
        for (final Domain domain : server.getDomains()) {
            for (final Environment env : domain.getEnvironments()) {
                addStubDefinitions(domain, env);
            }
        }
    }

    /**
     * Query RTCP to get StubDefinition information for the supplied domain and environment
     */
    private void addStubDefinitions(final Domain domain, final Environment env) throws IOException,
            ParserConfigurationException, SAXException {
        final String urlPart = MessageFormat.format("rest/stubs/?domain={0}&env={1}", domain.getName(), env.getName());
        final URL url = new URL(baseURL + urlPart);
        final Document stubDefs = getXML(url);
        final NodeList stubs = stubDefs.getElementsByTagName("stub");
        for (int i = 0; i < stubs.getLength(); i++) {
            final Node node = stubs.item(i);
            if (node instanceof Element) {
                final String name = ((Element) node).getAttribute("name");
                final String component = ((Element) node).getAttribute("component");
                final String operation = ((Element) node).getAttribute("operation");
                final String version = ((Element) node).getAttribute("version");
                final String href = ((Element) node).getAttribute("href");

                final StubDefinition stubDef = new StubDefinition(component, operation, name, version);
                addStubInstances(stubDef, href);

                final Component comp = env.addComponent(new Component(component));
                if (!"".equals(operation)) {
                    final Operation op = comp.addOperation(new Operation(operation));
                    op.addStub(stubDef);
                } else {
                    comp.addStub(stubDef);
                }
            }
        }
    }

    /**
     * Get locate the instance URL needed to find Stub instance information
     */
    private void addStubInstances(final StubDefinition stubDefinition, final String href) throws IOException,
            ParserConfigurationException, SAXException {
        final String urlPart = MessageFormat.format("rest/stubs/{0}", href);
        final URL url = new URL(baseURL + urlPart);
        final Document stubDef = getXML(url);
        final NodeList instances = stubDef.getElementsByTagName("instances");
        if (instances.getLength() == 1) {
            final Node node = instances.item(0);
            if (node instanceof Element) {
                final String instancesHref = ((Element) node).getAttribute("href");
                final String instancesUrl = urlPart + instancesHref;
                addInstanceData(instancesUrl, stubDefinition);
            }
        }

    }

    /**
     * Get stub instance data, i.e. the status, so it can be added to the definition
     */
    private void addInstanceData(final String instancesUrl, final StubDefinition stubDefinition) throws IOException,
            ParserConfigurationException, SAXException {
        final URL url = new URL(baseURL + instancesUrl);
        final Document instanceDoc = getXML(url);
        final NodeList instancesContainerNode = instanceDoc.getElementsByTagName("instances");
        if (instancesContainerNode.getLength() == 1) {
            final NodeList childNodes = instancesContainerNode.item(0).getChildNodes();
            for (int k = 0; k < childNodes.getLength(); k++) {
                final Node node = childNodes.item(k);
                if (node instanceof Element) {
                    final String status = ((Element) node).getAttribute("status");
                    final StubInstance instance = new StubInstance(status);
                    stubDefinition.addInstance(instance);
                }
            }
        }
    }

    /**
     * Simple connection to the server to get XML information back
     */
    private Document getXML(final URL url) throws IOException, ParserConfigurationException, SAXException {
        final URLConnection con = url.openConnection();
        try {
            final Document doc = dBuilder.parse(con.getInputStream());
            return doc;
        } catch (final IOException e) {
            final int responseCode = ((HttpURLConnection) con).getResponseCode();
            if (responseCode == 412 || responseCode == 404) {
                return dBuilder.newDocument();
            }
            throw e;
        } finally {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
        }
    }
}