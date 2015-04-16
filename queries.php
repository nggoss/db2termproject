<?php
   $result;

	//Execute query chosen by the client
	switch ($_GET["query"])
	{
		case 1:
			executeQuery("SELECT DISTINCT name FROM teams");
			break;
		case 2:
			//Get team for player
			//Input: firstName, lastName, year
			//Output: Team Name
			executeQuery("
			SELECT DISTINCT name FROM teams
			WHERE teamID = (SELECT DISTINCT teamID FROM salaries
							WHERE playerID = (SELECT DISTINCT playerID FROM master WHERE
										  nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
										  AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')
							AND yearID = '" . mysql_real_escape_string($_POST['year']) . "')
						");
			break;
		case 3:
			//Get player's basic info
			//Input: firstName, lastName
			//Output: 
			executeQuery("SELECT * FROM master
							WHERE playerID = (SELECT DISTINCT playerID FROM master WHERE
										  nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
										  AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')");
			break;
		case 4:
			executeQuery("SELECT * FROM batting WHERE
						playerID =(SELECT DISTINCT playerID FROM master WHERE
								nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
								AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')
						AND yearID = '" . mysql_real_escape_string($_POST['year']) . "'");
			break;
		case 5:
			executeQuery("SELECT * FROM pitching WHERE
						playerID =(SELECT DISTINCT playerID FROM master WHERE
								nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
								AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')
						AND yearID = '" . mysql_real_escape_string($_POST['year']) . "'");
			break;
		case 6:
			executeQuery("SELECT * FROM fielding WHERE
						playerID =(SELECT DISTINCT playerID FROM master WHERE
								nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
								AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')
						AND yearID = '" . mysql_real_escape_string($_POST['year']) . "'");
			break;
		case 7:
			executeQuery("SELECT nameFirst, nameLast FROM master
						WHERE playerID = (SELECT DISTINCT playerID FROM managers
										WHERE teamID = (SELECT DISTINCT teamID from teams
														WHERE name = '" . mysql_real_escape_string($_POST['teamName']) . "')
										AND yearID = " . mysql_real_escape_string($_POST['year']) . ")");
			break;
		case 8: //The same Query can be used for case 7 and 8
		case 9:
			executeQuery("SELECT salary
				     FROM salaries
				     WHERE playerID = '" .  mysql_real_escape_string($_GET['playerID']) . "'");
			break;
		case 10:
			executeQuery("SELECT teamID FROM teams");
			break;
		case 11:
			executeQuery("SELECT name_full
				     FROM schools, collegeplaying
				     WHERE collegeplaying.schoolID = schools.schoolID AND collegeplaying.playerID = '" . $_GET['playerID'] . "'");
			break;
		case 12:
			executeQuery("SELECT awardID
				     FROM awardsplayers
				     WHERE yearID = " . mysql_real_escape_string($_GET['yearID']));
			break;
		case 13:
			executeQuery("SELECT awardID
				     FROM awardsplayers
				     WHERE playerID = '" . mysql_real_escape_string($_GET['playerID']) . "'");
			break;
		case 14:
			executeQuery("SELECT DISTINCT playerID, awardID FROM awardsplayers");

			break;
		case 15://TODO finish query 15
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
