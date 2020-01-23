<%-- 
    Document   : Menu
    Created on : Sep 24, 2018, 9:06:09 AM
    Author     : emanlui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        
        
        String d = (String) request.getAttribute("d");
       
        if(d != null){
            
            out.println(d);
        }
        
        %>
    </body>
</html>
