<?php
        session_start();

        if($_SESSION['user'] == null || isset($_POST['username']))
        {
            $_SESSION['user'] = $_POST["username"];
            $_SESSION['password'] = $_POST["password"];
        }

	//create Database connection with given user name and password
	$myconnection = mysql_pconnect('localhost', $_SESSION['user'], $_SESSION['password']) or endSession();
	//Select Database
	$mydb = mysql_select_db ('project_database') or die ('Could not select database');
        
        function endSession()
        {
            session_destroy();
            echo "invalid login";
            die('invalid login');
        }
?>
