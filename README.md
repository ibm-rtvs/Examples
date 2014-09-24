# RTCP & RIT examples

IBM® Rational® Test Control Panel (RTCP) is a server-based web application providing
a central repository for virtualized services. RTCP allows you to use virtual services published 
with IBM Rational Integration Tester (RIT) to create, share, and deploy 
virtualized test environments in IBM Rational Test Virtualization Server (RTVS).

The following diagram shows how RTCP might be deployed in a RTVS Environment.


![RTCP](http://www.ibm.com/support/knowledgecenter/api/content/SSBLQQ_8.6.0/com.ibm.rational.rtcp.sysadmin.doc/images/RTCP_SYSADMIN_RTCP_RTVS-Environment.gif "RTCP")


This project contains ready-to-use examples that showcase features of RTCP and RIT.

***
## Download instructions

- Go to GitHub project [https://github.com/ibm-rtvs/Examples](https://github.com/ibm-rtvs/Examples) and select *Download ZIP*
- Unzip on a computer which has the following prerequisite software installed
	- Rational Integration Tester
	- Rational Integration Platform Pack
	- Rational Integration Tester Agent
	- Rational Test Control Panel 


All examples assume that the prerequisite software was installed with the default options. Most notably
- Software is at Version 6.0.1, or above
- All software is installed on the same computer
- Rational Test Control Panel port is 7189
- HTTP proxy port is 3128
  

*** 
## Examples

*echo*

This example takes advantage of the **echo** example web service that is built into
RTCP and includes: 

- A RIT project which contains a test to exercise the *echo* operation of the web service.

*calculator* 

This example takes advantage of the **calculator** example web service that is built into
RTCP and includes:
 
- A Java calculator application which uses the *calculator* web service to perform mathematical calculations.
Though the application itself remains unchanged, it can be launched in one of two modes.
	- Normal mode where it connects directly to the web service
	- Test mode, where traffic is routed through through a RIT proxy. 
	This facilitates both tracing at the operational level and routing operations to the 
	virtual calculators (stubs) provided in the RIT project. 

- A RIT project which contains
	- A test suite which exercises basic calculator operations of the web service.
    - Monitor definitions for all of the calculator operations
	- A stub which simulates all of the operations of the calculator web service
    - A partial stub which lets most operations [pass-through](http://www-01.ibm.com/support/knowledgecenter/SSBLQQ_8.6.0/com.ibm.rational.rtvs.ref.doc/topics/c_rtvsref_sift_passthrough.html?lang=en "sift-and-pass-through") to the actual web service
      but intercepts a specific operation to return an unexpected value

