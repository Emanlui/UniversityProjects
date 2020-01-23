package same;

import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class JugarInterfaz extends Application implements EventHandler<ActionEvent>{
    
	/**
     * @class JugarInterfaz
     * @brief Clase encargada de mostrar en pantalla el juego y sus cambios
     */

	
    Stage ventana;
    Scene escena;
    JugarLogica Lo = new JugarLogica();
    
    public Image[][] arreglo_logico = new Image[10][12];
    public Button[][] arreglo_interfaz = new Button[10][12];
    
    public void start(Stage ventana, int nivel) throws Exception {
        
        /**
         * @brief Este es el método para que se despliegue la pantalla
         * @param ventana El stage del programa
         * @param nivel El nivel del juego
         */
    	
        this.ventana = ventana;
        ventana.setTitle("~ Same ~"); //titulo
        GridPane gridpane = new GridPane();

        int tamano = 0;
        
        //for(int i = 0; i < 10; i++){
	    //    for(int j = 0; j < 12; j++){
	    //    	System.out.print( j + "" + i + " ");
	    //    }
	    //    System.out.println("");
        //}
        
        if(nivel == 1){
            
            tamano = 3;
        }
        else if(nivel == 2){
        	
            tamano = 4;
        }
        else{
        	
            tamano = 5;
        }
        
        
        Image rojoI = new Image(getClass().getResourceAsStream("rojo.png"));
        Image azulI = new Image(getClass().getResourceAsStream("azul.png"));
        Image verdeI = new Image(getClass().getResourceAsStream("verde.png"));
        Image amarilloI = new Image(getClass().getResourceAsStream("amarillo.png"));
        Image lilaI = new Image(getClass().getResourceAsStream("lila.png"));
      
        
        Image[] imagenes = new Image[6];
        
        imagenes[0] = rojoI;
        imagenes[1] = azulI;
        imagenes[2] = verdeI;
        imagenes[3] = amarilloI;
        imagenes[4] = lilaI;
        
        for(int i = 0; i < 10; i++){
        	
        	for(int j = 0; j < 12; j++){
        		
        			Random rand = new Random();
        			int n = rand.nextInt(tamano);
                    
                    final int x = i;
                    final int y = j;
                    
                    
                    
                    arreglo_logico[i][j] = imagenes[n];
                    arreglo_interfaz[i][j] = new Button();
                    arreglo_interfaz[i][j].setStyle("-fx-background-color: #FFFFFF");
                    arreglo_interfaz[i][j].setGraphic(new ImageView(imagenes[n]));
                    arreglo_interfaz[i][j].setPrefHeight(50);
                    arreglo_interfaz[i][j].setPrefWidth(50);
                    
                    arreglo_interfaz[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    	
                    @Override
                    public void handle(ActionEvent event) {
                        	
                    //System.out.println("Usted acaba de apretar el boton: " + x + " " + y);      
                    //System.out.println("Color: " + arreglo_logico[x][y]);
                            
                    int[][]adyacentes = new int [10][12];
                    adyacentes = Lo.NuevoArreglo(arreglo_logico,adyacentes,x,y,arreglo_logico[x][y]);
                         
                    if(Lo.VerificarArreglo(adyacentes) == true){
                              
	                    arreglo_logico = Lo.EliminarAdyacentes(adyacentes, arreglo_logico);
	                               
	                    arreglo_logico = Lo.UnirBotonesVertical(arreglo_logico);
	                    arreglo_logico = Lo.UnirBotonesHorizontal(arreglo_logico);
	                    arreglo_logico = Lo.UnirBotonesHorizontal(arreglo_logico);
	                                
	                    CambiarEstadoBotones(arreglo_logico);
	                    MostrarCambio(arreglo_logico,arreglo_interfaz);
                    }          
                    if(Lo.VerificarPartida(arreglo_logico) == 1){
                                	
	                    new Alert(Alert.AlertType.INFORMATION, "Ha ganado, felicidades. :)").showAndWait();
	                                   
	                    Menu i = new Menu();
	                                    
	                    try {
	                                 	
	                    	i.start(ventana);
	                    } 
	                    catch (Exception ex) {
	                                    	
	                    	Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
	                    }
                    }
                    else if(Lo.VerificarPartida(arreglo_logico) == -1){
                                	
	                    new Alert(Alert.AlertType.INFORMATION, "Lo sentimos, perdio. :(").showAndWait();
	                                    
	                    Menu i = new Menu();
	                    try {
	                    	
	                    	i.start(ventana);
	                    }
	                    catch (Exception ex) {
	                    	
	                    	Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
	                    }
                    }
                    }  
                    });
                    gridpane.add(arreglo_interfaz[i][j], j,i);
        	        }
                }
        escena = new Scene(gridpane, 700, 500);
        ventana.setScene(escena);          // inicializacion de la escena1
        ventana.show();
        this.ventana.setFullScreen(true);
    }
    
    public void CambiarEstadoBotones(Image[][]logico){
      

        /**
         * @brief Cambia las imágenes de la matriz de interfacez al nuevo del lógico
         * @param matriz Arreglo lógico que será el encargado de los cambios 
         */
    	
	    for(int i = 0; i < 10;i++){
		    for(int j = 0; j < 12;j++){
		      
		    	arreglo_interfaz[i][j].setGraphic(new ImageView(logico[i][j]));    
		    }
	    }
    }  
    
	public void MostrarCambio(Image[][] logico, Button[][] botones){
	      
		/**
         * @brief Muestra los cambios en pantalla del arreglo de botones
         * @param logico Matriz con las imágenes
         * @param botones Matriz con los botones
         */
		
	    for(int i = 0; i < 10;i++){
	    	
	        for(int j = 0; j < 12;j++){
	        	
	            if(logico[i][j] == null){
	            	Image beigeI = new Image(getClass().getResourceAsStream("beige.png"));
	                botones[i][j].setGraphic(new ImageView(beigeI)); 
	                botones[i][j].setDisable(true);
	            }
	            if(logico[i][j] != null && botones[i][j].isDisabled() == true){
	              
	            	botones[i][j].setDisable(false);
	            }
	        }
	    }
	}  

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		 /**
         * @brief No hace nada
         */
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		 /**
         * @brief No hace nada
         */
		
	}
}
