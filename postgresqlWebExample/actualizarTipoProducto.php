<!DOCTYPE html>
<html>
<body>

<?php 

	$cod = (int)$_GET["cod"];
	$desc = $_GET["desc"];
	$marca = $_GET["marca"];
	
	if(strpos($cod, 'select') !== FALSE || strpos($cod, 'from')!== FALSE || strpos($cod, 'update') !== FALSE || strpos($cod, 'delete') !== FALSE ||
		strpos($desc, 'select') !== FALSE || strpos($desc, 'from')!== FALSE || strpos($desc, 'update') !== FALSE || strpos($desc, 'delete') !== FALSE ||
		strpos($marca, 'select') !== FALSE || strpos($marca, 'from')!== FALSE || strpos($marca, 'update') !== FALSE || strpos($marca, 'delete') !== FALSE){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{

    require("conexion.php");
	
	if (isset($_GET['checkNumero'])) {
		$consulta= "UPDATE tipoproducto SET descripcion = '$desc' WHERE codigo_tipoproducto = '$cod'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del numero";
		}else{
		echo "Numero actualizado";
		}
	} 

	if (isset($_GET['checkDir'])) {
		$consulta= "UPDATE tipoproducto SET marca = '$marca' WHERE codigo_tipoproducto = '$cod'";
		
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