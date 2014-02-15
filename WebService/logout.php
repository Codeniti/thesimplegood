<?php
session_start();
//header( "refresh:5;url=login.html" );

unset($_SESSION['username']);
echo "Successfully Loggeed out.<br />Redirecting in 3 seconds.....";
session_destroy();

?>