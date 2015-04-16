<?php
   $result;

	//Execute query chosen by the client
	switch ($_GET["query"])
	{
		case 1: //TODO implement cases 1-4, 14
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
		case 8:
			executeQuery("SELECT salary
				     FROM salaries
				     WHERE playerID =(SELECT DISTINCT playerID FROM master WHERE
								nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
								AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')
							AND yearID = " . mysql_real_escape_string($_POST['year']));
			break;
		case 9:
			executeQuery("SELECT name_full
				     FROM schools, collegeplaying
				     WHERE collegeplaying.schoolID = schools.schoolID
					 AND collegeplaying.playerID = (SELECT DISTINCT playerID FROM master WHERE
								nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
								AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')");
			break;
		case 10:
			executeQuery("SELECT awardID, nameFirst, nameLast
						FROM master, awardsplayers
						WHERE master.playerID = awardsplayers.playerID
						AND yearID = " . mysql_real_escape_string($_POST['year']));
			break;
		case 11:
			executeQuery("SELECT yearID, awardID FROM awardsplayers
						WHERE playerID = (SELECT DISTINCT playerID FROM master WHERE
								nameFirst = '" . mysql_real_escape_string($_POST['firstName']) . "' 
								AND nameLast = '" . mysql_real_escape_string($_POST['lastName']) . "')");
			break;
		case 12:
			executeQuery("SELECT nameFirst, nameLast
						FROM master
						WHERE playerID IN (SELECT DISTINCT playerID FROM awardsplayers
											WHERE awardID = '" . mysql_real_escape_string($_POST['awardID']) . "')");

			break;
		case 13://TODO finish query 13
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
