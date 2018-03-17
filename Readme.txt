
Library Management System :

Prerequisites: 

1) Eclipse IDE should be installed. I have used Eclipse Oxygen to make my application.
2) Java version 9 should be installed.
3) Tomcat 8.5 server should be setup.
4) Maven 3.5.2 should be installed.
5) MYSQL should be installed.

Steps to compile and build and install :

1)The libmgmnt folder should be exported in the eclipse workspace.

2) Run the libraryDb.sql file in your local mysql database.

3)Add the connection profile of your local mysql database to web.xml of the project in the context params : dbUser, dbPassword and dbURL.

4) The folder contains all the dependencies which is needed to compile the application.
Right click on libmgmnt-> Run as -> 4 Maven Build.
In the console BUILD SUCCESS should be seen.

5) To run the application :
Right click on libmgmnt-> Run as -> 1 Run on server.
Apache tomcat server is started. 

6) Once the server is started copy the url on the browser : 
http://localhost:8080/libmgmnt/
The application is ready to be used.


