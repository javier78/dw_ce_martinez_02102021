# Shop Service
This Readme goes through the basics on how to use the Shop Service.
###Building and starting the service
In order to build the service, you need to run the gradlew script and pass in the 'build' command. For example, 
on Windows, you need to type "gradlew.bat build". After the service builds, an executable jar can be found in the build 
folder. Upon execution, a pre-seeded database will be generated with some sample data and the service will be started on
port 8080. Attached with the project are some functional postman tests which can be run against the service. 

###Importing the java project in Intellij
This is a standard gradle project, so all that needs to be done in Intellij is:
- Go to File --> New --> Project from existing sources...
- Select the root directory of the project
- Import using Gradle
- The project should be created, and all dependencies should be imported

###Viewing the in-memory database
Simply navigate to http://localhost:8080/h2 in a web browser, and you will be prompted for a password. The password is 
'password'.

###Sending requests to the service
I would recommend using the existing Postman collection to send requests to the service. Functional tests are included there.
All requests should mirror the requests in the primary swagger document.
