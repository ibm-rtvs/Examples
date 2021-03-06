
# Example : echo 

This example uses the **echo** example web service that is built into
RTCP and includes: 

- A RIT project which contains a test to exercise the *echo* operation of the web service.

The integrated web service examples can be found at [http://localhost:7819/RTCP/examples/](http://localhost:7819/RTCP/examples/). 
The *echo* web service provides an echo operation which simple returns any string it is sent. The definition of the
service can be found here [http://localhost:7819/RTCP/examples/echo/echoService/echo.wsdl](http://localhost:7819/RTCP/examples/echo/echoService/echo.wsdl). 
It is this definition that was used to create the test.  
 

## Post download instructions

After download of the Examples project the folder structure for the echo example is:

    echo
        rit-projects
            rit-project-echo.zip
                
- Unzip rit-projects/rit-project-echo.zip. This contains a RIT project echo.ghp

## Basic usage 
- Start RIT
- Select Open, then navigate to the echo/echo.ghp file
- Go to "Test Lab" (F11)
- Right click on test1
- Test should execute 100% complete

# Further reading
 - developerWorks blog entry :  [Rational Integration Tester example - Creating a test from WSDL](https://www.ibm.com/developerworks/community/blogs/e4210f90-a515-41c9-a487-8fc7d79d7f61/entry/rational_integration_tester_example_creating_a_test_from_wsdl?lang=en)