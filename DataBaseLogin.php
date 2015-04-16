<?php
	//create Database connection with given user name and password
	
	$myconnection = mysql_connect('localhost', $_POST["username"], $_POST["password"]) or failedLogin();
	//Select Database
	$mydb = mysql_select_db ('project_database') or die ('Could not select database');
        
        header("Location: http://localhost/queriesUI.html");
        
        function failedLogin()
        {
            header("Location: http://localhost");
	}
?>
