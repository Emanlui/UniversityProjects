package same;

import javafx.scene.image.Image;

public class JugarLogica {
    
	/**
     * @class JugarLogica
     * @brief Clase que se encarga de las funciones lógicas del juego
     */
	
    public boolean verificacion(int i, int j, Image [][] arreglo) {
    	
    	/**
         * @brief verifica si la posición en la matriz es valida
         * @param i posición en la matriz
         * @param j posición en la matriz
         * @param arreglo Arreglo de imágenes
         * @return true si es válido, false sino
         */
    	
        if(arreglo.length <= i){
        	
            return false;
        }
        else if(arreglo[0].length <= j){

        	
            return false;
        }
        else if(i < 0){
        	
            return false;
        }
        else if(j < 0){
        	
            return false;
        }
        return true;
    }
    
    public boolean VerificarArreglo(int [][] arreglo){
        
    	/**
         * @brief Verifica si hay ganador en el arreglo
         * @param arreglo El arreglo del juego
         * @return true si no hay ganador, false si ganó
         */
    	
        int contador = 0;
        for(int i = 0; i < 10;i++){
            
        	for(int j = 0; j < 12;j++){
                
        		if(arreglo[i][j] == 1){
                    
                    contador++;
                }
            }
        }
       
        if(contador > 1){
        	
            return true;
        }
        else{
        	
            return false;
        }
    }    
    
    public int[][] NuevoArreglo(Image[][] arreglo_logico, int[][] adyacentes, int i, int j, Image color){
       
    	/**
         * @brief El recorrido del backtracking, busca todos los vecinos en la matriz
         * @param arreglo_logico Arreglo del juego en Imágenes
         * @param adyacentes Arreglo donde se almacenan los adyacentes
         * @param i posición en el arreglo
         * @param j posición en el arreglo
         * @param color Guarda el color que anda buscando
         * @return retorna el arreglo de adyacentes(vecinos)
         */
    	
    	if(adyacentes[i][j] == 0){
            
    		if(arreglo_logico[i][j] == color){
              
    			adyacentes[i][j] = 1;
                //System.out.print(arreglo_logico[i][j]);
                //System.out.println("i: " + i + "j: " + j + " tienen el color:" + color);
               
                if(verificacion(i+1,j,arreglo_logico) == true){
                    adyacentes = NuevoArreglo(arreglo_logico,adyacentes,i+1,j,color);
                }
                if(verificacion(i-1,j,arreglo_logico)  == true){
                    adyacentes = NuevoArreglo(arreglo_logico,adyacentes,i-1,j,color);
                }
                if(verificacion(i,j+1,arreglo_logico) == true){
                    adyacentes = NuevoArreglo(arreglo_logico,adyacentes,i,j+1,color);
                }
                if(verificacion(i,j-1,arreglo_logico) == true){
                    adyacentes = NuevoArreglo(arreglo_logico,adyacentes,i,j-1,color);
                }
                //System.out.println("False");
            }
        }
    return adyacentes;
    }
    
    public Image[][] EliminarAdyacentes(int[][] adyacentes, Image[][] arreglo){
        
    	/**
         * @brief pone en nulo todos los vecinos encontrados del mismo color
         * @param adyacentes Matriz con todos los vecinos
         * @param arreglo Matriz con las Imágenes del juego(Se ponen en nulo)
         * @return retorna el arreglo de imágenes pero con los vecinos encontrados en nulo
         */
    	
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 12;j++){
                if(adyacentes[i][j] == 1){
                    arreglo[i][j] = null;
                }
            }
        }
        return arreglo;
    }
    
    public Image[][] UnirBotonesHorizontal(Image[][] arreglo){
    
    	/**
         * @brief Busca espacios nulos para que se puedan correr para abajo por medio de iteración
         * @param arreglo Matriz con las imágenes del juego
         * @return Retorna la misma matriz pero con las imágenes corridas como debe ser
         */

    	
        int contador;
        
        //System.out.println("Entra");
        
        for(int j = 0; j < 12; j++){
            contador = 0;
            for(int i = 0; i < 10; i++){
                if(arreglo[i][j] == null){
                    contador++;
                    
                }
            
	            if(contador == 10){
	            //System.out.println("Boton es: " + j);
	            
	                for(int z = 0; z < 10; z++){
	                	
	                    for(int x = j; x < 11;x++){
	                    	
	                           arreglo[z][x] = arreglo[z][x+1];
	                           arreglo[z][11] = null;
	                    }
	                }
	            }        
            }
        }
        return arreglo;
    }
    
    public Image[][] UnirBotonesVertical(Image[][]arreglo){    
        
    	/**
         * @brief Une la matriz cuando encuentra columnas en blanco, las mueve a la izquierda
         * @param matriz Matriz que va ser corrida en caso de que encuentre espacios
         * @return Retorna la misma matriz pero alterada
         */
    	
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 12; j++){
              
                if(arreglo[i][j] == null){
                    for(int x = i; x > 0;x--){
                        arreglo[x][j] = arreglo[x-1][j];
                        
                    }
                    arreglo[0][j] = null;
                }
            }     
        }
        return arreglo;
    }
    
    public int VerificarPartida(Image[][]arreglo){
        
    	/**
         * @brief Verifica si existen jugadas existentes en el tablero
         * @param matriz Matriz con las imágenes
         * @return Retorna -1 si no hay jugadas existentes, 1 si se puede jugar
         */
    	
        int contador = 0;
        
        for(int i = 0; i < 10;i++){
        	
            for(int j = 0; j < 12;j++){
            	
                if(arreglo[i][j] != null){
                    contador++;
                }
            }
        }
        
        if(contador == 0){
            return 1;
        }
        
        int parejas = 0;
        
        for(int i = 1; i < 9;i++){
           for(int j = 1; j < 9;j++){
                if(arreglo[i][j] != null){
                    if(arreglo[i][j] == arreglo[i+1][j]) parejas++;
                    else if(arreglo[i][j] == arreglo[i-1][j]) parejas++;
                    else if(arreglo[i][j] == arreglo[i][j+1]) parejas++;
                    else if(arreglo[i][j] == arreglo[i][j-1]) parejas++;
                }
           }
           
        }
     
        for(int i = 1; i < 11; i++){
            if(arreglo[0][i] != null){
                if(arreglo[0][i] == arreglo[1][i]) parejas++;
                else if(arreglo[0][i] == arreglo[0][i+1]) parejas++;
                else if(arreglo[0][i] == arreglo[0][i-1]) parejas++;
            }
        }
        
        for(int i = 1; i < 11; i++){
            
            if(arreglo[9][i] != null){
                if(arreglo[9][i] == arreglo[8][i]) parejas++;
                else if(arreglo[9][i] == arreglo[9][i+1]) parejas++;
                else if(arreglo[9][i] == arreglo[9][i-1]) parejas++;
            }
        }
       
        for(int i = 1; i < 9; i++){
            if(arreglo[i][0] != null){
                if(arreglo[i][0] == arreglo[i-1][0]) parejas++;
                else if(arreglo[i][0] == arreglo[i+1][0]) parejas++;
                else if(arreglo[i][0] == arreglo[i][1]) parejas++;
            }
        }
        
        for(int i = 1; i < 9; i++){
            if(arreglo[i][11] != null){
                if(arreglo[i][11] == arreglo[i-1][11]) parejas++;
                else if(arreglo[i][11] == arreglo[i+1][11]) parejas++;
                else if(arreglo[i][11] == arreglo[i][10]) parejas++;
            }
        }
        
        if(parejas == 0){
            return -1;
        }
        return 0;
    } 
}