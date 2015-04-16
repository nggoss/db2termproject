<?php
   $result;

	//Execute query chosen by the client
	switch ($_GET["query"])
	{
		case 1: //TODO implement cases 1-4, 14
			executeQuery("SELECT name FROM teams");
			break;
		case 2:
			executeQuery("SELECT teamID FROM teams");
			break;
		case 3:
			executeQuery("SELECT teamID FROM teams");
			break;
		case 4:
			executeQuery("SELECT teamID FROM teams");
			break;
		case 5:
			executeQuery("SELECT *
				     FROM batting, pitching, fielding
				     WHERE pitching.playerID = '" . mysql_real_escape_string($_GET['playerID']). "'
				     AND batting.playerID = '" . mysql_real_escape_string($_GET['playerID']) . "'
				     AND fielding.playerID = '" . mysql_real_escape_string($_GET['playerID']) . "'");
			break;
		case 6:
			executeQuery("SELECT playerID, teamID FROM managers");
			break;
		case 7: //The same Query can be used for case 7 and 8
		case 8:
			executeQuery("SELECT salary
				     FROM salaries
				     WHERE playerID = '" .  mysql_real_escape_string($_GET['playerID']) . "'");
			break;
		case 9:
			executeQuery("SELECT teamID FROM teams");
			break;
		case 10:
			executeQuery("SELECT name_full
				     FROM schools, collegeplaying
				     WHERE collegeplaying.schoolID = schools.schoolID AND collegeplaying.playerID = '" . $_GET['playerID'] . "'");
			break;
		case 11:
			executeQuery("SELECT awardID
				     FROM awardsplayers
				     WHERE yearID = " . mysql_real_escape_string($_GET['yearID']));
			break;
		case 12:
			executeQuery("SELECT awardID
				     FROM awardsplayers
				     WHERE playerID = '" . mysql_real_escape_string($_GET['playerID']) . "'");
			break;
		case 13:
			executeQuery("SELECT DISTINCT playerID, awardID FROM awardsplayers");

			break;
		case 14://TODO finish query 14
			executeQuery("SELECT teamID FROM teams");
			break;
		default:
			exit('That Query is not supported');
	}
	
	returnResultsToClient($result);
	
	//accepts SQL query as parameter, and executes query 
	function executeQuery($query)
	{
		echo $query . '<br><br>';
		$GLOBALS['result'] = mysql_query($query) or die ('Query failed: ' . mysql_error());
	}
	
	//accepts query results as parameter, prints all results to client
	function returnResultsToClient($result)
	{
		while ($row = mysql_fetch_array ($result, MYSQL_BOTH))
		{
			for($x = 0; $x < count($row)/2; $x++)
			{
				echo $row[$x] . "\t";
			}
			
			echo '<br>';
			/*
			echo $row["playerID"] . "\t";
			echo $row["birthYear"];
			echo '<br>';*/
		}
	}
	
	mysql_free_result($result);
?>