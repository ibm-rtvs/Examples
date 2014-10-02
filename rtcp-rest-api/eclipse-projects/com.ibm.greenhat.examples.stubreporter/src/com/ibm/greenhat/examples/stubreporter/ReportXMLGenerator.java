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

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

import com.ibm.greenhat.examples.stubreporter.model.Component;
import com.ibm.greenhat.examples.stubreporter.model.Domain;
import com.ibm.greenhat.examples.stubreporter.model.Environment;
import com.ibm.greenhat.examples.stubreporter.model.Operation;
import com.ibm.greenhat.examples.stubreporter.model.Server;
import com.ibm.greenhat.examples.stubreporter.model.StubDefinition;
import com.ibm.greenhat.examples.stubreporter.model.StubInstance;

/**
 * Turn the Server model into an XML document
 */
public class ReportXMLGenerator {

    private final DocumentBuilder dBuilder;

    public ReportXMLGenerator(final DocumentBuilder dBuilder) {
        this.dBuilder = dBuilder;
    }

    public String getReportXMLAsString(final Server server) throws Exception {
        final Document doc = dBuilder.newDocument();
        ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"stub-reporter.xsl\"");

        final Element serverElement = doc.createElement("server");

        serverElement.setAttribute("url", server.getUrl());
        doc.appendChild(serverElement);
        doc.insertBefore(pi, serverElement);

        final Element domains = doc.createElement("domains");
        serverElement.appendChild(domains);
        final List<Domain> domainList = server.getDomains();
        for (final Domain domain : domainList) {
            final Element d = doc.createElement("domain");
            d.setAttribute("name", domain.getName());
            domains.appendChild(d);
            appendEnvs(doc, d, domain);
        }

        return prettyPrint(doc);
    }

    private void appendEnvs(final Document doc, final Element domainElement, final Domain domain) {
        final Element environments = doc.createElement("environments");
        domainElement.appendChild(environments);
        for (final Environment environment : domain.getEnvironments()) {
            final Element e = doc.createElement("environment");
            e.setAttribute("name", environment.getName());
            environments.appendChild(e);
            appendComponents(doc, e, environment);
        }
    }

    private void appendComponents(final Document doc, final Element environmentElement, final Environment environment) {
        final Element compElement = doc.createElement("components");
        environmentElement.appendChild(compElement);
        for (final Component comp : environment.getComponents()) {
            final Element c = doc.createElement("component");
            c.setAttribute("name", comp.getName());
            compElement.appendChild(c);
            appendStubs(doc, c, comp.getStubs());
            appendOperations(doc, c, comp);
        }
    }

    private void appendOperations(final Document doc, final Element parentElement, final Component component) {
        final Element opElement = doc.createElement("operations");
        parentElement.appendChild(opElement);
        for (final Operation op : component.getOperations()) {
            final Element o = doc.createElement("operation");
            o.setAttribute("name", op.getName());
            opElement.appendChild(o);
            appendStubs(doc, o, op.getStubs());
        }
    }

    private void appendStubs(final Document doc, final Element parentElement, final List<StubDefinition> stubDefs) {
        final Element stubs = doc.createElement("stubs");
        parentElement.appendChild(stubs);
        for (final StubDefinition stub : stubDefs) {
            final Element s = doc.createElement("stub");
            s.setAttribute("name", stub.getName());
            s.setAttribute("component", stub.getComponent());
            s.setAttribute("operation", stub.getOperation());
            s.setAttribute("version", stub.getVersion());
            stubs.appendChild(s);
            appendInstances(doc, s, stub);
        }
    }

    private void appendInstances(final Document doc, final Element stubElement, final StubDefinition stub) {
        final Element instances = doc.createElement("instances");
        stubElement.appendChild(instances);
        for (final StubInstance instance : stub.getInstances()) {
            final Element s = doc.createElement("instance");
            s.setAttribute("status", instance.getStatus());
            instances.appendChild(s);
        }
    }

    /**
     * Use a transformer to pretty print the XML
     */
    public static String prettyPrint(final Document doc) throws TransformerException {
        final TransformerFactory tf = TransformerFactory.newInstance();
        final Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        final StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.toString();
    }
}
