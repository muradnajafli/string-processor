# String Processor.

## Purpose
Get familiar with basic Kotlin syntax (types, loops, conditions, functions). Work with Kotlin classes, interfaces, and inheritance.

## Introduction
When you need to implement a filter for strings due to business rules, the system has three rules to apply to each string:

“foo” string is not applicable to be presented in a string. If it exists it should be replaced with a “bar” string. It is applicable even if "foo" is a substring.
* If the string contains whitespaces at the beginning or at the end, they should be removed.
* Depending on the size of the string next actions should be applied:

    1. If the string length is in the range from 0 to 5 (inclusively), add a “short ” string to the origin string at the beginning;
    2. If the string length is in the range from 6 to 10 (inclusively), add a “medium ” string to the origin string at the beginning;
    3. If string length in range from 11 to 20 (inclusively) then add “long ” string to the origin string at the beginning;
    4.Keep all other strings as they are.
       
Keep in mind, that after “short ”, “medium ”, “long ” you need to have a space.

An additional business rule involves having an option to process both single strings and arrays of strings.
In case the array of strings is processed, the function should return the number of affected strings in the array.
All changes for the array should be done in place, meaning the initial array should be changed.
The string might be null, in such cases return the default value for that string for both single string processor and array string processor.
The final rule is to have an option to apply different processors in different orders, so each process function should take an array of processors.


## Task description
Complete program to meet the requirements above. You will:

* Implement each string processor class.
* Implement processor for both cases single string and array of string.

Check java docs for additional information about implementation details.
For additional details please check the tests. They might help you to cover edge cases.