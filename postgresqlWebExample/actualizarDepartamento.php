<!DOCTYPE html>
<html>
<body>

<?php 

	$nombre = $_GET["nombre"];
	$desc = $_GET["desc"];
	$num = (int)$_GET["num"];
	
	if(strpos($nombre, 'select') !== FALSE || strpos($nombre, 'from')!== FALSE || strpos($nombre, 'update') !== FALSE || strpos($nombre, 'delete') !== FALSE ||
		strpos($desc, 'select') !== FALSE || strpos($desc, 'from')!== FALSE || strpos($desc, 'update') !== FALSE || strpos($desc, 'delete') !== FALSE ||
		strpos($num, 'select') !== FALSE || strpos($num, 'from')!== FALSE || strpos($num, 'update') !== FALSE || strpos($num, 'delete') !== FALSE){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");


	if (isset($_GET['checkDir'])) {
		$consulta= "UPDATE departamento SET descripcion = '$desc' WHERE nombre_departamento = '$nombre'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del direccion";
		}else{
		echo "Direccion actualizado";
		}
	}
	
	if (isset($_GET['checktel'])) {
		$consulta= "UPDATE departamento SET numerotelefonico = '$num' WHERE nombre_departamento = '$nombre'";
		
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