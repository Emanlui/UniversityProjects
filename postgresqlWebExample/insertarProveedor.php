<!DOCTYPE html>
<html>
<body>


<?php 

	$nombre = $_GET["nombre"];
	$numero = (int)$_GET["numero"];
	$dir = $_GET["dir"];
	$codigoDelTipo = $_GET['codigoDelTipo'];

	
	if(strpos($nombre, 'select') !== FALSE || strpos($nombre, 'from')!== FALSE || strpos($nombre, 'update') !== FALSE || strpos($nombre, 'delete') !== FALSE ||
	   strpos($numero, 'select') !== FALSE || strpos($numero, 'from')!== FALSE || strpos($numero, 'update') !== FALSE || strpos($numero, 'delete') !== FALSE ||
	   strpos($dir, 'select') !== FALSE || strpos($dir, 'from')!== FALSE || strpos($dir, 'update') !== FALSE || strpos($dir, 'delete') !== FALSE ||
	   strpos($codigoDelTipo, 'select') !== FALSE || strpos($codigoDelTipo, 'from')!== FALSE || strpos($codigoDelTipo, 'update') !== FALSE || strpos($codigoDelTipo, 'delete') !== FALSE ){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
		
		require("conexion.php");
		
		
		$consulta="INSERT INTO proveedor(nombre_proveedor,numero,ubicacion) VALUES ('$nombre','$numero','$dir')";
		$resultado=pg_query($conexion,$consulta);
		
		if($resultado == false){
			echo "Error en la inserción";
		}else{
			echo "Insercion exitosa.";
		}
		
		$consulta="INSERT INTO proveedortipoproducto(nombre_proveedor,codigo_tipoproducto) VALUES ('$nombre','$codigoDelTipo')";
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