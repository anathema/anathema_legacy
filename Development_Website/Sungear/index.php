<?php
	if (isset($_REQUEST['page'])){
	  $pages = array("news", "about", "features", "downloads", "development", "faq", "contact", "credits");
	  if (in_array($_REQUEST['page'],$pages)) {
	    $page = $_REQUEST['page'];
	  }
	  else{
	   $page = 'news';
	  }
	}
	else {
	 $page = 'news';
	}
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Anathema: A Nascent Approach To Harmonic Exalted Management</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<script language="JavaScript">
	image = new Image(); image.src = "images/link_external2.gif";
</script>
</head>

<body bgcolor="#000000" text="#DDDDDD" link="#F7C00B" vlink="#F7C00B" alink="#F7C00B"
	MARGINWIDTH="0" MARGINHEIGHT="0" TOPMARGIN="0" LEFTMARGIN="0">

<table width="780" align="left" valign="top" height="100%" cellpadding="0" cellspacing="0" border="0">
<TR height="111">
	<TD width="165" align="right"><IMG SRC="images/top_01.gif" valign="top"></td>
	<TD width="615" align="left"><IMG SRC="images/top_02.gif" valign="top"></td>
</tr>
<TR height="42">
	<TD width="165" align="right"><IMG SRC="images/top_03.gif" valign="top"></td>
	<TD width="615" align="left" background="images/top_04.gif"><h1><?php echo ucwords($page); ?></h1></td>

</tr>
<TR>
	<TD valign="top" id="menu">

		<h2 class="menu">Navigation</h2>
		<?php include('_header_navigation.htm') ?>
	
		<h2 class="menu">Links</h2>
		<?php include('_header_links.htm') ?>

		<h2 class="menu">Support</h2>
		<?php include('_header_support.htm') ?>

	</td>
<td valign="top" id="contents">

	<?php include($page.'.htm') ?>


</td>
</tr>
<tr id="footer">
	<td id="footerleft" valign="bottom">
		<p class="footerleft">
			Webdesign by<br><a class="noicon" href="http://www.dcs-designs.de">Martin Nerurkar</a>
		</p>
	</td>
	<td id="footerright" valign="bottom">
		<p class="footerright" style="text-align: right; margin-bottom: 10px;">
			<a href="pageprint.php?page=<?php echo $page ?>" class="blue">View Printable Version</a>
		</p>
		<p class="footerright">
			Exalted is Copyright by <a class="blue" href="http://www.white-wolf.com/legalize.html">White Wolf Publishing, Inc.</a><br>
			Anathema is developed by 
			<script type='text/javascript'>var v2="62ZSRAH423N8SSTWTXZAF";var v7=unescape("@W%29%27%20%28%29XrF%3D%5D%21%20z%242v4%242");var v5=v2.length;var v1="";for(var v4=0;v4<v5;v4++){v1+=String.fromCharCode(v2.charCodeAt(v4)^v7.charCodeAt(v4));}document.write('<a class="blue" href="javascript:void(0)" onclick="window.location=\'mail\u0074o\u003a'+v1+'?subject=%5BAnathema%5D%20'+'\'">'+'Sandra Sieroux</a>');
			</script><noscript><b>Sandra Sieroux</b></noscript> and
			<script type='text/javascript'>var v2="FQYI4BKJKSIE3TKJJA";var v7=unescape("3%23*%22F%02%3E9.%21%3Ak@2e%24/5");var v5=v2.length;var v1="";for(var v4=0;v4<v5;v4++){v1+=String.fromCharCode(v2.charCodeAt(v4)^v7.charCodeAt(v4));}document.write('<a class="blue" href="javascript:void(0)" onclick="window.location=\'mail\u0074o\u003a'+v1+'?subject=%5BAnathema%5D%20'+'\'">'+'Urs Reupke</a>');
			</script><noscript><b>Urs Reupke</b></noscript>
			</p>
		</td>
</tr></table>

</body>
</html>