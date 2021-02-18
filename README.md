# TTMS - demo for recrutations

## Description
Create a REST service that takes IP addresses, checks which countries they are from and (if there are any) returns a list of country names from the northern hemisphere.
In order to get information about IP addresses please use IP-API: https://ip-api.com/docs.

Application have one endpoint */north-countries* which can accept ip list. The list should be 1 to items. Enpoint return list country wihout duplicate and only country from north part of world.

## Running
1. Application can run by command: 

`./mvnw spring-boot:run`

or from file jar, after `mvn clean install`. This file is in these path: path_to_projec\demo\target.

2. Automated test can run by command:
```
mvn clean
mvn install
mvn test
```

3. Tested endpoint can run by command:

`curl "http://localhost:8888/north-countries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0"`

All commands should run in terminal with maven.

