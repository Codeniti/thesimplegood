<?php

session_start();
$return_result = array();
$data=array();
/*
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



 $image=$_FILES["file"]["name"];*/
 





@include('config.php');


   // Initialize session data
    

	if(isset($_SESSION['username'])) {
     //echo "You're already registered as ".$_SESSION['username'];
	 $return_result["success"] = 0;
           		$return_result["message"] = "You're already registered as ".$_SESSION['username'];
 	}

     // Checks if the form was submitted
     else  {

        
			$uname = $_POST['username'] ;
			$email = $_POST['email'];
			$pwd = $_POST['password'];
			$name = $_POST['name'];
			//$email = isset($_POST['gender']) ? trim($_POST['gender']) : '';


          if(!empty($uname)
          && !empty($email)
          && !empty($pwd)
          && !empty($name)) {

              // Store escaped $_POST values in variables 
              $uname = htmlentities($_POST['username']);
              $email = htmlentities($_POST['email']);
              $pwd = htmlentities($_POST['password']);
              $gen = "";
              $name = htmlentities($_POST['name']);
              $encrypt_pwd=md5($pwd);

              $_SESSION['username'] = $uname;

              mysql_query("INSERT INTO users (user_id, email_id, password, name)
							VALUES ('$uname','$email','$encrypt_pwd','$name')");
			
			$return_result["success"] = 1;
           		$return_result["message"] = "Success";
	//              echo "Thanks for registering!",
      //        "Username: $uname ",
        //      "Email: $email ";
          }

          /*
           * If the user did not fill out both fields, display 
           * a message letting them know that both fields are 
           * required for registration
           */

           else {
           		
           		$return_result["success"] = 0;
           		$return_result["error"] = "Please fill out both fields!";

           		
           }
     }

     // If the form was not submitted, dispalys the HTML form



  //Ending the connection
$data[]=$return_result;

echo json_encode($data );


?> 
