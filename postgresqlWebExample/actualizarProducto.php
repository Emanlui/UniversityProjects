<!DOCTYPE html>
<html>
<body>

<?php 

	$codigo_producto = $_GET["codigo_producto"];
	$nombre = $_GET["nombre"];
	$precio = (int)$_GET["precio"];
	
	if(strpos($codigo_producto, 'select') !== FALSE || strpos($codigo_producto, 'from')!== FALSE || strpos($codigo_producto, 'update') !== FALSE || strpos($codigo_producto, 'delete') !== FALSE || 
		strpos($nombre, 'select') !== FALSE || strpos($nombre, 'from')!== FALSE || strpos($nombre, 'update') !== FALSE || strpos($nombre, 'delete') !== FALSE ||
		strpos($precio, 'select') !== FALSE || strpos($precio, 'from')!== FALSE || strpos($precio, 'update') !== FALSE || strpos($precio, 'delete') !== FALSE){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
	
    require("conexion.php");
	
	 
	if (isset($_GET['checkNombre'])) {
		$consulta= "UPDATE producto SET nombre = '$nombre' WHERE codigo_producto = '$codigo_producto'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del nombre";
		}else{
		echo "Nombre actualizado";
		}
	
	} 
	
	if (isset($_GET['checkPrecio'])) {
		$consulta= "UPDATE producto SET precio = '$precio' WHERE codigo_producto = '$codigo_producto'";
		
		$resultado=pg_query($conexion,$consulta);
		if($resultado == false){
		echo "Error en la actualizacion del precio";
		}else{
		echo "Precio actualizado";
		}
	
	} 
	pg_close($conexion);
	}
 ?>

</body>
</html>