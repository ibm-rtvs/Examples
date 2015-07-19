#Actions#

See official documentation on details. Here some hints and tips are documented on selected Actions which might be handy.

##General##

*You can use the custom functions which gives a boolean as a return, for example directoryExists.
This is not suggested when you use the contextual menu on ECMAsript field.*

##Tag Data Store##

Sample file which can be copy and pasted into the tag store.

'''
Names/TeamLeader	Boss
Names/Deverloper	Dave
Names/Tester	Tanuj
'''
Note there is a single tab between the tagname and default value. The Names/ gives a hierarchy when
you ask for a variable via contextual menu. Disadvantage is that the tags[] notation must be used to assign values.

##Assertion##
You can use the custom functions which gives a boolean as a return, for example directoryExists.

You can add a failure path to an assertion.

##Iterate##
Example: WaitForFile

The iterate can be used as a 'sleep', see the CreateTriggerFile example.

##Sleep##
Example: Sleep

The iterate can be used as a 'sleep', see the CreateTriggerFile example.

[Back](index.md)