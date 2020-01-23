<!DOCTYPE html>
<html>
<body>

<?php 

	$nombre = $_GET["nombre"];
	$codigoSucursal = $_GET["codigo"];
	$codigoS = $_GET["cedulaS"];
	$dir = $_GET["dir"];
	$genero = $_GET["gender"];
	$salario = $_GET["salario"];
	$cedula = $_GET["cedulaE"];
    $telefono = $_GET["telefono"];
	$departamento = $_GET["departamento"];
	
	$FN = $_GET["FN"];
	
	if(strpos($nombre, 'select') !== FALSE || strpos($nombre, 'from')!== FALSE || strpos($nombre, 'update') !== FALSE || strpos($nombre, 'delete') !== FALSE ||
	   strpos($codigoSucursal, 'select') !== FALSE || strpos($codigoSucursal, 'from')!== FALSE || strpos($codigoSucursal, 'update') !== FALSE || strpos($codigoSucursal, 'delete') !== FALSE ||
	   strpos($codigoS, 'select') !== FALSE || strpos($codigoS, 'from')!== FALSE || strpos($codigoS, 'update') !== FALSE || strpos($codigoS, 'delete') !== FALSE ||
	   strpos($dir, 'select') !== FALSE || strpos($dir, 'from')!== FALSE || strpos($dir, 'update') !== FALSE || strpos($dir, 'delete') !== FALSE 
	   strpos($genero, 'select') !== FALSE || strpos($genero, 'from')!== FALSE || strpos($genero, 'update') !== FALSE || strpos($genero, 'delete') !== FALSE
	   strpos($salario, 'select') !== FALSE || strpos($salario, 'from')!== FALSE || strpos($salario, 'update') !== FALSE || strpos($salario, 'delete') !== FALSE
	   strpos($cedula, 'select') !== FALSE || strpos($cedula, 'from')!== FALSE || strpos($cedula, 'update') !== FALSE || strpos($cedula, 'delete') !== FALSE
	   strpos($telefono, 'select') !== FALSE || strpos($telefono, 'from')!== FALSE || strpos($telefono, 'update') !== FALSE || strpos($telefono, 'delete') !== FALSE
	   strpos($departamento, 'select') !== FALSE || strpos($departamento, 'from')!== FALSE || strpos($departamento, 'update') !== FALSE || strpos($departamento, 'delete') !== FALSE){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
		
		require("conexion.php");

		$consulta="INSERT INTO empleado (cedulaJefe_empleado,cedula_empleado,codigo_sucursal,direccion,fechaNacimiento,nombre,salario,sexo,telefono)
		VALUES ('$codigoS','$cedula','$codigoSucursal','$dir','$FN','$nombre','$salario','$genero','$telefono')";
		$resultado=pg_query($conexion,$consulta);
		
		if($resultado == false){
			echo "Error en la inserción";
		}else{
			echo "Registro guardado";
		}
		
		$consulta="INSERT INTO departamentoempleado (cedula_empleado,nombre_departamento)
		VALUES ('$cedula','$departamento')";
		$resultado=pg_query($conexion,$consulta);
		
		if($resultado == false){
			echo "Error en la inserción";
		}else{
			echo "Registro guardado";
		}
		
		$consulta="INSERT INTO horario (cedula_empleado,horasalida,horallegada)
		VALUES ('$cedula',null,null)";
		$resultado=pg_query($conexion,$consulta);
		
		if($resultado == false){
			echo "Error en la inserción";
		}else{
			echo "Registro guardado";
		}
		
		pg_close($conexion);
	}
 ?>

</body>
</html>
