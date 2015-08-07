#Custom Functions#

This page covers the custom functions provided by this project.
Additional several standard RIT functions might be documented with examples.

Documentation on the standard provided functions can be fount at:
[IBM Documentation](http://business.vanlint5.nl/html2/doc.html?com.ibm.rational.rit.ref.doc/topics/c_ritref_functions.html)
For more on Actions see [Actions](actions.md)

| Group | Description |
| ---  | --- |
| [COBOL](customfunctions.md#cobol) | Several specific COBOL Copybook related functions |
| [Date](customfunctions.md#date-and-time) | 1 Additional new Date function |
| [File](customfunctions.md#file) | Powerful file functions |
| [MQ](customfunctions.md#mq) | 1 Specific for MQ |
| [String](customfunctions.md#string) | Powerful string processing functions |
| [System](customfunctions.md#system) | Function to be use in script-code |


##Assertion##
Standard RIT.


##COBOL##

More on RIT and COBOL can be found on http://business.vanlint5.nl/html2/rit-cobol.html

| Function | Description |
| ---  | --- |
| [COBOL_Integer](customfunctions.md#COBOL_Integer) | Generate a COBOL Integer |
| [COBOL_parseNumber](customfunctions.md#COBOL_parseNumber) | Parse a COBOL formatted string to a number |
| [COBOL_SignLeadingSeparate](customfunctions.md#COBOL_SignLeadingSeparate) | Generate a COBOL Sign Leading Value |
| [COBOL_SignTrailingSeparate](customfunctions.md#COBOL_SignTrailingSeparate) | Generate a COBOL Sign Trailing Value |


###COBOL_Integer###

__Syntax__: COBOL_Integer(string)

__Description__:
Give a integer based on the _string_.

__Example__:
```
tags["input"]=99
tags["output"]= COBOL_Integer(tags["input"],5);
// gives output="00099"
```
Make note that leading 0 can be added by a format definition on a field. But this gives a quick option to generate a number to compare with the field read. Alternatively you could use COBOL_parseNumber.

###COBOL_parseNumber###

__Syntax__: COBOL_parseNumber(string)

__Description__:
Give a sting based on the _string_.
Get the number from a string (integer and floating, separator can be a , or a . ).

__Example__:
```javascript
tags["output"]=COBOL_parseNumber("+0000012345");
// gives output="12345"
tags["output"]=COBOL_parseNumber("-0000012345");
// gives output="-12345"
tags["output"]=COBOL_parseNumber("0000012345-");
// gives output="-12345"
tags["output"]=COBOL_parseNumber("00000123.45-");
// gives output="-123.45"
tags["output"]=COBOL_parseNumber("00000123,45-");
// gives output="-123.45"
```

###COBOL_SignLeadingSeparate###

__Syntax__: COBOL_SignLeadingSeparate(integerString,length) 

__Description__:
Return the integer value back with leading zero's and sign on the left.

__Example__:
```javascript
tags["output"]=COBOL_SignLeadingSeparate("12345",10);
// gives output="+0000012345"
tags["output"]=COBOL_SignLeadingSeparate("-12345",10);
// gives output="-0000012345"
```

###COBOL_SignTrailingSeparate###

__Syntax__: COBOL_SignTrailingSeparate(value,length) 

__Description__:
Return the value back with leading zero's and sign on the right.

__Example__:
```javascript
tags["output"]=COBOL_SignLeadingSeparate("12345",10);
// gives output="0000012345+"
tags["output"]=COBOL_SignLeadingSeparate("-12345",10);
// gives output="0000012345-"
```

##Coherence##
Standard RIT

##Data Model##
Standard RIT

##DATE and TIME##

| Function | Description |
| ---  | --- |
| [FormatSDF](customfunctions.md#FormatSDF) | Generate a Simple Date Format based on ms |

###FormatSDF###

__Syntax__: formatSDF( value ) 

__Description__:
Conversion of milliseconds to SimpleDateFormat

Convesion of the time (in milliseconds as a string) to any SimpleDateFormat. See http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html

__Example__:
```javascript
var k = new Date();
// Create a string of the long integer.
tags["DateNow"] = ""+k.valueOf() ;
// http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
tags["DateFormat"] = "yyyy-MM-dd HH:mm:ss";
tags["DateOutput"] = formatSDF(tags["DateNow"],tags["DateFormat"]);
```




##FILE##

| Function | Description |
| ---  | --- |
| [CleanFileSeparator](customfunctions.md#CleanFileSeparator) | Replace a nicely formatted full path |
| [DirectoryExists](customfunctions.md#DirectoryExists) | Check if a directory exists |
| [DirectoryList](customfunctions.md#DirectoryList) | List files in a directory |
| [FileExists](customfunctions.md#FileExists) | Check if a file exists |
| [FileSize](customfunctions.md#FileSize) | Return size of file |

FileExists

###CleanFileSeparator###

__Syntax__: cleanFileSeparator( directoryAsSting ) 

__Description__:
Replaces the / and \ by the correct file separator of the platform.
It will remove any double file separators.

__Example__:
```javascript
tags["file"]=cleanFileSeparator( "C:\\RIT-Projects\\\new//CustomFunctions\\" ) )
// return C:\RIT-Projects\new\CustomFunctions\
```

###DirectoryExists###

__Syntax__: directoryExists( directoryAsSting ) 

__Description__:
Checks if a directory exists and returns a boolean value.

__Example__:
```
if ( directoryExists( "C:\\RIT-Projects" ) ) {
  // Do something
}
// return true
```

###DirectoryList###

__Syntax__: DirectoryList( directoryAsString ) 

__Description__:
List all files in a directory. The list will not contain subdirectories, only files.

__Example__:
```javascript
tags["output"]=directoryList("C:\\RIT-Projects");
// output contains a list of files.
```

###FileExists###

__Syntax__: FileExists( fileAsString ) 

__Description__:
Returns a boolean true if the file exists. This can be used directly in the
Decision Action.

__Example__:
```javascript
if ( fileExists( "C:\\RIT-Projects\\config.properties" ) ) {
  // Do something
}
// returns true
```

###FileSize###

__Syntax__: FileSize( fileAsString ) 

__Description__:
Returns the file size in bytes (Long value).

__Example__:
```javascript
fileSize( "C:\RIT-Projects\config.properties" )
// return file size as a Long object.

```

##MQ##

There are some specific functions to support working with MQ.

| Function | Description |
| ---  | --- |
| [MsgID](customfunctions.md#MsgID) | Generates an ID-string |

###MsgID###

__Syntax__: MsgID( [ length ] ) 

__Description__:
A generation of a MsgID string consisting of the date, time and a number of random numbers.
If no length is given, length of 20 is assumed. Example data, sample SimpleDateFormat and length:

<PRE>
DATA             20141106193417383080
SimpleDateFormat yyyyMMddHHmmssSSSRRR
Length           12345678901234567890
</PRE>

__Example__:
```javascript
tags["output"]=MsgID()
// Returns "201411061934173830" (default length = 20)
tags["output"]=MsgID(24)
// Returns "201411061935260297365638"
```



##String##

| Function | Description |
| ---  | --- |
| [DoubleSlash](customfunctions.md#DoubleSlash) | Generate a double slash string |
| [Left](customfunctions.md#Left) | Left most X characters of a string |
| [MidString](customfunctions.md#MidSting) | Returning substring identified by tags |
| [Right](customfunctions.md#Right) | Right most X characters of a string |
| [SingleSlash](customfunctions.md#SingleSlash) | Remove double shashes |
| [Trimlength](customfunctions.md#Trimlength) | Give length of string without the leading and following spaces |


###DoubleSlash###

__Syntax__: DoubleSlash( input ) 

__Description__:
Replace the slashes by double slashes.

__Example__:
```javascript
tags["output"]=DoubleSlash("C:/RIT-Projects/Customer1/Project10",10);
// gives output="????"
```


###Left###

__Syntax__: left(string,length)

__Description__:
Gives the left _length_ number of characters of the _string_.

__Example__:
```javascript
tags["product"]="IBM Rational Tester";
tags["output"]=left(tags["product"],3);
// gives output="IBM"
```

###MidString###

__Syntax__: midString(source,lefttag)
            midString(source,lefttag,righttag)

__Description__:
Gives the left _length_ number of characters of the _string_.

__Example__:
```javascript
tags["product"]="IBM [Rational] Tester";
tags["output"]=midString(tags["product"],"[","]");
// gives output="Rational"
```


###Right###

__Syntax__: right(string,length)

__Description__:
Gives the right _length_ number of characters of the _string_.

__Example__:
```javascript
tags["product"]="IBM Rational Tester";
tags["output"]=right(tags["product"],6);
// gives output="Tester"
```



###SingleSlash###

__Syntax__: singleSlash( input ) 

__Description__:
Remove any double slashes. Consider using CleanFileSeparate.

__Example__:
```javascript
tags["output"]=DoubleSlash("C://RIT-Projects//Customer1//Project10",10);
// gives output="????"
```

###Trimlength###

__Syntax__: trimlength(string)

__Description__:
Remove spaces on left and right and returns the length of the trimmed _string_.

__Example__:
```javascript
tags["product"]="    IBM Rational Tester            ";
tags["output"]=trimlength(tags["product"]);
// gives output=19
```



##System##

| Function | Description |
| ---  | --- |
| [GetCentralProperty](customfunctions.md#GetCentralProperty) | Get central property |
| [Info](customfunctions.md#Info) | Show system information |
| [Log](customfunctions.md#Log) | Log message |
| [PrintBinConsole](customfunctions.md#PrintBinConsole) | Output in HEX format to console |
| [PrintConsole](customfunctions.md#PrintConsole) | Output message to console |
| [Sleep](customfunctions.md#Sleep) | Inline sleep function |


###GetCentralProperty###

__Syntax__: GetCentralPropert( property [,propertyfile] ) 

__Description__:
Get a property from the property file. The property must be in lower case!
If property file is not provided, the file C:\config.properties used.

__Example__:
```javascript
tag["QueueToUse"]=GetCentralProperty("mq.mydomain.myenvironment.application1.application2");
// Gives the applicable queue for that domain/environment combination.
tag["QueueToUse"]=GetCentralProperty("mq.mydomain.myenvironment.application1.application2","C:\\RIT-Projects\\CustomFunctions");
// Gives the applicable queue for that domain/environment combination.
```

###Info###

__Syntax__: info() 

__Description__:
Give system information in the consoleLog.

__Example__:
```javascript
info();
```

###Log###

__Syntax__: log( message [, logfile] ) 

__Description__:
Write the message string to the consoleLog. If a logfile is specified it's appended to that file.

__Example__:
```javascript
log("Transaction started");
logFile=cleanFileSeparator( tags["PROJECT/ROOT_DIRECTORY"]+"\\log\\+tags["TEST/NAME"]+".log");
log("Stub started now",tags["logFile"]);
```
The Log Action shows the message in the RIT GUI Console.

Make note that the Log Action can be set to Warning and Error. Error will fail the test.

###PrintBinConsole###

__Syntax__: printBinConsole( input ) 

__Description__:
Print out the characters of the string in ASCII codes to the consolelog.

__Example__:
```javascript
printBinConsole("IBM Rational Integration Tester")
```

![alt text](http://business.vanlint5.nl/html2/images/rit-function-printBin.png "PrintBinConsole")


###PrintConsole###

__Syntax__: printConsole( input ) 

__Description__:
Equal to log without specification of log-file.

__Example__:
```javascript
printConsole("IBM Rational Integration Tester")
```



###Sleep###

__Syntax__: sleep( miliseconds ) 

__Description__:
There is a Action Function Sleep. Yes, but this can be embedded into a Action Function, within your script.

__Example__:
```javascript
sleep(10000)
```


#Other Sources#

* http://business.vanlint5.nl/html2/rit-functions.html



[Back](index.md)
