<!DOCTYPE html>
<html>
<body>

<?php

	$dbhost="localhost";
	$dbusuario="postgres";
	$port="5432";
	$base="super";
	$contrasena="0/+bzUXy8B9hykLKmyjga0S+1fPUSWcmQu7I697qA/4=";
	
	$cryptKey  = 'qJB0rGtIn5UB1xG03efyCp';
    $contrasenaDecodificada      = rtrim( mcrypt_decrypt( MCRYPT_RIJNDAEL_256, md5( $cryptKey ), base64_decode( $contrasena ), MCRYPT_MODE_CBC, md5( md5( $cryptKey ) ) ), "\0");
	
	$conexion= pg_connect("host=".$dbhost." port=".$port." dbname=".$base." user=".$dbusuario." password=".$contrasenaDecodificada."");

?>

</body>
</html>