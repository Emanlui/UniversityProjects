/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos;

import java.util.Scanner;

/**
 *
 * @author Emanuelle
 */
public class datosUsuario {
    
    String []datos;
    int cantidadHilos;
    int cantidadRepeticiones;
    String url;
        
    /**
     * @brief Constructor vacio
     */
    public datosUsuario(){
    
        datos = null;
        cantidadHilos = 0;
        cantidadRepeticiones = 0;
        url = null;
        
    }
    
    /**
     * @brief inserta los datos a los atributos con el arreglo de Strings
     */
    public  void insertarDatos(){
        
        cantidadHilos = Integer.parseInt(datos[2]);
        cantidadRepeticiones = Integer.parseInt(datos[4]); 
        url = datos[6];
                
    }
    
    /**
     * @brief Solicita los datos al usuario, en caso de error vuelve a llamarse hasta que no se encuentren errores en el comando
     */
    public void pedirDatos(){
        
        try{
            Scanner sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            datos = separarRespuesta(respuesta);
            insertarDatos();
        }catch(Exception e){
             pedirDatos();
        }
        
        
    }
    
    /**
     * 
     * @param respuesta El comando especificado por el usuario 
     * @return Un arreglo de Strings con los campos del commando
     */
    public String[] separarRespuesta(String respuesta) {
       
        //stress -n <cantidad-hilos> -r <cantidad de repeticiones> -u <url en el dominio.onion>
        //stress -n 1 -r 1 -u https://www.facebookcorewwwi.onion
        String[] partes = respuesta.split(" ");
        return partes;
        
    }
}
