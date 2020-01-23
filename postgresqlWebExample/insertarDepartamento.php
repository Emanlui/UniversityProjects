<!DOCTYPE html>
<html>
<body>


<?php 

	$nombre = $_GET["nombre"];
	$desc = $_GET["descripcion"];
	$num = (int)$_GET["telefono"];
	
	if(strpos($busqueda, 'select') !== FALSE || strpos($busqueda, 'from')!== FALSE || strpos($busqueda, 'update') !== FALSE || strpos($busqueda, 'delete') !== FALSE ){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");
	
	
	$consulta="INSERT INTO departamento(nombre_departamento,descripcion,numerotelefonico) VALUES ('$nombre','$desc','$num')";
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