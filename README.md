# Street Name Parser

Program to parse concatenated street names and numbers into the object with separated field for street name and 
house number, represented as JSON object

## Overview
### Frameworks 
In this solution I've used Guice framework - simple DI framework from google, and Gson - google library to serialize 
and deserialize Java objects to JSON

### Data structures
`WordTraversal` is the additional data structure that allows operating with the minimal valuable data - word. It 
represents the pointer that moves from one word to the next one, and in every moment we can get a pointer value.  

### Algorithm
In this solution I've used two extractors: to extract house number and street address. Both of them implements the greedy 
algorithm: they both try to as much data as possible

#### House Number Extractor
Since we have a knowledge that house number is located strictly at the beginning or at the end of the string, we 
 1. find the first number
 2. make an assumption that this is the house number
 3. try to expand the bounds as much as possible until we get to the border or to the word that represents the street
 if we found a house number - we return it, otherwise we repeat the process for the last digit
 
 After we found the house number - we extract it from the string
 
#### Address extractor
Address extractor clears the given string from special symbols, like commas, and returns the rest as street address

## Build and Run

### How to build
Application can be built with the command: 
```
mvn package
```    
It will generate `uber jar` file `street-parser-0.0.1-SNAPSHOT-jar-with-dependencies.jar` inside `/target` directory

### How to run
 1. go to the location of `street-parser-0.0.1-SNAPSHOT-jar-with-dependencies.jar` 
 2. execute the command 
```
java -cp target/street-parser-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.kabanov.street_parser.Launcher
```   
 
