<?
$response=array();
if(isset($_POST['username']) && isset($_POST['pwd']) )
	{
	$username=$_POST['username'];
	$pwd=$_POST['pwd'];

		include_once('config.php');
		
		$sql = "SELECT * FROM login where email_id='$username' and password='$pwd'";
		
		$result = mysql_query($sql)  or die(mysql_error()); 
				
			$count = mysql_num_rows($result);
			$data=array();
			if($count<1)
			{
			
			$data["success"] = 0;
			$data["message"] = "Return Code: Invalid Username or Password. " ;
			$response[]=$data;
			}
			else
			{
				while ($row = mysql_fetch_assoc($result)) {
				$data["user_id"]=$row['user_id'];
				}
			
			$data["success"] = 1;
			$data["message"] = "Return Code: Success " ;
			$response[]=$data;
			}
		
		
		
		/*else
		{
		$response["success"] = 0;
		$response["message"] = "Return Code: Error " ;
		*/
	
	}
	echo json_encode($response  );

?>