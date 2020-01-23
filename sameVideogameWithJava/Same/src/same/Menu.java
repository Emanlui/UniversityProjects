package same;

import static java.lang.System.exit;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.*;

public class Menu extends Application implements EventHandler<ActionEvent> {
	

    /**
     * @class Menu
     * @brief Clase donde muestra el menú del juego con los botones para escoger el nivel
     */
	
	Stage ventana;
	Scene escena;
	
	Button nivel1;
	Button nivel2;
	Button nivel3;
	Button salir;
	Button jugar;
	
    public int nivel = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	/**
         * @brief Metodo que inicia la interfaz gráfica
         * @param primaryStage El stage de la pantalla
         */
    	
    	Image logo = new Image(getClass().getResourceAsStream("logo.png"));
    	
    	primaryStage.getIcons().add(logo);
    	ventana = primaryStage;
    	ventana.setTitle("~ Same ~"); //titulo
    	
	    nivel1 = new Button("NIVEL 1");
        nivel2 = new Button("NIVEL 2");
        nivel3 = new Button("NIVEL 3");
        jugar = new Button("Jugar");
        salir = new Button("Salir");
           
        nivel1.setOnAction(this);
        nivel2.setOnAction(this);
        nivel3.setOnAction(this);
        jugar.setOnAction(this);
        salir.setOnAction(this);
    	
		AnchorPane root = new AnchorPane(); 
		HBox niveles = new HBox(20, nivel1, nivel2, nivel3);
		HBox desicion = new HBox(50, jugar, salir); 
	
        root.setPadding(new Insets(20, 20, 20,20));
            
	    root.getChildren().addAll(niveles,desicion);
	    
	    AnchorPane.setRightAnchor(niveles,0d); //combo box
    	AnchorPane.setTopAnchor(niveles, 70d); //combo box
    	AnchorPane.setLeftAnchor(desicion, 50d); //combo box
    	AnchorPane.setTopAnchor(desicion, 10d); //combo box
    	
	   AnchorPane sp = new AnchorPane();
       sp.getChildren().add(root);           //inicializacion de la escena 2
       escena = new Scene(sp, 300, 150);
       ventana.setScene(escena);          // inicializacion de lbbbbbiiia escena1
       ventana.show();
    }
    
    @Override
    public void handle(ActionEvent evento) {
    	
    	/**
         * @brief Accion de los botones
         * @param evento Variable que capta el evento de los botones
         */
    	
    	if (evento.getSource() == nivel1) {
    		
                nivel = 1;
                nivel1.setDisable(true);
                nivel2.setDisable(false);
                nivel3.setDisable(false);
    	}
    	if (evento.getSource() == nivel2) {
    		
                nivel = 2;
                nivel1.setDisable(false);
                nivel2.setDisable(true);
                nivel3.setDisable(false);
    	}
    	if (evento.getSource() == nivel3) {
        
                nivel = 3;
                nivel1.setDisable(false);
                nivel2.setDisable(false);
                nivel3.setDisable(true);
    	}
    	
    	if (evento.getSource() == jugar) {
    		
            if(nivel == 0){

                new Alert(Alert.AlertType.ERROR, "Error, no ha seleccionado nivel.").showAndWait();
            }
            else{
                
                JugarInterfaz i = new JugarInterfaz();
                try {

                    i.start(ventana, nivel);
                } 
                catch (Exception ex) {

                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    	}
    	if (evento.getSource() == salir) {
    
    	    exit(0);
    	}
    } 

    public static void main(String[] args) {     
    	
    	launch(args);
    }
}  