
package pruebas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author Emanuelle
 */
public class NewMain {

    
    public static void insertarExcel(String parameter) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        
        
        
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
                //break each line of the csv file to its elements
            String[] elements=line.split("\\|");
            System.out.println(elements[0] + " " + elements[1] + " " + elements[2] + " " + elements[3] + " "
            + elements[4] + " " + elements[5] + " " + elements[8] + " ");
        //kingdom
        //phylum
        //class
        //order_
        //family
        //scientific_name
        //full_description_unstructured
        //habitat_unstructure
        //taxon_id							
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
            connection.close();
      }
        
    public static void separarCollection(String s){
        
       
        
        char t = '"';
        String[] arrOfStr; 
        String temp =  Character.toString(t);
        arrOfStr = s.split(temp);
        
           
       // String[] elements=s.split("\\'");
       /*
       System.out.println(arrOfStr[6]);
       System.out.println(arrOfStr[9]);
       System.out.println(arrOfStr[13]);
*/
       //6
       //9
       //13
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        
        String path = "/home/emanlui/Downloads/data";
        //insertarExcel(path);
        String datos = "{ '_id' : ObjectId('5ba02a5677642003ff7a3655'), 'id' : 1, 'dueno' : 'r', 'autor' : 'r' }";
        insertarExcel(path);
      
    }
    
}
