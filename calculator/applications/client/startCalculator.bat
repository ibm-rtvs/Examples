rem ***************************************************************************
@rem Licensed Materials - Property of IBM
@rem (c) Copyright IBM Corporation 2014. All Rights Reserved.
@rem
@rem Note to U.S. Government Users Restricted Rights:  
@rem Use, duplication or disclosure restricted by GSA ADP Schedule 
@rem Contract with IBM Corp. 
@rem ***************************************************************************
@echo off

pushd
setlocal

title Calculator Client

@rem Location of downloaded Apache Axis2
set AXIS2_HOME=./axis2-1.6.2


set CP=-classpath com.ibm.greenhat.examples.calculator.jar;%AXIS2_HOME%/lib/axiom-api-1.2.13.jar;%AXIS2_HOME%/lib/axiom-impl-1.2.13.jar;%AXIS2_HOME%/lib/axis2-adb-1.6.2.jar;%AXIS2_HOME%/lib/axis2-kernel-1.6.2.jar;%AXIS2_HOME%/lib/axis2-transport-http-1.6.2.jar;%AXIS2_HOME%/lib/axis2-transport-local-1.6.2.jar;%AXIS2_HOME%/lib/commons-codec-1.3.jar;%AXIS2_HOME%/lib/commons-httpclient-3.1.jar;%AXIS2_HOME%/lib/commons-logging-1.1.1.jar;%AXIS2_HOME%/lib/httpcore-4.0.jar;%AXIS2_HOME%/lib/mail-1.4.jar;%AXIS2_HOME%/lib/neethi-3.0.2.jar;%AXIS2_HOME%/lib/wsdl4j-1.6.2.jar;%AXIS2_HOME%/lib/XmlSchema-1.4.7.jar
java %CP% com.ibm.greenhat.examples.calculator.Calculator http://localhost:7819/RTCP/examples/calculator/calculatorService/calculator.wsdl

endlocal
popd

