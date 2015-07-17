#Custom Functions#

A number of functions will appear in the library. They are grouped

* [COBOL](#cobol)
* [String](#string)
* File
* Date
* System

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
tags["output"]=COBOL_parseNumber("-0000012345");
tags["output"]=COBOL_parseNumber("0000012345-");
tags["output"]=COBOL_parseNumber("000001.2345-");
tags["output"]=COBOL_parseNumber("00000123,45-");
// gives output="????"
```







##String##

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

Gives the right _length_ number of characters of the _string_.

__Example__:
```
tags["product"]="IBM Rational Tester";
tags["output"]=right(tags["product"],6);
// gives output="Tester"
```


###Right###

__Syntax__: trimlength(string)

__Description__:
Remove spaces on left and right and returns the length of the trimmed _string_.

__Example__:
```
tags["product"]="    IBM Rational Tester            ";
tags["output"]=trimlength(tags["product"]);
// gives output=19
```

#Other Sources#

* http://business.vanlint5.nl/html2/rit-functions.html

