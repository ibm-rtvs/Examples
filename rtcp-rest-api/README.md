
# Example : rtcp-rest-api 


A collection of example applications which takes advantage of the RTCP REST API

## stub-reporter

An example applications which takes advantage of the [Stubs REST API](http://www.ibm.com/support/knowledgecenter/SSBLQQ_8.6.0/com.ibm.rational.rit.ref.doc/topics/r_rtvs_rest_stub.html)
that is part of the Rational Test Control Panel and a simple report in text form containing information about the stubs available and running within the Rational Test Control Panel.

### Usage

    java -jar stub-reporter.jar
        Usage: RTCPUrl [Domain name,Environment name];*
        e.g.: http://localhost:7819/RTCP Domain Name1,Env1;Domain2,Env2
        
### Usage example

- To report on the domain "calculator" and environments "local" and "test" within that domain, for a server hosted on "myhost" the following command sytax would be used:

    java -jar stub-reporter.jar http://myhost:7819/RTCP calculator,local;calculator,test >calculator-stubs.xml
 