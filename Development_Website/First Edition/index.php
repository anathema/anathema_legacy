<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>Anathema - A nascent approach to harmonic Exalted management</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta name="description" content="Anathema - An open source Exalted Toolkit">
		<meta name="keywords" content="Anathema, Exalted, White Wolf, character generator, campaign management">
		<meta name="robots" content="index">
		<meta name="author" content="Urs Reupke">
		<link rel="stylesheet" type="text/css" href="anathema.css">
		<link rel="shortcut icon" href="favicon.ico">
		<script src="anathema_menu.js" type="text/javascript"></script>
	</head>
	<?php 
			if (isset($_GET['content'])) {
				$content=$_GET['content']; 
			}
			else {
				$content="home";
			};
			if (isset($_GET['language'])) {
				$language=$_GET['language'];
			}
			else {
			    $language="en";
			}
	?>
	<body onload="register(2, 'home'); register(4, 'features'); register (6, 'faq'); register (8,'screenshots'); register(10,'project'); register (12,'download'); register(14, 'links'); register(16, 'contact'); register(18, 'forum');setSelectedStatus('<?php echo($content) ?>');">		
		<div class="head">
			<a href="http://anathema.sf.net">
				<img id="headimg" src="images/head.gif" alt="ANATHEMA" />
			</a>
		</div>

		<div class="navigation">				
			<table id="menutable" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a href="index.php?content=home&language=<?php echo $language ?>" onmouseover="hover(2)" onmouseout="unhover(2)">
							<img class="menuimg" src="images/u_home.gif" alt="Home" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a href="index.php?content=features&language=<?php echo $language ?>" onmouseover="hover(4)" onmouseout="unhover(4)">
							<img class="menuimg" src="images/u_features.gif" alt="Features" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a href="index.php?content=faq&language=<?php echo $language ?>" onmouseover="hover(6)" onmouseout="unhover(6)">
							<img class="menuimg" src="images/u_faq.gif" alt="FAQ" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a href="index.php?content=screenshots&language=<?php echo $language ?>" onmouseover="hover(8)" onmouseout="unhover(8)">
							<img  class="menuimg" src="images/u_screenshots.gif" alt="Screenshots" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a href="http://sf.net/projects/anathema" onmouseover="hover(10)" onmouseout="unhover(10)">
							<img class="menuimg" src="images/u_project.gif" alt="Project Page" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a href="index.php?content=download&language=<?php echo $language ?>" onmouseover="hover(12)" onmouseout="unhover(12)">
							<img class="menuimg" src="images/u_download.gif"alt="Download" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a href="index.php?content=links&language=<?php echo $language ?>" onmouseover="hover(14)" onmouseout="unhover(14)">
							<img class="menuimg" src="images/u_links.gif" alt="Links" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a  href="index.php?content=contact&language=<?php echo $language ?>" onmouseover="hover(16)" onmouseout="unhover(16)">
							<img class="menuimg" src="images/u_contact.gif" alt="Contact" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="center">
						<a  href="http://sourceforge.net/forum/?group_id=122320" onmouseover="hover(18)" onmouseout="unhover(18)">
							<img class="menuimg" src="images/u_forum.gif" alt="Forum" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="left">
						<a href="http://sourceforge.net">
							<img id="sfimg" title="Sourceforge.net" src="http://sourceforge.net/sflogo.php?group_id=122320&amp;type=3" alt="SourceForge.net Logo" />
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>
					<td align="left">
						<a href="http://www.cenqua.com/fisheye">
							<img src="http://www.cenqua.com/images/fisheyed2.gif" width="89" height="33" border="0" alt="Source Perspective by FishEye"/>
						</a>
					</td>
				</tr>
				<tr>
					<td align="left">
						<img class="statusimg" src="images/blank.gif" alt="Status" />'
					</td>										
					<td align="left">
						<a  href="index.php?content=<?php echo $content ?>&language=en">
							<img src="images/flags/gbr.png"/>
						</a>	
						<a  href="index.php?content=<?php echo $content ?>&language=es">
							<img src="images/flags/esp.png"/>
						</a>											
					</td>
				</tr>
			</table>
		</div>
				
		<div class="content">						
			<?php 
				$page="./content/".$language."/".$content.".cnt";
				if (file_exists($page)){
					include($page);
				}
				else{ 
					echo ("<p>This content does not exist.<br/> Please use the menu to the left for navigation.</p>");
			}?>
		</div>
	</body>
</html>