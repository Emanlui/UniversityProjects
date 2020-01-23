<!DOCTYPE html>
<html>
<body>

<?php 

	$cod = (int)$_GET["cod"];
	$enc = $_GET["enc"];
	$can = $_GET["can"];

	if(strpos($cod, 'select') !== FALSE || strpos($cod, 'from')!== FALSE || strpos($cod, 'update') !== FALSE || strpos($cod, 'delete') !== FALSE ||
		strpos($enc, 'select') !== FALSE || strpos($enc, 'from')!== FALSE || strpos($enc, 'update') !== FALSE || strpos($enc, 'delete') !== FALSE ||
		strpos($can, 'select') !== FALSE || strpos($can, 'from')!== FALSE || strpos($can, 'update') !== FALSE || strpos($can, 'delete') !== FALSE){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");
	
	if (isset($_GET['checkNumero'])) {
		$consulta= "UPDATE sucursal SET nombreencargado = '$enc' WHERE codigo_sucursal = '$cod'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del numero";
		}else{
		echo "Numero actualizado";
		}
	} 

	if (isset($_GET['checkDir'])) {
		$consulta= "UPDATE sucursal SET canton = '$can' WHERE codigo_sucursal = '$cod'";
		
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