<%-- 
    Document   : ActualizarVista
    Created on : 15-Sep-2018, 02:28:31
    Author     : Emanuelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <table border="0" width="1000" align="center">
            <tr bgcolor="skyblue">
                <th><a href="index.jsp">Menu</a></th>
                <th><a href="InsertarVista.jsp">Insertar</a></th>
                <th><a href="buscarVista.jsp">Buscar</a></th>
                <th><a href="EliminarVista.jsp">Eliminar</a></th>
                <th><a href="ActualizarVista.jsp">Actualizar</a></th>
            </tr>
        </table>
        <br><br>
        <div class="formulario" align="center">
            <form  action="ControladorWeb" method="post">
                Id: <input name="id" /> <br><br>
                Autor: <input name="autor" /><br><br>
                Dueno: <input name="dueno" /><br><br>
                Descipcion: <input name="Descripcion" /><br><br>
                Cientifico: <input name="cientifico" /><br><br>
                <input type="text" name="imgInfo"><br><br>
                <input type="submit" name="accion" value="Actualizar"/><br><br>
            </form>
        </div>
        
    </body>
</html>
