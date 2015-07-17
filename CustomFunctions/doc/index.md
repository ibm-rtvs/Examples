#Custom Functions#

##COBOL##

###COBOL_Integer###

COBOL_Integer(string)

Give a integer based on the _string_.

##String##

###Left###

left(string,length)

Gives the left _length_ number of characters of the _string_.

###Right###

right(string,length)

Gives the right _length_ number of characters of the _string_.

###Right###

__Syntax__: trimlength(string)
__Description__:

Remove spaces on left and right and returns the length of the trimmed _string_.

__Example__:

`
tags["product"]="    IBM Rational Tester            ";
tags["output"]=trimlength(tags["product"]);
// gives output=19
`


