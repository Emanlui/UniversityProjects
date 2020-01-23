<!DOCTYPE html>
<html>
<body>

<?php 

	$busqueda = $_GET["busqueda"];
	
	if(strpos($busqueda, 'select') !== FALSE || strpos($busqueda, 'from')!== FALSE || strpos($busqueda, 'update') !== FALSE || strpos($busqueda, 'delete') !== FALSE ){
		$msg = "SQL INJECTION detectado";
		echo "<script type='text/javascript'>alert('$msg');</script>";
	}else{
		
		require("conexion.php");

		$consulta="DELETE FROM producto WHERE codigo_producto = '$busqueda'";
		$resultado=pg_query($conexion,$consulta);
		
		if($resultado == false){
			echo "Error en la eliminacion";
		}else{
			echo"Eliminado correctamente";
		}pg_close($conexion);
	
	}
	
	
	
 ?>

</body>
</html>
