# leanft-testng-template

Following batch commands need to be executed in order for Maven to read the LeanFT dependencies:-

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.sdk-standalone.jar" -DgroupId="com.hp.lft" -DartifactId="sdk" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.sdk-javadoc.jar" -DgroupId="com.hp.lft" -DartifactId="sdk" -Dversion=14.0.0 -Dpackaging=JAR -Dclassifier=javadoc -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.report.jar" -DgroupId="com.hp.lft" -DartifactId="report" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.unittesting.jar" -DgroupId="com.hp.lft" -DartifactId="unittesting" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

mvn install:install-file -Dfile="C:\Program Files (x86)\HP\LeanFT\SDK\Java\com.hp.lft.verifications.jar" -DgroupId="com.hp.lft" -DartifactId="verifications" -Dversion=14.0.0 -Dpackaging=JAR -DgeneratePom=true

These commands put the LeanFT JARS in the local maven repository
