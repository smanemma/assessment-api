# assessment-api



steps to run in local environment

clone or down load the project

import the project in intellij and do mvn clean install

set the vm options in intellij as below to run test cases for dev,uat and prod and run

-Dconfig.location=<yourpath>/src/test/resources/properties/dev.properties -Dlog4j.configuration.file=<yourpath>/src/test/resources/properties/log4j.properties
