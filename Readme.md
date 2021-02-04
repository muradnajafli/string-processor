# Kotlin String Processor.

## Purpose
Get familiar with basic Kotlin syntax (types, loops, conditions, functions). Work with Kotlin classes, interfaces and inheritance.

## Introduction
Let’s consider that we need to implement filter for strings due to business rules. System has three rules to apply to each string:
* “foo” string is not applicable to be presented in a string. If it exists it should be replaced with “bar” string. It is applicable even if foo is substring.
* If string contains whitespaces at the begging or at end, they should be removed.
* Depending on a size of the string next actions should be applied:
    1.	If string length in range from 0 to 5 (inclusively) then add “short ” string to the origin string at the begging;
    2.	If string length in range from 6 to 10 (inclusively) then add “medium ” string to the origin string at the begging;
    3.	If string length in range from 11 to 20 (inclusively) then add “long ” string to the origin string at the begging;
    4.	Keep all other strings as is without any changes.

Additional business rule is to have an option to process both single string and array of strings. 
In case array of strings is processed function should return numbers of affected strings in the array.
All changes for the array should be done in place, meaning initial array should be changed.
String might be null in such a case return default value for that string for both single string processor and array string processor.
The final rule is to have an option to apply different processors in different orders, so each process function should take array of processors.

## Task description
Complete program to meet requirements above. You will:
* Implement each string processor class
* Implement processor for both cases single string and array of string.

Check java docs for additional information about implementation details.
For additional details please check tests. They might help you to cover edge cases.

