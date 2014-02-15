<?php
$data=array();

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
 
 $data[]=$response;
 echo json_encode($data);
 
?>