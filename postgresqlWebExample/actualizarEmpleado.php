<!DOCTYPE html>
<html>
<body>

<?php 

	$nombre = $_GET["nombre"];
	$FN = $_GET["FN"];
	$dir = $_GET["dir"];
	$salario = $_GET["salario"];
	$cedula = $_GET["cedulaE"];
    $telefono = $_GET["telefono"];

	if(strpos($nombre, 'select') !== FALSE || strpos($nombre, 'from')!== FALSE || strpos($nombre, 'update') !== FALSE || strpos($nombre, 'delete') !== FALSE ||
		strpos($FN, 'select') !== FALSE || strpos($FN, 'from')!== FALSE || strpos($FN, 'update') !== FALSE || strpos($FN, 'delete') !== FALSE ||
		strpos($dir, 'select') !== FALSE || strpos($dir, 'from')!== FALSE || strpos($dir, 'update') !== FALSE || strpos($dir, 'delete') !== FALSE ||
		strpos($salario, 'select') !== FALSE || strpos($salario, 'from')!== FALSE || strpos($salario, 'update') !== FALSE || strpos($salario, 'delete') !== FALSE ||
		strpos($cedula, 'select') !== FALSE || strpos($cedula, 'from')!== FALSE || strpos($cedula, 'update') !== FALSE || strpos($cedula, 'delete') !== FALSE ||
		strpos($telefono, 'select') !== FALSE || strpos($telefono, 'from')!== FALSE || strpos($telefono, 'update') !== FALSE || strpos($telefono, 'delete') !== FALSE){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");

	if (isset($_GET['checkNombre'])) {
		$consulta= "UPDATE empleado SET nombre = '$nombre' WHERE cedula_empleado = '$cedula'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del nombre";
		}else{
		echo "Nombre actualizado";
		}
	
	} 
	if (isset($_GET['checkFecha'])) {
		$consulta= "UPDATE empleado SET fechanacimiento = '$FN' WHERE cedula_empleado = '$cedula'";
		
		$resultado=pg_query($conexion,$consulta);
		
		if($resultado == false){
		echo "Error en la actualizacion de la fecha";
		}else{
		echo "Fecha actualizada";
		}
		
	} 
	if (isset($_GET['checkDir'])) {
		$consulta= "UPDATE empleado SET direccion = '$dir' WHERE cedula_empleado = '$cedula'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion de la dirección";
		}else{
		echo "Direccion actualizada";
		}
	
	} 
	
 
	if (isset($_GET['checkSalario'])) {
		$consulta= "UPDATE empleado SET salario = '$salario' WHERE cedula_empleado = '$cedula'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del salario";
		}else{
		echo "Salario actualizado";
		}
		
	} 
 
	if (isset($_GET['checkTelefono'])) {
		$consulta= "UPDATE empleado SET telefono = '$telefono' WHERE cedula_empleado = '$cedula'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del telefono";
		}else{
		echo "Telefono actualizado";
		}
		
	} 
	 pg_close($conexion);
	}

 ?>

</body>
</html>
