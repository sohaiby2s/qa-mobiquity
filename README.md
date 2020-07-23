# qa-mobility

**Description**
This test automation framework is used to automate backend api based test cases.

**TESTCASES**

**Backend API based test cases**

*  Validate api response when user calls the users endpoint
*  Validate api response when user calls the posts endpoint
*  Validate api response when user calls the comments endpoint
*  Validate api response when user calls the endpoint which does not exist
*  Validate api response when user provides wrong value in parameter
*  Validate that a user is found in users data
*  Validate the count of user's posts
*  Validate email address format on comments of each post


**Prerequisites**
This framework requires following
*  Java 8
*  Maven

**Steps to execute**
1.  Go to the project root folder through cmd

    *  cd {Project-Path}

2.  Execute below command on cmd

    mvn clean test -Denv={ENVIRONMENT NAME}
    
**{ENVIRONMENT NAME}**
This framework is supporting following environments
*  dev1
*  dev2
*  uat
*  prod


**EXAMPLE COMMAND**
mvn clean test -Denv=dev1

By default this framework will execute for **dev1** environment
To execute it on default parameters just execute the following command

mvn clean test

**REPORTING**

A detailed cucumber jvm report is being generated after every execution on following path

target/cucumber-JVM-reports/cucumber-html-reports/overview-features.html
