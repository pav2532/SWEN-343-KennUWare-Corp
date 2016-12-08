# KennUWare Sales and Customer Support

1. Setting up a development environment
2. Running the servers
3. How to run unit tests

## Preparing the environment:

Dependencies:
  - Java 1.8
  - MySQL server
  - NodeJS/npm (latest versions)
  - Idea Intellij editor 2016
  - Postman (To run API tests)

### Java version
This project makes use of lambda functions, a feature introduced in java 8.
Thus, a java version of 1.8 or higher is required to run the projects.

### Setting up MySQL server:
Install MySQL community edition from their website. Use the wizard to
setup a basic MySQL server with the root user's password as 'test'.
Create three databases named:
  - sales
  - customersupport
  - sso

### NodeJS/NPM:
A version of npm >= 3.0 is required.
A version of NodeJS >= 6.0 is required.

### Intellij:
Intellij is the chosen IDE of our development team. Eclipse can be used
but results are not guaranteed.

## Getting started:
Clone this repository into the directory you want it to be in.
```
git clone https://github.com/pav2532/SWEN-343-KennUWare-Corp.git
```

### Working on the backend web services
Using Intellij, open the project source you wish to work on. The Sales,
CustomerSupport, SSO, and external-stubs projects are all separate Maven
projects but are included under the same parent folder.

In the case of working on Sales, you would select to open the Sales
subfolder through the Intellij prompt. Then right click pom.xml, and choose 
'+ add as maven project'. Intellij should auto-import all dependencies from
the pom.xml file and build the project.

Do this for each project you wish to work on or inspect.

### Working with the frontend
To work on the frontend, simply open the 'react/' folder under the parent of this
repository in your favorite text editor. 

To install the dependencies and run the front-end:
```
cd react
```
```
npm install
```
```
npm start
```

Navigate to localhost:3000/sales or localhost:3000/customer-support to view the files.

Note: You need to have the backend services running for the front-end to work properly.

These three commands will get the frontend up and running. Hot reloading is built in, so 
any changes you make to the project should automatically show up in the web browser.

### Troubleshooting:
If at first the project cannot be compiled by Intellij, make sure that the
correct JDK is being used for compiling. Make sure that a version of JDK 1.8
is set as the compiler for the project.

## Running the servers
You can run any of the projects from the IntelliJ editor as described in 'Working on backend services'.

### Running services from .jar files - Locally
.jar files have been built within each directory to make the services easier to deploy.

To run the services locally using jar files, execute:
```
java -jar Sales/out/artifacts/Sales_Jar/Sales.jar
java -jar CustomerSupport/out/artifacts/CustomerSupport_jar/CustomerSupport.jar
java -jar SSO/out/artifacts/SSO_Jar/SSO.jar
java -jar external-stubs/out/artifacts/external_stubs_Jar/external-stubs.jar
```

You will either need 4 terminal windows, one for each service, or run them with a command 
that lets you detach from the process. Once these are running, API tests can be run and the 
frontend can also be used.

### Deploying to the VM
Deploying the project to the VM is similar to deploying them locally, except you will be on a VM.
On the VM, clone the git repository and checkout the branch 'goodURL'. This is specific to vm343c.se.rit.edu.
A code change will need to be made for it work properly on another VM because a server.properties file is not used in deployment.
This is not a concern for deploying locally though, because the master branch is set up to run on a local environment.

Follow the steps for running the .jar files locally, on the VM in the newly cloned directory.

Follow the steps for working on the front-end to set it up and run it. (npm install; npm start)

Navigate to your VM's URL on port 3000 and the application should be there.

## How to run unit tests
To run unit tests, open the projects in Intellij, right-click the files or folders you wish to run 
within src/main/test/ and select "Run test". This will use the built-in JUnit runner to execute the tests.

## Integration/API Tests (Postman Scripts)
Within the Sales and CustomerSupport directories, there is a folder called PostmanScripts. 
With Postman, import the file with the filename like *.postman_collection.json
It will create a collection of tests that can be accessed on the left side and run as a collection. 
Some tests may fail if the database has been modified and the expected values are not received.