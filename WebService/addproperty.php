<?php

$response=array(); 		
$allowedExts = array("gif", "jpeg", "jpg", "png");
$temp = explode(".", $_FILES["file"]["name"]);
$extension = end($temp);
if ((($_FILES["file"]["type"] == "image/gif")
|| ($_FILES["file"]["type"] == "image/jpeg")
|| ($_FILES["file"]["type"] == "image/jpg")
|| ($_FILES["file"]["type"] == "image/pjpeg")
|| ($_FILES["file"]["type"] == "image/x-png")
|| ($_FILES["file"]["type"] == "image/png"))
&& ($_FILES["file"]["size"] < 2000000)
&& in_array($extension, $allowedExts))
  {
  if ($_FILES["file"]["error"] > 0)
    {
	$response["success"] = 0;
    $response["message"] = "Return Code: " . $_FILES["file"]["error"];
    //echo "Return Code: " . $_FILES["file"]["error"] . "<br>";
    }
  else
    {
    //echo "Upload: " . $_FILES["file"]["name"] . "<br>";
   // echo "Type: " . $_FILES["file"]["type"] . "<br>";
   // echo "Size: " . ($_FILES["file"]["size"] / 1024) . " kB<br>";
   // echo "Temp file: " . $_FILES["file"]["tmp_name"] . "<br>";

    if (file_exists("upload/" . $_FILES["file"]["name"]))
      {
			$response["success"] = 0;
			$response["message"] = "Return Code: " . $_FILES["file"]["error"];
			//header('Refresh: 2;url=index.php');
			//die( $_FILES["file"]["name"] . " already exists. ");
			//echo "Try again with a different file name<br />Redirecting....";
      }
    else
      {
	   move_uploaded_file($_FILES["file"]["tmp_name"],
      "user/" . $_FILES["file"]["name"]);
		$response["success"] = 1;
		$response["message"] = "File uploaded Successfully";
      }
    }
  }
else
  {
  $response["success"] = 0;
		$response["message"] = "Image upload error. Check filename,size and format.";
  }



 $image=$_FILES["file"]["name"];



/*
** Connect to database:
*/
 
// connect to the database
include_once('../config.php');
//$con = mysql_connect('localhost','sunder_admin','sunder@123') 
//    or die('Could not connect to the server!');
 
// select a database:
//mysql_select_db('sunder_realestate') 
//    or die('Could not select a database.');
	
		$price=mysql_real_escape_string($_POST['price']);
		$type=mysql_real_escape_string($_POST['type']);
		$subtype=mysql_real_escape_string($_POST['subtype']);
		$area=mysql_real_escape_string($_POST['area']);
		$locality=mysql_real_escape_string($_POST['locality']);
		$floor=mysql_real_escape_string($_POST['floor']);
		$car=mysql_real_escape_string($_POST['car']);
		$rtype=mysql_real_escape_string($_POST['rtype']);
		$hotproperty=mysql_real_escape_string($_POST['hotproperty']);
		if(isset($_POST['depositp']))
		$deposit=mysql_real_escape_string($_POST['depositp']);
		else
		$deposit=-1;
//	  $image=mysql_real_escape_string($_POST['price']);
if(!isset($image))
$image="";

if(isset($_POST['edit']))
{
$pid="";
	
	if(isset($_POST['pid']))
		$pid = $_POST['pid'];
	else
		header('Refresh: 0;url=index.php');
 
 
// escape bad chars:
$pid = mysql_real_escape_string($pid);
 	if(!isset($image))
		$sql="update property set price='$price',type='$type',subtype='$subtype',area='$area',locality='$locality',floor='$floor',car='$car' where property_id='$pid'";
	 else
		$sql="update property set price='$price',type='$type',subtype='$subtype',area='$area',locality='$locality',floor='$floor',car='$car',image='$image' where property_id='$pid'";
 
}
else
{
	/*
** Do a insert query:
*/

// create SQL query:
$sql = "INSERT INTO property (price,type,subtype,area,locality,floor,car,image,deposit,hotproperty,rtype) VALUES ('$price','$type','$subtype','$area','$locality','$floor','$car','$image','$deposit','$hotproperty','$rtype')";

}
// execute query:
$result = mysql_query($sql) or die('A error occured: ' . mysql_error());
 


// get the new ID of the last insert command
//$new_id = mysql_insert_id();
header('Refresh: 2;url=index.php');
echo "Data inserted successfully<br />Redirecting....";
//}
?>