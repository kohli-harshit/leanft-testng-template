# LeanFT TestNG Template
This project is designed to be a template which can be used to write Automation scripts using LeanFT with the help of TestNG. 

Some of the features provided in this template are:-
1. LeanFT SDK and Report initialization are handled
2. Logging with Log4J
3. Utils to Read from Properties File.
4. Utils to Create Data Driven Tests from CSV Data.
5. TestNG listeners used.

# Prerequisites
Following batch commands need to be executed in order for Maven to read the LeanFT dependencies:-

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.sdk-standalone.jar" -DgroupId="com.hp.lft" -DartifactId="sdk" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.sdk-javadoc.jar" -DgroupId="com.hp.lft" -DartifactId="sdk" -Dversion=14.0.0 -Dpackaging=JAR -Dclassifier=javadoc -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.report.jar" -DgroupId="com.hp.lft" -DartifactId="report" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.unittesting.jar" -DgroupId="com.hp.lft" -DartifactId="unittesting" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.verifications.jar" -DgroupId="com.hp.lft" -DartifactId="verifications" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

These commands put the LeanFT JARS in the local maven repository
