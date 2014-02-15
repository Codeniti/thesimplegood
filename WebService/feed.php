<?php
// Create connection

include_once('config.php');


   
      //Ending the connection

    	
    	$return_result = array();
    	$data= array();

    	$return_result = mysql_query("SELECT ipath,photo_id FROM photo");
		while($row = mysql_fetch_assoc($return_result))
    	{
		$data[] = $row;
		}

		echo str_replace('\\/', '/', json_encode($data));
    	//echo json_encode($data); 


 	




?>