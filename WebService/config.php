<?php

$db_host = "localhost";
$db_user = "annuccym_sg";
$db_pwd = "sg@2014";
$db_database = "annuccym_simplegood";
// Create connection
mysql_connect($db_host,$db_user,$db_pwd) or die("Error connecting to db");
mysql_select_db($db_database) or die(mysql_error());


  ?>