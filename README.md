## General info
This project contains tests for Allegro API.
	
## Technologies
Project is created with:
* Java version: 8
* JUnit version: 5.6.2
* Rest-assured version: 4.3.1
	
## Running tests
Make sure that the authorization.key value in application.properties file is set. Test will not work properly without it.
In order to generate it, you should register your application here: https://apps.developer.allegro.pl/new, then set your authorization.key in format just as below:
<ClientId>:<ClientSecret>
Colon should be also included.

To run tests, open the command line inside the project directory, then:

```
$ mvn test - if you want to run your tests only
$ mvn site - if you would like to generate a basic test report also after running your tests
```
To open the report, you have to go to target/site and open the index.html file. 
Choose the Project Reports/Surefire Report tab and the test run report will appear.