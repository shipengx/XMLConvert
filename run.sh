#!/bin/bash

#the output file doesn't necessarily exist.
mvn clean package
java -cp ./target/XMLConvert1-1.0-SNAPSHOT-jar-with-dependencies.jar com.shipeng.xml.XSLTransform    ./input/source.xml    ./input/transform.xsl  ./input/output.txt

#java -cp ./target/XMLConvert1-1.0-SNAPSHOT-jar-with-dependencies.jar com.shipeng.xml.XSLTransform    ./input/source.xml    ./input/transform.xsl    




