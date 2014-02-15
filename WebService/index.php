<?php
session_start();
$admin=0;
if(isset($_SESSION['admin']))
	if($_SESSION['admin']=="sunderghar")
	$admin=1;

?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:: Sunder Ghar :: Complete Real Estate Solutions</title>
<style type="text/css">
<!--
body {
	background-image: url(images/bg.jpg);
	margin-left: 100px;
	margin-top: 0px;
	margin-right: 100px;
	margin-bottom: 0px;
}
a {
text-decoration: none;
color: #D42824;
}
.style1 {color: #FFFFFF}
.style2 {
	color: #B61409;
	font-family: "Trebuchet MS";
	font-weight: bold;
	font-size: 18px;
}
.style3 {
	font-family: "Segoe Print";
	font-size: 20px;
}
.style4 {
	font-family: "Trebuchet MS";
	font-size: 12px;
}
.style9 {color: #B61409; font-family: "Trebuchet MS"; font-weight: bold; font-size: 14px; }
.style13 {font-family: "Segoe Print"; font-size: 14px; }
.style14 {font-family: "Segoe Print"}
.style18 {font-family: "Trebuchet MS"; font-size: 12px; color: #000000; }
.style19 {
	font-family: "Trebuchet MS";
	font-size: 12px;
	color: #DC2C1F;
}
.style20 {
	font-family: "Segoe Print";
	font-size: 16px;
	color: #D42824;
}
.style21 {
	font-family: "Segoe Print";
	color: #D92F20;
	font-size: 16px;
}
.style22 {font-family: "Trebuchet MS"; font-size: 12px; color: #D12F1A; }
.style23 {font-family: "Trebuchet MS"; font-size: 12px; color: #C22A1F; }
.style24 {font-family: "Trebuchet MS"; font-size: 12px; color: #D42824; }
-->
</style>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body>
<?php
if($admin==1)
{
echo '<div style="
    position: absolute;
    right: 50px;
    background: white;
    padding: 5px;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
    border: 1px solid black;
    border-top: 0px;
">
    <div style="
    display: inline-block;
"><a href="add.php" style="
    display: block;
    padding: 5px;
    padding-left: 10px;
    padding-right: 10px;
    background-color: black;
    border-radius: 8px;
">Add Property</a></div>
    <div style="
    display: inline-block;
"><a href="logout.php" style="
    display: block;
    padding: 5px;
    padding-left: 10px;
    padding-right: 10px;
    background-color: black;
    border-radius: 8px;
">Logout</a></div>
  </div>';

}
?>
<table width="950" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" background="images/bg.jpg"><table width="804" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="804"><img src="images/header.jpg" width="801" height="193" /></td>
      </tr>
      <tr>
        <td><img src="images/navi.jpg" width="803" height="28" border="0" usemap="#MapMap" /></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF"><script type="text/javascript">
AC_FL_RunContent( 'codebase','http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0','width','803','height','289','src','images/flash','quality','high','pluginspage','http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash','movie','images/flash' ); //end AC code
    </script>
            <noscript>
            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="803" height="289">
              <param name="movie" value="images/flash.swf" />
              <param name="quality" value="high" />
              <embed src="images/flash.swf" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="803" height="289"></embed>
            </object>
            </noscript>
        </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#FFFFFF"><span class="style1"><br />
          </span>
            <table width="780" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="551"><div align="justify"><span class="style2"><span class="style3">Welcome to our website </span></span><br />
                        <br />
                        <table width="780" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td><div align="center"><img src="images/residential.jpg" width="150" height="150" /></div></td>
                            <td><div align="center"><img src="images/commer.jpg" width="150" height="150" /></div></td>
                            <td><div align="center"><img src="images/plots.jpg" width="150" height="150" /></div></td>
                            <td align="center"><img src="images/special.jpg" width="200" height="149" /></td>
                          </tr>
                          <tr>
                            <td class="style9"><div align="center"><span class="style13">Residential Property</span></div></td>
                            <td class="style9"><div align="center"><span class="style13">Commercial Property</span></div></td>
                            <td class="style9"><div align="center"><span class="style13">New Projects</span></div></td>
                            <td class="style9"><div align="center" class="style14">Buy Property</div></td>
                          </tr>
                        </table>
                  <span class="style4"><br />
                      </span></div>
                    <table width="780" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td align="center"><h2 class="style21">Property for Sale</h2></td>
                        <td>&nbsp;</td>
                        <td align="center"><h2 class="style20">Property for Rent</h2></td>
                      </tr>
                      <tr>
                        <td align="center"><table width="380" border="1" cellpadding="4" cellspacing="0" bordercolor="#D6D6D6">
                            <tr>
                              <th width="95" height="30" scope="col"><span class="style18">Title</span></th>
                              <th width="51" scope="col"><span class="style18">Type</span></th>
                              <th width="70" scope="col"><span class="style18">Area</span></th>
                              <th width="94" scope="col"><span class="style18">Price</span></th>
                              <th width="26" scope="col"><span class="style18"> Car Parking</span></th>
                            </tr>
                            
                            <?php 
							
// connect to the database
$con = mysql_connect('localhost','sunder_admin','sunder@123') 
    or die('Could not connect to the server!');
 
// select a database:
mysql_select_db('sunder_realestate') 
    or die('Could not select a database.');
 
 
 
 
 $sql = "SELECT * FROM property where type='sale' Order by property_id desc LIMIT 0,5";
 
 
 
 // execute query:
$result = mysql_query($sql) 
    or die('A error occured: ' . mysql_error());
	
// get result count:
$count = mysql_num_rows($result);
if($count<1)
{


die( "No such Property found");
}	

while ($row = mysql_fetch_assoc($result)) {


	echo ' <tr><td height="25" style="font-size: 13px;"><a href="view.php?type='.$row['type'] . '&subtype='. $row['subtype'] .'" >' . $row['area'] . ' Sq Ft ' . $row['type'] .  '</a></td>
	<td><div class="style18"><span id="ctl00_ctl00_pageContentMaster_pageContent_GridView2_ctl02_Label2">' . $row['type'] . '</span></div></td>
                              <td><div class="style18">' . $row['area'] . ' Sq. Ft.</div></td>
                              <td><div class="style18"> Rs. ' . $row['price'] . '/-</div></td>
                              <td>' . $row['car'] . '</td>
                            </tr>';

	
}


 
 				
							?>
                            
                            
                          <!--  <tr>
                              <td height="25"><a href="#" title="" class="style23" rel="tooltip" data-original-title="Available Industrial unit for sale in Sativali, Vasai East with a very best price.">Available Indus...</a></td>
                              <td><div class="style18"><span id="ctl00_ctl00_pageContentMaster_pageContent_GridView2_ctl02_Label2">Industrial</span></div></td>
                              <td><div class="style18">1050 Sq. Ft.</div></td>
                              <td><div class="style18"> Rs. 35,00,000/-</div></td>
                              <td><a href="http://www.sukhigroups.com/property-details/IS00000081" class="style22">View</a></td>
                            </tr>-->
                            
                        </table></td>
                        <td>&nbsp;</td>
                        <td align="center"><table width="380" border="1" cellpadding="4" cellspacing="0" bordercolor="#D6D6D6">
                            <tr>
                              <th width="95" height="30" scope="col"><span class="style18">Title</span></th>
                              <th width="51" scope="col"><span class="style18">Type</span></th>
                              <th width="70" scope="col"><span class="style18">Area</span></th>
                              <th width="94" scope="col"><span class="style18">Price</span></th>
                              <th width="26" scope="col"><span class="style18"> Car Parking</span></th>
                            </tr>
                            <?php 
							
// connect to the database
$con = mysql_connect('localhost','sunder_admin','sunder@123') 
    or die('Could not connect to the server!');
 
// select a database:
mysql_select_db('sunder_realestate') 
    or die('Could not select a database.');
 
 
 
 
 $sql = "SELECT * FROM property where type='rent' Order by property_id desc LIMIT 0,5";
 
 
 
 // execute query:
$result = mysql_query($sql) 
    or die('A error occured: ' . mysql_error());
	
// get result count:
$count = mysql_num_rows($result);
if($count<1)
{


die( "No such Property found");
}	

while ($row = mysql_fetch_assoc($result)) {


	echo ' <tr><td height="25" style="font-size: 13px;"><a href="view.php?type='.$row['type'] . '&subtype='. $row['subtype'] .'" >' . $row['area'] . ' Sq Ft ' . $row['type'] .  '</a></td>
	<td><div class="style18"><span id="ctl00_ctl00_pageContentMaster_pageContent_GridView2_ctl02_Label2">' . $row['type'] . '</span></div></td>
                              <td><div class="style18">' . $row['area'] . ' Sq. Ft.</div></td>
                              <td><div class="style18"> Rs. ' . $row['price'] . '/-</div></td>
                              <td>' . $row['car'] . '</td>
                            </tr>';

	
}


 
 				
							?>
                            
                           <!--<tr>
                              <td height="29"><a href="http://www.sukhigroups.com/property-details/IS00000081" title="" class="style24" rel="tooltip" data-original-title="Available Industrial unit for sale in Sativali, Vasai East with a very best price.">Available Indus...</a></td>
                              <td><div class="style18"><span id="ctl00_ctl00_pageContentMaster_pageContent_GridView2_ctl02_Label">Industrial</span></div></td>
                              <td><div class="style18">1050 Sq. Ft.</div></td>
                              <td><div class="style18"> Rs. 35,00,000/-</div></td>
                              <td><a href="http://www.sukhigroups.com/property-details/IS00000081" class="style19">View</a></td>
                            </tr>-->
                           
                        </table></td>
                      </tr>
                  </table></td>
              </tr>
            </table>
          <span class="style1"><br />
          </span></td>
      </tr>
      <tr>
        <td><img src="images/footer.jpg" width="803" height="65" border="0" usemap="#Map2" />
            <map name="Map2" id="Map2">
              <area shape="rect" coords="246,15,295,49" href="index.php" />
              <area shape="rect" coords="311,16,379,48" href="aboutus.html" />
              <area shape="rect" coords="392,19,469,48" href="services.html" />
              <area shape="rect" coords="477,12,562,51" href="contact.html" />
            </map>
        </td>
      </tr>
    </table>
      <map name="MapMap" id="MapMap">
        <area shape="rect" coords="15,2,78,28" href="index.php" />
        <area shape="rect" coords="101,1,190,28" href="aboutus.html" />
        <area shape="rect" coords="685,2,785,26" href="contact.html" />
        <area shape="rect" coords="573,3,656,30" href="services.html" />
      <area shape="rect" coords="218,2,292,26" href="sale.php" />
      <area shape="rect" coords="321,2,405,35" href="rent.php" />
      <area shape="rect" coords="430,3,546,30" href="hotproperty.php" />
      </map>
    </td>
  </tr>
</table>
<p>&nbsp;</p>

<map name="Map" id="Map"><area shape="rect" coords="15,2,78,28" href="index.php" />
<area shape="rect" coords="101,1,190,28" href="aboutus.html" />
<area shape="rect" coords="685,2,785,26" href="contact.html" /><area shape="rect" coords="573,3,656,30" href="services.html" />
      <area shape="rect" coords="218,2,292,26" href="sale.php" />
      <area shape="rect" coords="321,2,405,35" href="rent.php" />
      <area shape="rect" coords="430,3,546,30" href="hotproperty.php" />
</map></body>
</html>
