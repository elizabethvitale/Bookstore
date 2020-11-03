# UGA Bookstore
To get this working, you need a tomcat server.
	
	1. Download tomcat 9 from: https://tomcat.apache.org/download-90.cgi
	
		If you have a Mac, download the core zip.
		Unzip your download. This should unzip to a folder called apache-tomcat-9.0.39.
		This is where most of our code will live. Move folder to appropriate place for easy access.

		If you have a Windows device, download the installer and follow the instructions.
		It is easiest to leave the default port on '8080', but if there is something already running on it,
		then use a different port.
		
	4. Navigate to the apache tomcat folder. 	
	
		If you have a Mac, thencd into the 'bin' folder and you may use the commands ./startup.sh and ./shutdown.sh to start and stop the server.

		If you have a Windows device, press the Windows key and the 'R' key to dring up a dialog box. Type in 'services.msc' and press enter.
		The Services windows will open and look for a service that say something like 'Apache Tomcat'. Click on it and press the start button on the left sidebar.
		This is the most fullproof way I have found for using Tomcat on Windows.

	5. Now, find go into the webapps folder, and delete the 'ROOT' folder. 
		
	6. Unzip our folder and copy the 'ROOT' folder into the webapps folder.

	7. Now, there is another file in the 'ROOT' folder called bookstore.sql. This is the schema for our database. Import it into MySQL workbench.
		Use this article if you have never imported a .sql file before: https://stackoverflow.com/questions/15884693/how-can-i-import-data-into-mysql-database-via-mysql-workbench.
		
		Make sure to already have a schema created named 'bookstore' and import the file to that specific schema. The code will not run unless the database is named 'bookstore'.

	8. Unfortunately, you will most likely have to change your password temporarily for our code to work, but there is a very simple command. Just
		open your local instance of your SQL server and open a new SQL query. Then type this command exactly: ALTER USER 'root'@'localhost' IDENTIFIED BY 'rootroot';
		
		You can easily change your password back afterwards using the same query and setting 'rootroot' to the password of your choice.

		We will make alterations to our code in the future to ensure that this does not have to be done again.

	9. Now, it is time to actually run Tomcat. Use number 4 to start Tomcat on the device and then open a web browser. Type in 'localhost:8080' or substitute '8080' with
		whatever port Tomcat is running on.

	10. The website should be up and running now. Please email us if something is not working properly.

		elizabeth.vitale@uga.edu
		jcs93125@uga.edu
