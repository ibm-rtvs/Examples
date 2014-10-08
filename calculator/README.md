
# Example : calculator

This example takes advantage of the **calculator** example web service that is integrated into
RTCP and includes:
 
- A Java calculator application which uses the *calculator* web service to perform mathematical calculations.
Though the application itself remains unchanged, it can be launched in one of two modes.
	- Normal mode where it connects directly to the web service.
	- Test mode, where traffic is routed through through a RIT proxy. 
	This facilitates both tracing at the operational level and use of the stubs provided.	

![Calculator](calculator.png "The calculator app")	

- A RIT project which contains
    - A data-driven test, fed by rows in a CSV file.
    - A series of basic test calculator tests, one per calculator operation.
	- A test suite which exercises basic calculator operations of the web service.
	- Monitor definitions for all of the calculator operations.
	- A stub which simulates all of the operations of the calculator web service.
    - A partial stub which lets most operations [pass-through](http://www-01.ibm.com/support/knowledgecenter/SSBLQQ_8.6.0/com.ibm.rational.rtvs.ref.doc/topics/c_rtvsref_sift_passthrough.html?lang=en "sift-and-pass-through") to the actual web service
      but intercepts a specific operation to return an unexpected value.

The integrated web service examples can be found at [http://localhost:7819/RTCP/examples/](http://localhost:7819/RTCP/examples/)
The *calculator* web service provides state-less operations Add, Divide, Modulus, Multiply and Subtract. The definition of 
the service can be found here [http://localhost:7819/RTCP/examples/calculator/calculatorService/calculator.wsdl](http://localhost:7819/RTCP/examples/calculator/calculatorService/calculator.wsdl). 
It is this definition that was used to create the test.  
 
## Post download instructions

After download of the Examples project, the folder structure for the calculator example is:

    calculator
        rit-projects
            rit-project-calculator.zip
        applications
            client
                calculator.jar
                startCalculator.bat  
                startCalculatorInTestMode.bat 
                
- Unzip rit-projects/rit-project-calculator.zip. This contains a RIT project calculator.ghp.                
- Download the required Apache Axis2 libraries from [http://axis.apache.org/axis2/java/core/download.cgi#a1_6_2](http://axis.apache.org/axis2/java/core/download.cgi#a1_6_2) and unzip into the calculator/applications/client folder.                

## Running the calculator application
- Open a command prompt.
- change directory to Examples/Calculator/applications/client.
- run startCalculator.bat.

## Running the basic test suite
- Start RIT.
- Select Open, then navigate to the calculator/calculator.ghp file. 
- Go to "Test Lab" (F11).
- Right click on Logical/calculator/Suites/BasicTests.
- Select *Run*.
- The BasicTests suite, which consists of 5 tests shows 100% complete.

## Running the data-driven test, based on rows in a CSV file
- Start RIT.
- Select Open, then navigate to the calculator/calculator.ghp file. 
- Go to "Test Lab" (F11).
- Right click on Logical/calculator/Tests/TestOperations.
- Select *Run*.
- The TestOperations test, which iterates over the 5 data rows in the *calculations.csv* file shows 100% complete.

The data in the *calculations.csv* file is of the form

    Operation,Value1,Value2,Result
    Add,1,1,2
    Divide,12,4,3
    Modulus,12,5,2
    Multiply,3,4,12
    Subtract,7,4,3

## Recording the calculator application
- Start RIT.
- Select Open, then navigate to the calculator/calculator.ghp file. 
- Go to "Recording Studio" (F9).
- In the Events Monitors window import monitors from file *monitors.rsh*. 
- Select *Record*.
- Open a command prompt.
- Change directory to Examples/Calculator/applications/client.
- Run startCalculatorInTestMode.bat.
- Using the calculator application will results in operations being shown in the Events View window of RIT.


## Temporarily replacing the calculator web service with the "VirtualCalculator" stub (for 5 minutes)

As a quick test you can try out the VirtualCalculator stub without deploying it. 
You can have it run within RIT, however it will only run for 5 minutes. 
 In this mode, the transaction throughput of the stub is also throttled.

- Start RIT.
- Select Open, then navigate to the calculator/calculator.ghp file. 
- Go to "Test Lab" (F11).
- Right click on Logical/calculator/Stubs/VirtualCalculator.
- Select *Run*.
- Wait until all parts of the stub are showing *ready* status in the Task Monitor window.
- Open a command prompt.
- Change directory to Examples/Calculator/applications/client.
- Run startCalculatorInTestMode.bat
- Using the calculator application will results in increments of progress being shown in the Console window of RIT.

To confirm that the VirtualCalculator is, in fact, being used:

 - See stubbing activity in [RTCP > administration > activity](http://localhost:7819/RTCP/#Administration:activity)


## Temporarily replacing the calculator web service with the "2+2=5" stub (for 5 minutes)

As a quick test you can try out the "2+2=5" stub without deploying it. 
You can have it run within RIT, however it will only run for 5 minutes. 
In this mode, the transaction throughput of the stub is also throttled.

The "2+2=5" stub uses the [sift-and-pass-through](http://www-01.ibm.com/support/knowledgecenter/SSBLQQ_8.6.0/com.ibm.rational.rtvs.ref.doc/topics/c_rtvsref_sift_passthrough.html?lang=en "sift-and-pass-through") facility in RTVS to allow all mathematical operations to pass through to the real calculator
web service, except in the condition where the operation is 2+2. In this case it returns the value 5. 

- Start RIT
- Select Open, then navigate to the calculator/calculator.ghp file 
- Go to "Test Lab" (F11)
- Right click on Logical/calculator/Stubs/VirtualCalculator
- Select *Run*
- Wait until all parts of the stub are showing *Ready* status in the Task Monitor window.
- Open a command prompt
- Change directory to Examples/Calculator/applications/client
- Run startCalculatorInTestMode.bat
- Using the calculator application will results in increments of progress being shown in the Console window of RIT

To confirm that the "2+2=5" stub is, in fact, being used:

 - Use the calculator to confirm that performing the calculation 2+2 returns the value 5.
 - See stubbing activity in [RTCP > administration > activity](http://localhost:7819/RTCP/#Administration:activity)

## Replacing the calculator web service with the "VirtualCalculator" stub, deployed to an agent.

In this section we deploy the stub to an agent running on the same computer. 
The stub will run indefinitely.

- Start RIT
- Select Open, then navigate to the calculator/calculator.ghp file 
- Go to "Test Lab" (F11)
- Right click on Logical/calculator/Stubs/VirtualCalculator
- Select *Run...*
- Select Execution preference of *Run later*, then Press run.
- View [RTCP > Home > Environments > domain1 > calculator](http://localhost:7819/RTCP/#Environments:domain1/calculator) and
wait until all operations are being *Satisfied By* the **VirtualCalculator 1.0** and their status is *Ready*
- Open a command prompt
- change directory to Examples/Calculator/applications/client
- run startCalculatorInTestMode.bat

To confirm that the VirtualCalculator is, in fact, being used:

 - See stubbing activity in [RTCP > administration > activity](http://localhost:7819/RTCP/#Administration:activity)
 - Click ![Spyglass](spyglass.png "view deployed projects and stubs") against RTVS on [RTCP > Home > Agents](http://localhost:7819/RTCP/#Agents:) to view deployed projects and stubs of the RTVS Agent.

# How it works

## How does the calculator app traffic get routed via the proxy in 'Test Mode' without needing to change the app?

Compare the two startup scripts, **startCalulator** and **startCalculatorInTestMode**. 
You'll find the only real difference is the addition of two [java networking properties](http://docs.oracle.com/javase/7/docs/api/java/net/doc-files/net-properties.html) in
the startup script.

    -Dhttp.proxyHost=localhost -Dhttp.proxyPort=3128

During the default installation process for *Rational Integration Platform Pack* a default http proxy is installed and auto-started.
This proxy's configuration is determined by the contents of RIT-Platform/httptcp/registration.xml.

    <server base-url="http://localhost:7819/RTCP"/>
	<http-proxy port="3128" bind-address="">
	</http-proxy>	

Port 3128 is the default port that is used to manage http traffic, in conjunction with the associated RTCP instance (default is http://localhost:7819/RTCP) 

# Further reading
 - TechNote : [Message filtering and validation](http://www-01.ibm.com/support/docview.wss?uid=swg21669000).
 - developerWorks article :  [Virtualize multiple behaviors in parallel with Rational Test Virtualization Server](http://www.ibm.com/developerworks/rational/library/multiple-behaviors-parallel-test-virtualization-server/index.html).