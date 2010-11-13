<?php

	if (isset($_REQUEST['page'])) $page = $_REQUEST['page'];
	else $page = 'news';

?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Anathema: A Nascent Approach To Harmonic Exalted MAnagement</title>
</head>

<body>

<h1>Anathema - <?php echo ucwords($page); ?></h1>

<?php include($page.'.htm') ?>

</body>
</html>