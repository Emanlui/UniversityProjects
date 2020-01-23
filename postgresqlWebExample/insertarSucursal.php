<!DOCTYPE html>
<html>
<body>


<?php 

	$cod = (int)$_GET["codigo"];
	$enc = $_GET["encargado"];
	$can = $_GET["canton"];

	if(strpos($busqueda, 'select') !== FALSE || strpos($busqueda, 'from')!== FALSE || strpos($busqueda, 'update') !== FALSE || strpos($busqueda, 'delete') !== FALSE ){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");
	
	
	$consulta="INSERT INTO sucursal(codigo_sucursal,nombreencargado,canton) VALUES ('$cod','$enc','$can')";
	$resultado=pg_query($conexion,$consulta);
	
	if($resultado == false){
		echo "Error en la inserción";
	}else{
		echo "Insercion exitosa.";
	}
	pg_close($conexion);
	}
 ?>

</body>
</html>