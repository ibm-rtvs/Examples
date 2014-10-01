
# Example : Rational Test Control Panel - Stub Reporter

This example takes advantage of the [Stubs REST API](http://www.ibm.com/support/knowledgecenter/SSBLQQ_8.6.0/com.ibm.rational.rit.ref.doc/topics/r_rtvs_rest_stub.html)
that is part of the Rational Test Control Panel and a simple report in text form containing information about the stubs available and running within the Rational Test Control Panel.
 
## Usage

    java -jar RTCPStubReporter.jar
        Usage: RTCPUrl [Domain name,Environment name];*
        e.g.: http://localhost:7819/RTCP Domain Name1,Env1;Domain2,Env2
        
### Usage example

- To report on the domain "calculator" and environments "local" and "test" within that domain, for a server hosted on "myhost" the following command sytax would be used:

    java -jar RTCPStubReporter.jar http://myhost:7819/RTCP calculator,local;calculator,test
 
## Output

    <?xml version="1.0" encoding="UTF-8"?>
    <server url="http://localhost:7819/RTCP">
        <domains>
            <domain name="calculator">
                <environments>
                    <environment name="local">
                        <components>
                            <component name="calculator">
                                <stubs>
                                    <stub component="calculator" name="VirtualCalculator" operation="" version="1.0">
                                        <instances/>
                                    </stub>
                                    <stub component="calculator" name="2+2=5" operation="" version="1.0">
                                        <instances/>
                                    </stub>
                                </stubs>
                                <operations>
                                    <operation name="Divide">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Divide" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Subtract">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Subtract" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Add">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Add" version="1.0">
                                                <instances/>
                                            </stub>
                                            <stub component="calculator" name="2+2=5" operation="Add" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Multiply">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Multiply" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Modulus">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Modulus" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                </operations>
                            </component>
                        </components>
                    </environment>
                    <environment name="test">
                        <components>
                            <component name="calculator">
                                <stubs>
                                    <stub component="calculator" name="VirtualCalculator" operation="" version="1.0">
                                        <instances/>
                                    </stub>
                                    <stub component="calculator" name="2+2=5" operation="" version="1.0">
                                        <instances/>
                                    </stub>
                                </stubs>
                                <operations>
                                    <operation name="Divide">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Divide" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Subtract">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Subtract" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Add">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Add" version="1.0">
                                                <instances/>
                                            </stub>
                                            <stub component="calculator" name="2+2=5" operation="Add" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Multiply">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Multiply" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                    <operation name="Modulus">
                                        <stubs>
                                            <stub component="calculator" name="VirtualCalculator" operation="Modulus" version="1.0">
                                                <instances/>
                                            </stub>
                                        </stubs>
                                    </operation>
                                </operations>
                            </component>
                        </components>
                    </environment>
                </environments>
            </domain>
        </domains>
    </server>
