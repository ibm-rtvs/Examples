#Custom Functions#

NOT YET READY

A number of functions will appear in the library. They are grouped

* [COBOL](#cobol)
* [Date](#date and time)
* [File](#file)
* [MQ](#mq)
* [String](#string)
* [System](#system)

##COBOL##

More on RIT and COBOL can be found on http://business.vanlint5.nl/html2/rit-cobol.html

###COBOL_Integer###

__Syntax__: COBOL_Integer(string)

__Description__:
Give a integer based on the _string_.

__Example__:
```
tags["Number"]=99
tags["output"]= COBOL_Integer(tags["Number"],5);
// gives output="00099"
```
Make note that leading 0 can be added by a format definition on a field. But this gives a quick option to generate a number to compare with the field read. Alternatively you could use COBOL_parseNumber.

###COBOL_parseNumber###

__Syntax__: COBOL_parseNumber(string)

__Description__:
Give a sting based on the _string_.
Get the number from a string (integer and floating, separator can be a , or a . ).

__Example__:
```
tags["output"]=COBOL_parseNumber("+0000012345");
// gives output="????"
tags["output"]=COBOL_parseNumber("-0000012345");
// gives output="????"
tags["output"]=COBOL_parseNumber("0000012345-");
// gives output="????"
tags["output"]=COBOL_parseNumber("000001.2345-");
// gives output="????"
tags["output"]=COBOL_parseNumber("00000123,45-");
// gives output="????"
```

###COBOL_SignLeadingSeparate###

__Syntax__: COBOL_SignLeadingSeparate(value,length) 

__Description__:
Return the value back with leading zero's and sign on the left.

__Example__:
```
tags["output"]=COBOL_SignLeadingSeparate("123.45-",10);
// gives output="????"
```

###COBOL_SignTrailingSeparate###

__Syntax__: COBOL_SignTrailingSeparate(value,length) 

__Description__:
Return the value back with leading zero's and sign on the right.

__Example__:
```
tags["output"]=COBOL_SignLeadingSeparate("123.45-",10);
// gives output="????"
```




##DATE and TIME##

###FormatSDF###

__Syntax__: formatSDF( value ) 

__Description__:
Conversion of milliseconds to SimpleDateFormat

Convesion of the time (in milliseconds as a string) to any SimpleDateFormat. See http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html

__Example__:
```
var k = new Date();
// Create a string of the long integer.
tags["DateNow"] = ""+k.valueOf() ;
// http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
tags["DateFormat"] = "yyyy-MM-dd HH:mm:ss";
tags["DateOutput"] = formatSDF(tags["DateNow"],tags["DateFormat"]);
```




##FILE##

###DirectoryExists###

__Syntax__: directoryExists( directoryAsSting ) 

__Description__:
Checks if a directory exists and returns a boolean value.

__Example__:
```
if ( directoryExists( "C:\RIT-Projects\" ) ) {
  // Do something
}
// return true
```

###DirectoryList###

__Syntax__: DirectoryList( directoryAsString ) 

__Description__:
List all files in a directory. The list will not contain subdirectories, only files.

__Example__:
```
tags["output"]=directoryList("C:\RIT-Projects\");
// output contains a list of files.
```

###FileExists###

__Syntax__: FileExists( fileAsString ) 

__Description__:
Some description

__Example__:
```
if ( fileExists( "C:\RIT-Projects\config.properties" ) ) {
  // Do something
}
// return true
```

###FileSize###

__Syntax__: FileSize( fileAsString ) 

__Description__:
Returns the file size in bytes (Long value).

__Example__:
```
fileSize( "C:\RIT-Projects\config.properties" )
// return file size as a Long object.

```

##MQ##

There are some specific functions to support working with MQ.

###MsgID###

__Syntax__: msgID( [ length ] ) 

__Description__:
A generation of a MsgID string consisting of the date, time and a number of random numbers.
If no length is given, length of 20 is assumed. Example data, sample SimpleDateFormat and length:

'''
DATA             20141106193417383080
SimpleDateFormat yyyyMMddHHmmssSSSRRR
Length           12345678901234567890
'''

__Example__:
```
tags["output"]=MsgID()
// Returns "201411061934173830" (default length = 20)
tags["output"]=MsgID(24)
// Returns "201411061935260297365638"
```



##String##

###DoubleSlash###

__Syntax__: DoubleSlash( input ) 

__Description__:
Replace the slashes by double slashes.

__Example__:
```
tags["output"]=DoubleSlash("C:/RIT-Projects/Customer1/Project10",10);
// gives output="????"
```


###Left###

__Syntax__: left(string,length)

__Description__:
Gives the left _length_ number of characters of the _string_.

__Example__:
```
tags["product"]="IBM Rational Tester";
tags["output"]=left(tags["product"],3);
// gives output="IBM"
```


###Right###

__Syntax__: right(string,length)

__Description__:
Gives the right _length_ number of characters of the _string_.

__Example__:
```
tags["product"]="IBM Rational Tester";
tags["output"]=right(tags["product"],6);
// gives output="Tester"
```



###SingleSlash###

__Syntax__: singleSlash( input ) 

__Description__:
Some description

__Example__:
```
tags["output"]=DoubleSlash("C://RIT-Projects//Customer1//Project10",10);
// gives output="????"
```

###Trimlength###

__Syntax__: trimlength(string)

__Description__:
Remove spaces on left and right and returns the length of the trimmed _string_.

__Example__:
```
tags["product"]="    IBM Rational Tester            ";
tags["output"]=trimlength(tags["product"]);
// gives output=19
```



##System##

###GetCentralProperty###

__Syntax__: GetCentralPropert( property [,propertyfile] ) 

__Description__:
Get a property from the property file.

__Example__:
```
tag["QueueToUse"]=GetCentralProperty("MQ.myDomain.myEnvironment.Application1.Application2");
// Gives the applicable queue for that domain/environment combination.
```

###Info###

__Syntax__: info() 

__Description__:
Some information shown in the consoleLog

__Example__:
```
info();
```

###Log###

__Syntax__: log( argument ) 

__Description__:
Write the message string to the consoleLog(?) and the logFile.

__Example__:
```
code here
```

###PrintBinConsole###

__Syntax__: printBinConsole( input ) 

__Description__:
Print out the characters of the string in ASCII codes to the consolelog.

__Example__:
```
printBinConsole("IBM Rational Integration Tester")
```

###PrintConsole###

__Syntax__: printConsole( input ) 

__Description__:
Some description

__Example__:
```
printConsole("IBM Rational Integration Tester")
```

###Sleep###

__Syntax__: sleep( miliseconds ) 

__Description__:
There is a Action Function Sleep. Yes, but this can be embedded into a Action Function, within your script.

__Example__:
```
sleep(10000)
```


#Other Sources#

* http://business.vanlint5.nl/html2/rit-functions.html




###Source###

__Syntax__: function( argument) 

__Description__:
Some description

__Example__:
```
code here
```
