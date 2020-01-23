<!DOCTYPE html>
<html>
<body>


<?php 

	$nombre = $_GET["nombre"];
	$numero = (int)$_GET["numero"];
	$dir = $_GET["dir"];

	if(strpos($nombre, 'select') !== FALSE || strpos($nombre, 'from')!== FALSE || strpos($nombre, 'update') !== FALSE || strpos($nombre, 'delete') !== FALSE ||
		strpos($numero, 'select') !== FALSE || strpos($numero, 'from')!== FALSE || strpos($numero, 'update') !== FALSE || strpos($numero, 'delete') !== FALSE ||
		strpos($dir, 'select') !== FALSE || strpos($dir, 'from')!== FALSE || strpos($dir, 'update') !== FALSE || strpos($dir, 'delete') !== FALSE){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");
	
	
	 
	
	if (isset($_GET['checkNumero'])) {
		$consulta= "UPDATE proveedor SET numero = '$numero' WHERE nombre_proveedor = '$nombre'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del numero";
		}else{
		echo "Numero actualizado";
		}
	} 
 
	if (isset($_GET['checkDir'])) {
		$consulta= "UPDATE proveedor SET ubicacion = '$dir' WHERE nombre_proveedor = '$nombre'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del direccion";
		}else{
		echo "Direccion actualizado";
		}
	}
 
	pg_close($conexion);
	}
 ?>

</body>
</html>