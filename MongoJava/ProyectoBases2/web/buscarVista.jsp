<%-- 
    Document   : buscarVista
    Created on : 15-Sep-2018, 02:17:40
    Author     : Emanuelle
--%>

<%@page import="java.util.List"%>
<%@page import="modelo.ConexionMongo"%>
<%@page import="com.mongodb.DBObject"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
    <form method="get">
         <table border="0" width="1000" align="center">
            <tr bgcolor="skyblue">
                <th><a href="index.jsp">Menu</a></th>
                <th><a href="InsertarVista.jsp">Insertar</a></th>
                <th><a href="buscarVista.jsp">Buscar</a></th>
                <th><a href="EliminarVista.jsp">Eliminar</a></th>
                <th><a href="ActualizarVista.jsp">Actualizar</a></th>
            </tr>
        </table>
    </form>
    
    <form  action="ControladorWeb" method="post">
        <div class="input" align="center">
        <br>
        <br>
        <input name="busqueda" />
        <input type="submit" name="accion" value="BuscarE"/>
        <input type="submit" name="accion" value="BuscarA"/>
        <br>
        <br>    
        <label for="estado">El estado de la especie es: </label>
        <br>
         <%
            String resEst = (String) request.getAttribute("estadoRes");
                        if(resEst != null){
                            out.println(resEst);
                        }
                        
        %>
        <br>
        <label for="descripcion"> Descipcion: </label>
        <br>
        <%
            String resDes = (String) request.getAttribute("descripcionRes");
                        if(resDes != null){
                            out.println(resDes);
                        }
                        
        %>
        <br>
        <label for="arbol"> Arbol jerarquico: </label>
        <br>
        <br>
        <%
            String res = (String) request.getAttribute("datoArbol");
                        if(res != null){
                            out.println(res);
                        }
                        
        %>
           <br><br> <div id="resultado">
                <div id="tablaizq" align="center">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Autor</th>
                        <th>Dueno</th>
                        <th>Imagen</th>
                        
                    </tr>
                    <%
                        
                        DBCursor cursor;

                        cursor = (DBCursor) request.getAttribute("datoDeLaBusqueda");
                        ConexionMongo cm = new ConexionMongo();
                        if(cursor != null){
                            while(cursor.hasNext()) {

                                //int idMongo = (int) cursor.next().get("id");
                                /*
                                String autorMongo = (String) dbo.get("autor");
                                String duenoMongo = (String) dbo.get("dueno");
                                */
                                String b = cursor.next().toString();
                                String [] colleccionSeparada = cm.separarCollection(b);
                                out.println("<tr>");
                                out.println("<th>");
                                out.println(cm.cortarString(colleccionSeparada[8]));
                           
                                out.println("</th>");
                                out.println("<th>");
                                out.println(colleccionSeparada[11]);
                           
                                out.println("</th>");
                                out.println("<th>");
                                out.println(colleccionSeparada[15]);
                           
                                out.println("</th>");
                                
                               
                                out.println("<th>");
                                out.println("<img src= " + colleccionSeparada[19] + "alt='ejemplo' >");
                              
                                out.println("</th>");
                                
                                out.println("</tr>");
                                /*
                                out.println("<td>");
                                out.println(idMongo);
                                out.println("</td>");
                                out.println("<td>");
                                out.println(duenoMongo);
                                out.println("</td>");*/
                                
                                }
                        }
                            
                        %>
                        
                        <tr>
                        </table>
                        </div>
                        
            </div>

        </div>
            
    </form>    
        
    </body>
</html>
