There are four main sections to this readme.

1. Setting up a development environment
2. Running the servers
3. How to run unit tests

1. Setting up a dev environment:

Dependencies:
  - Java 1.8
  - MySQL server
  - NodeJS/npm
  - Idea Intellij editor 2016

Notes on MySQL server:
Install MySQL community edition from their website. Use the wizard to
setup a basic MySQL server with the root user's password as 'test'.
Create two databases. One should be named 'sales' and the other should
be named 'customer support'.

Notes on npm:
A version of npm >= 3.0 is required.

Notes on Intellij:
Intellij is the chosen IDE of our development team. Eclipse can be used
but results are not guaranteed.

Getting started:
In Intellij, open the project source you wish to work on. The Sales,
CustomerSupport, and external-stubs projects are all separate Maven
projects but are included under the same parent folder.

In the case of working on Sales, you would select to open the Sales
subfolder through the Intellij prompt. Then right click pom.xml, and choose 
'+ add as maven project'. Intellij should auto-import all dependencies from
the pom.xml file and build the project.

Do this for each project you wish to work on.

Troubleshooting:
If at first the project cannot be compiled by Intellij, make sure that the
correct JDK is being used for compiling. Make sure that a version of JDK 1.8
is set as the compiler for the project.

2. Running the servers
Running the application servers is fairly straighforward. 

Follow the steps from Section 1 of this readme to open all of the projects.

In each projects source code directory, in Intellij, find and run the APIs.java file.

This should start up the application servers for each project.

3. How to run unit tests
To run unit tests, again follow the steps in Section 1 to open the projects.

In the directory, /src/main/test find the test file or directory you wish to run.

In Intellij, right click the file, and click 'run "filename"'. Intellij's
built in support for JUnit will run the tests and show the output.

4. Integration Tests (Postman Scripts)
At the file path Sales/PostmanScripts/SalesExternalAPITests.postman_collection.json is a file that can be imported into the postman application.  It will create a collection of tests that can be accessed on the left side and run as a collection.  The first run, all tests will pass.  The second run, some tests will fail which shows that the calls update the database.