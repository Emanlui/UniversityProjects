/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.eclipse.jdt.internal.compiler.ast.Statement;


/**
 *
 * @author Emanuelle
 */
public class ConexionOracle {

    Connection connection;
    
    public void conectar() throws ClassNotFoundException, SQLException {
       

            Class.forName("oracle.jdbc.driver.OracleDriver");  
  
        
            Connection connection=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");  

            
    }

    public void insertarExcel(String parameter) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
          
        Class.forName("oracle.jdbc.driver.OracleDriver");  
  
        
            Connection connection=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");  
            
        BufferedReader bfr = new BufferedReader(new FileReader(parameter));
        String  line = "";
        int cont = 0;
        String querry = "INSERT INTO tablaTemp VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt1 = connection.prepareStatement(querry);
        while((line = bfr.readLine()) != null){
            if(cont != 0){
               
                String[] elements=line.split("\\|");
        				
                if(elements[6].length() > 3999){
                elements[6] = "";
                }

                pstmt1.setString(1, elements[0]);
                pstmt1.setString(2, elements[1]);
                pstmt1.setString(3, elements[2]);
                pstmt1.setString(4, elements[3]);
                pstmt1.setString(5, elements[4]);
                pstmt1.setString(6, elements[5]);
                pstmt1.setString(7, elements[6]);
                pstmt1.setString(8, elements[7]);

                pstmt1.executeUpdate();

        
            }
            cont  = cont + 1;
            }

      }

    public void insertarStatus(int id_esp, String nombre) throws SQLException, ClassNotFoundException{
        
            Class.forName("oracle.jdbc.driver.OracleDriver");  
  
        
            Connection connections =DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13"); 
            
        
            String querry = "INSERT INTO status VALUES (?,?)";
            
            PreparedStatement pstmt1 = connections.prepareStatement(querry);
            
            pstmt1.setInt(1, id_esp);
            pstmt1.setString(2, nombre);
            pstmt1.executeUpdate();
         
        
    }
    public void insertarSpeciesPage(int id, String descripcion, String cientifico) throws SQLException, ClassNotFoundException{
        
        
            Class.forName("oracle.jdbc.driver.OracleDriver");  
  
        
            Connection connectionisp =DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13"); 
            
            String querry = "INSERT INTO species_page (id_esp,descripcion,cientifico) VALUES (?,?,?)";
            
            PreparedStatement psSP = connectionisp.prepareStatement(querry);
            
            psSP.setInt(1, id);
            psSP.setString(2, descripcion);
            psSP.setString(3, cientifico);
            psSP.executeUpdate();
            
        
        
    }
    
    
    public String getEspecie(int id) throws SQLException, ClassNotFoundException{
        
        String resultado;
       
  
        
            Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13"); 
        
        String querry = "SELECT nombre"
                 + "      From  especie"
                 + "      Where id_esp = ?;";
        
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        resultado = rset.getString(1);
        
        return getGenero(resultado,id);
    }
    
    public String getGenero(String resultado,int id) throws SQLException, ClassNotFoundException{
        
        int id_temp;
         
        Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
            
        String querry = "SELECT nombre,id_gen "
                 + "      From genero"
                 + "      Where id_esp = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        resultado += " -> " + rset.getString(1);
        id_temp = Integer.parseInt(rset.getString(2));
        
        return getFamilia(resultado, id_temp);
    }
    
    public String getFamilia(String resultado, int id) throws SQLException, ClassNotFoundException{
        
        int id_temp;
        
         Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
        
        String querry = "SELECT nombre,id_fam"
                 + "      From familia"
                 + "      Where id_gen = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        resultado += " -> " + rset.getString(1);
        id_temp = Integer.parseInt(rset.getString(2));
        
        return getOrden(resultado, id_temp);
    }
    
    public String getOrden(String resultado, int id) throws SQLException, ClassNotFoundException{
        
        int id_temp;
        
         Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
            
        String querry = "SELECT nombre,id_orden"
                 + "      From orden"
                 + "      Where id_fam = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        resultado += " -> " + rset.getString(1);
        id_temp = Integer.parseInt(rset.getString(2));
        
        return getClase(resultado, id_temp);
    }
    
    public String getClase(String resultado, int id) throws SQLException, ClassNotFoundException{
        
        int id_temp;
        
         Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
            
        String querry = "SELECT nombre,id_clase"
                 + "      From clase"
                 + "      Where id_orden = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        resultado += " -> " + rset.getString(1);
        id_temp = Integer.parseInt(rset.getString(2));
        
        return getFilo(resultado, id_temp);
    }
    
    public String getFilo(String resultado, int id) throws SQLException, ClassNotFoundException{
        
        int id_temp;
        
         Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
        
        String querry = "SELECT nombre,id_filo"
                 + "      From filo"
                 + "      Where id_clase = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        resultado += " -> " + rset.getString(1);
        id_temp = Integer.parseInt(rset.getString(2));
        
        return getReino(resultado, id_temp);
    }
    
    public String getReino(String resultado, int id) throws SQLException, ClassNotFoundException{
        
        int id_temp;
        
         Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
        
        String querry = "SELECT nombre"
                 + "      From reino"
                 + "      Where id_filo = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        resultado += " -> " + rset.getString(1);
       
        
        return resultado;
    }
    
    public void actualizar(int id_esp, String cientifico, String descripcion, String nombre) throws SQLException{
        
        if(!cientifico.equals("")){
            String querry = "UPDATE species_page SET " + cientifico + " WHERE id_esp = " + Integer.toString(id_esp) + ";";
            PreparedStatement ps = connection.prepareStatement(querry);
            ps.executeUpdate();
        }
        if(!descripcion.equals("")){
            String querry = "UPDATE species_page SET " + descripcion + " WHERE id_esp = " + Integer.toString(id_esp) + ";";
            PreparedStatement ps = connection.prepareStatement(querry);
            ps.executeUpdate();
        }
        if(!nombre.equals("")){
            String querry = "UPDATE status SET nombre = " + nombre + " WHERE id_esp = " + Integer.toString(id_esp) + ";";
            PreparedStatement ps = connection.prepareStatement(querry);
            ps.executeUpdate();
        }
        
    
    }
            
    
    public ResultSet obtenerDatos() throws SQLException{
         String querry = "SELECT s.nombre,ss.cientifico,ss.descripcion "
                 + "      From s status, ss species_page e especie"
                 + "      Where e.id_esp = s.id_esp AND e.id_esp = ss.id_esp " ;
       
        PreparedStatement pstmt1 = connection.prepareStatement(querry);
        ResultSet rset = pstmt1.executeQuery();
       
        /*
        while (rs.next()) {
            String nombre = rs.getString(1);
            String cientifico = rs.getString(2);
            String descripcion = rs.getString(3);
            System.out.println(nombre + "\t" + cientifico +
                               "\t" + descripcion);
        }
        */
        return rset;
    }

    public String getEstado(int id) throws ClassNotFoundException, SQLException{
        int id_temp;
        
         Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
        
        String querry = "SELECT nombre"
                 + "      From status"
                 + "      Where id_filo = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        String resultado = rset.getString(1);
       
        return resultado;
        
    }
    
      public String getSpecies_page(int id) throws ClassNotFoundException, SQLException{
        int id_temp;
        
         Class.forName("oracle.jdbc.driver.OracleDriver");  
  
            Connection connection1=DriverManager.getConnection(  
            "jdbc:oracle:thin:@172.28.138.13:1521:bd2","usuario13","usuario13");
        
        String querry = "SELECT descripcion"
                 + "      From species_page"
                 + "      Where id_filo = ?;" ;
       
        PreparedStatement pstmt1 = connection1.prepareStatement(querry);
        pstmt1.setInt(1,id);
        ResultSet rset = pstmt1.executeQuery();
        
        String resultado = rset.getString(1);
       
        return resultado;
        
    }
    
    public void llamarTriggerExcel() throws SQLException {
    
         CallableStatement cStmt = connection.prepareCall("{call proceso_datos()}");
         
    }
    
}
