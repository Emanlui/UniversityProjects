<!DOCTYPE html>
<html>
<body>

<?php 
	$codigo_tipoproducto = $_GET["codigoTP"];
	$codigo_producto = $_GET["codigoProducto"];
	$nombre = $_GET["nombre"];
	$precio = (int)$_GET["precio"];
	
	if(strpos($codigo_tipoproducto, 'select') !== FALSE || strpos($codigo_tipoproducto, 'from')!== FALSE || strpos($codigo_tipoproducto, 'update') !== FALSE || strpos($codigo_tipoproducto, 'delete') !== FALSE ||
	   strpos($codigo_producto, 'select') !== FALSE || strpos($codigo_producto, 'from')!== FALSE || strpos($codigo_producto, 'update') !== FALSE || strpos($codigo_producto, 'delete') !== FALSE ||
	   strpos($nombre, 'select') !== FALSE || strpos($nombre, 'from')!== FALSE || strpos($nombre, 'update') !== FALSE || strpos($nombre, 'delete') !== FALSE ||
	   strpos($precio, 'select') !== FALSE || strpos($precio, 'from')!== FALSE || strpos($precio, 'update') !== FALSE || strpos($precio, 'delete') !== FALSE ){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
		
		require("conexion.php");
		
		$consulta="INSERT INTO producto(codigo_producto,codigo_tipoproducto,nombre,precio) VALUES ('$codigo_producto','$codigo_tipoproducto','$nombre','$precio')";
		$resultado=pg_query($conexion,$consulta);
		
		if($resultado == false){
			echo "Error en la inserción";
		}else{
			echo "Insercion exitosa";
		}
		pg_close($conexion);
	}
    
 ?>

</body>
</html>
