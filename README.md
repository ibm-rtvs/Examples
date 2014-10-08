# RTCP & RIT examples #

IBM® Rational® Test Control Panel (RTCP) is a server-based web application providing
a central repository for virtualized services. RTCP allows you to use virtual services published 
with IBM Rational Integration Tester (RIT) to create, share, and deploy 
virtualized test environments in IBM Rational Test Virtualization Server (RTVS).

The following diagram shows how RTCP might be deployed in a RTVS Environment.


![RTCP](http://www.ibm.com/support/knowledgecenter/api/content/SSBLQQ_8.6.0/com.ibm.rational.rtcp.sysadmin.doc/images/RTCP_SYSADMIN_RTCP_RTVS-Environment.gif "RTCP")


This project contains ready-to-use examples that showcase features of RTCP and RIT.

* [Download instructions](#download-instructions)
* [Examples](#examples)
 * [echo](#echo)
 * [calculator](#calculator)
 * [rtcp-rest-api](#rtcp-rest-api)
* [License](#license)


***
## Download instructions

- Go to GitHub project [https://github.com/ibm-rtvs/Examples](https://github.com/ibm-rtvs/Examples) and select *Download ZIP*
- Unzip on a computer which has the following prerequisite software installed
	- Rational Integration Tester
	- Rational Integration Tester Platform Pack
	- Rational Integration Tester Agent
	- Rational Test Control Panel 


All examples assume that the prerequisite software was installed with the default options. Most notably
- Software is at Version 6.0.1, or above
- All software is installed on the same computer
- Rational Test Control Panel port is 7189
- HTTP proxy port is 3128
  

*** 

## Examples ##

### [*echo*](echo/README.md)

This example takes advantage of the **echo** example web service that is built into
RTCP and includes: 

- A RIT project which contains a test to exercise the *echo* operation of the web service.

### [*calculator*](calculator/README.md)

This example takes advantage of the **calculator** example web service that is built into
RTCP and includes:
 
- A Java calculator application which uses the *calculator* web service to perform mathematical calculations. Though the application itself remains unchanged, it can be launched in one of two modes.
	- Normal mode where it connects directly to the web service.
	- Test mode, where traffic is routed through through a RIT proxy. 
	This facilitates both tracing at the operational level and routing operations to the 
	virtual calculators (stubs) provided in the RIT project. 

- A RIT project which contains
    - A data-driven test, fed by rows in a CSV file.
    
    Operation,Value1,Value2,Result
    Add,1,1,2
    Divide,12,4,3
    Modulus,12,5,2
    Multiply,3,4,12
    Subtract,7,4,3
    
    - A series of basic test calculator tests, one per calculator operation.
	- A test suite which exercises basic calculator operations of the web service.
	- A stub which simulates all of the operations of the calculator web service.
    - A partial stub which lets most operations [pass-through](http://www-01.ibm.com/support/knowledgecenter/SSBLQQ_8.6.0/com.ibm.rational.rtvs.ref.doc/topics/c_rtvsref_sift_passthrough.html?lang=en "sift-and-pass-through") to the actual web service
      but intercepts a specific operation to return an unexpected value
	  
### [*rtcp-rest-api*](rtcp-rest-api/README.md)

Examples that take advantage of the RTCP REST API that is part of the Rational Test Control Panel. 

#### stub reporter

This produces and a simple report in text form containing information about the stubs available and running within the Rational Test Control Panel.

    java -jar stub-reporter.jar
        Usage: RTCPUrl [Domain name,Environment name];*
        e.g.: http://localhost:7819/RTCP Domain Name1,Env1;Domain2,Env2

## License ##
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this except in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

