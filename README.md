# UGA Bookstore
To get this working, you need a tomcat server.
	
	1. Download tomcat 9 from: https://tomcat.apache.org/download-90.cgi
	
		Make sure you download the core zip
	
	2. Unzip your download. This should unzip to a folder called apache-tomcat-9.0.39
	
	3. This is where most of our code will live. Move folder to appropriate place for easy access.
	
	4. Navigate to the apache tomcat folder. Then, to the bin folder. Inside the bin folder, use the commands ./startup.sh to begin running tomcat server. If you need to restart it at any point, use ./shutdown.sh follwed by ./startup.sh	
	
	5. Now, you will need to cd ../webapps/ROOT   
	
	6. It may now be a good idea to delete everything inside of the ROOT folder. It will cause merge conflicts if you try to pull otherwise.
	
	7. This is where you need to use the command git clone https://github.com/elizabethvitale/Bookstore.git . 
	
		If there are issues, try making sure /ROOT is empty, and you can try using the 'git init' command.
	
	8. Make sure not to forget the '.' - this should ensure you clone the repo into the directory itself and not into a /ROOT/Bookstore directory. This will break the build 

		THIS IS NOT A JOKE. USE THE COMMAND "git clone https://github.com/elizabethvitale/Bookstore.git ." IN IT'S ENTIRETY
		THERE SHOULD BE TWO PERIODS IN THE COMMAND. ONE AFTER GITHUB and ONE at the END OF THE COMMAND
	
	9. At this point, you should be able to access tomcat via localhost:8080 in your web browser. If you see the Bookstore, everything is set up correctly...except for some broken links here and there. If you see a "you have sucessfully created a tomcat server!"-themed webpage, you did not delete the original index.jsp page provided by tomcat, so you'll need to delete the ROOT directly (rm -rf ROOT), recreate an empty one, and pull again.
