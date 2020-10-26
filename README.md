# ceADAR-coding-challenge

This is a dynamic web project created to display the content of the given dataset (csv) in a table format.

Below are the steps to be followed to run the project:

1.Install Eclipse 

2.Go to this path : File -> İmport -> Git -> Projects From Git -> Clone URI

3.In the URI section copy and paste this : https://github.com/preethigd-06/ceadar-coding-challenge.git

4.Authentication Section can be left blank.

5.Once the project is imported, change the path of the dataset file (dataset.csv) which is defined in Constants.java file

6.Use maven to install the dependencies defined in the pom file.

7.Use mvn clean and mvn install 

8.Once the build is success, Add Servers 

9.Go this path : Right click of the project -> New -> Other -> Server -> Tomcat v9.0 server and create this server.

7.Take the content of server.xml and context.xml in the ServerConfings folder and replace with Servers'.

8.Run the project with using the Tomcat v9.0 server.

