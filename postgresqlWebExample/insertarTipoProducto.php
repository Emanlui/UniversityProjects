<!DOCTYPE html>
<html>
<body>


<?php 

	$codigo = (int)$_GET["codigo"];
	$descripcion = $_GET["descripcion"];
	$marca = $_GET["marca"];

	if(strpos($busqueda, 'select') !== FALSE || strpos($busqueda, 'from')!== FALSE || strpos($busqueda, 'update') !== FALSE || strpos($busqueda, 'delete') !== FALSE ){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");
	
	$consulta="INSERT INTO tipoproducto(codigo_tipoproducto,descripcion,marca) VALUES ('$codigo','$descripcion','$marca')";
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