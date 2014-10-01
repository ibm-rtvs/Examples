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
package com.ibm.greenhat.rtcp.stub.reporter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.ibm.greenhat.rtcp.stub.reporter.model.Domain;
import com.ibm.greenhat.rtcp.stub.reporter.model.Environment;
import com.ibm.greenhat.rtcp.stub.reporter.model.Server;

/**
 * Main class, deals with processing command line input and running the application
 */
public class Main {

    /**
     * Main entry point when run on the command line
     */
    public static void main(final String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: RTCPUrl [Domain name,Environment name];*");
            System.out.println(" e.g.: http://localhost:7819/RTCP Domain Name1,Env1;Domain2,Env2");
            System.exit(1);
        }

        new Main().run(args);
    }

    private final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private final DocumentBuilder dBuilder;

    public Main() throws ParserConfigurationException {
        dBuilder = dbFactory.newDocumentBuilder();
    }

    /**
     * Build a list of domains and environments, then gather information about them from RTCP
     */
    public void run(final String[] args) throws Exception {
        // Construct the representation of the Server
        final Server server = new Server(args[0]);
        try {
            // Build the domain list from arguments
            buildDomainAndEnvironmentList(args, server);
        } catch (final ArgParseException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }

        // Gather stub information from RTCP
        final RTCPStubDataGatherer gather = new RTCPStubDataGatherer(server, dBuilder);
        gather.populate();

        // Create report XML and output to console
        final ReportXMLGenerator generator = new ReportXMLGenerator(dBuilder);
        final String report = generator.getReportXMLAsString(server);
        System.out.println(report);
        System.exit(0);
    }

    /**
     * Parse the list of command line arguments to extract the domain and environment pairs
     */
    private Collection<Domain> buildDomainAndEnvironmentList(final String[] args, final Server server)
            throws ArgParseException {
        // Build a full string of arguments, removing the URL
        final StringBuilder builder = new StringBuilder();
        for (int k = 1; k < args.length; k++) {
            builder.append(args[k]);
        }

        // Split the list on ";" to separate each pair
        final String argList = builder.toString();
        final String[] split = argList.split(";");
        if (split.length == 0) {
            throw new ArgParseException("Must specify at least 1 domain,env pair in the argument list");
        }

        final List<Domain> domains = new LinkedList<>();
        for (int k = 0; k < split.length; k++) {
            final String[] domenvpair = split[k].split(",");
            // Check we have a pair.
            if (domenvpair.length != 2) {
                throw new ArgParseException("List of domains and environments must contain pairs:\n" + argList);
            }

            // Process the list and ensure environments are added to an existing domain to prevent duplicates
            final String domainName = domenvpair[0];
            final String envName = domenvpair[1];

            final Domain domain = server.addDomain(new Domain(domainName));
            domain.addEnvironment(new Environment(envName));
        }

        return domains;
    }
}
