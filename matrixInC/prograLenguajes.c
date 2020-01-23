#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
/**

	Esta es la lista con todos los nodos, almacena el valor de la casilla, la columna, la fila y un puntero
	al siguiente nodo.
	
*/
typedef struct listaGlobal {

  int valor;
  int filaDato;
  int columnaDato;
  struct listaGlobal *siguiente;
}listaGlobal;


/**

	Esta es la estructura de la matriz, me permite conoce que tan grande es la matriz y también tiene un puntero a la lista de nodos.

*/
typedef struct matriz {

    int filaDatos;
    int columnaDatos;
    struct listaGlobal *inicio;
} matriz;

void menu(matriz *matriz);
void llenar_matriz(matriz *matriz);
void sumarColumna(matriz *matriz);
void sumarFila(matriz *matriz);
void traspuesta(matriz *matriz);
void guardar(matriz *matriz);
void cargar(matriz *matriz);
void imprimir(matriz *matriz);
void multiplicar(matriz *matriz);
void sumar(matriz *matriz);
void multiplicarMatrices(matriz *matriz1,matriz *matriz2);

/**

	Descripcion: Este es el main, limpia la pantalla y le pide que inserte los valores de la matriz, luego llama al menú con los comandos.
	
*/
void main() {

    printf("\e[1;1H\e[2J");
    printf("Bienvenido a la pagina de matrices.\n");

    matriz *matriz = malloc(sizeof(matriz));
    


    printf("\e[1;1H\e[2J");
    menu(matriz);
    

}
/**

	Descripcion: Este es el menú del programa, le permite al usuario seleccionar la opción que desee hacer la matriz
	Entradas: La matriz 
	Salidas: Ninguna
	Restricciones: Ninguna

*/
void menu(matriz *matriz) {

    int opcion = 0;
    
    printf("\n----------------MENU----------------\n");
    
    printf("0.Crear matriz \n");
    printf("1.Insertar elementos en la matriz: \n");
    printf("2.Sumar columnas: \n");
    printf("3.Sumar filas: \n");
    printf("4.Calcular transpuesta: \n");
    printf("5.Imprimir matriz: \n");
    printf("6.Guardar matriz: \n");
    printf("7.Cargar matriz: \n");
    printf("8.Multiplicar matrices: \n");
    printf("9.Sumar matrices: \n");
    printf("\n Digite la opcion que desee: \n");
    scanf("%d", &opcion);
    printf("\e[1;1H\e[2J");
    switch(opcion) {
        
        case 0:
            crear(matriz);
            break;
        case 1:
            llenar_matriz(matriz);    
            break;
        case 2:
            sumarColumna(matriz);
        case 3:
            sumarFila(matriz);
            break;
        case 4:
            traspuesta(matriz);
            break;
        case 5:
            imprimir(matriz);
            break;
        case 6:
            guardar(matriz);
            break;
        case 7:
            cargar(matriz);
            break;
        case 8:
            multiplicar(matriz);
            break;
        case 9:
            sumar(matriz);
        default:
            menu(matriz);
    }
}


/**

    Descripcion: Esta es la funcion que permite crear una matriz, unicamente define los tamanos
                 Se le resta 1 porque cuando el usuario digita que es tamano 4x4 en realidad
                 el programa lo agarra como 3x3, porque el 0 se toma en cuenta.
    Entradas: La matriz.
    Salidas: Ninguna.
    Restricciones: Ninguna
*/
void crear(matriz* matriz){

    if(matriz->inicio != NULL){
        printf("Ya existe una matriz creada\n");
        menu(matriz);
    }
    printf("Digite la cantidad de filas en la matriz: \n");
    scanf("%d", &matriz->filaDatos);
    matriz->filaDatos--;
    printf("Digite la cantidad de columnas en la matriz: \n");
    scanf("%d", &matriz->columnaDatos);
    matriz->columnaDatos--;
    matriz->inicio = NULL;
    menu(matriz);
}
/**

	Descripcion: Esta es la función para poder sumar dos matrices, le pide al usuario que escriba los valores para las 2 matrices
				 luego este le imprime las dos matrices y finalmente le retorna el resultado de la suma.
	Entradas:   La matriz.
	Salidas:	Ninguna.
	Restricciones:	Las matrices deben tener el mismo tamano.

*/
void sumar(matriz *matrizGlobal){

    matriz *matriz1 = malloc(sizeof(matriz));
    matriz *matriz2 = malloc(sizeof(matriz));

    printf("A continuacion se le preguntara por la dimension de las dos matrices \n");

    llenarMatrices(matriz1);
    llenarMatrices(matriz2);
    

    if(matriz1->filaDatos != matriz2->filaDatos || matriz1->columnaDatos != matriz2->columnaDatos){
        printf("Error, las dimensiones de las matrices son incorrectas \n");
        return ;
    }


    printf("Digite los datos de la primera matriz");
    llenarListas(matriz1);
    printf("\e[1;1H\e[2J");

    printf("Digite los datos de la segunda matriz");
    llenarListas(matriz2);
    printf("\e[1;1H\e[2J");


// -------------------------------------------------------------------------------------

    sumarMatrices(matriz1,matriz2);
    menu(matrizGlobal);

}

/**

	Descripcion: Esta función me permite sumar 2 matrices que le entren por parámetro.
	Entradas:	Las 2 matrices a sumar.
	Salidas:	Ninguna.
	Restricciones: Ninguna.

*/
void sumarMatrices(matriz *matriz1, matriz *matriz2){


    int i = 0;
    int j = 0;


    i = matriz1->filaDatos-1;  
    j = matriz1->columnaDatos-1;  
    
    int **first = (int **)malloc(i * sizeof(int*));
    for(int x = 0; x <= i; x++) first[x] = (int *)malloc(j * sizeof(int));

    int **second = (int **)malloc(i * sizeof(int*));
    for(int x = 0; x <= i; x++) second[x] = (int *)malloc(j * sizeof(int));

    int **respuesta = (int **)malloc(i * sizeof(int*));
    for(int x = 0; x <= i; x++) respuesta[x] = (int *)malloc(j * sizeof(int));

//-------------------------------------------------------------------------------------------------

    listaGlobal* actual1 = matriz1->inicio;
    while (actual1->siguiente != NULL) {
        first[actual1->filaDato][actual1->columnaDato] = actual1->valor;
        actual1 = actual1->siguiente;
    }
    first[actual1->filaDato][actual1->columnaDato] = actual1->valor;

//-------------------------------------------------------------------------------------------------

    listaGlobal* actual2 = matriz2->inicio;
    while (actual2->siguiente != NULL) {
    
        second[actual2->filaDato][actual2->columnaDato] = actual2->valor; 
        actual2 = actual2->siguiente;
    }
    second[actual2->filaDato][actual2->columnaDato] = actual2->valor;

//-------------------------------------------------------------------------------------------------
    
    int sum = 0;

    for (int x = 0; x <= i; x++)
      for (int y = 0; y <= j; y++) 
        respuesta[x][y] = first[x][y] + second[x][y];
    

    printf ("Matrices a sumar: \n \n");

    for(int x = 0; x<=i; x++){
        for(int y = 0; y <= j; y++)
            printf("%d ", first[x][y]);
        printf("\n");
    }

    printf("\n");
    printf("--------------------------------------\n");
    printf("\n");

    for(int x = 0; x<=i; x++){
        for(int y = 0; y <= j; y++)
            printf("%d ", second[x][y]);
        printf("\n");
    }

    printf("\n");
    printf("--------------------------------------\n");
    
    printf ("Respuesta: \n");

    for(int x = 0; x<=i; x++){
        for(int y = 0; y <= j; y++)
            printf("%d ", respuesta[x][y]);
        printf("\n");
    }

}

/**

	Descripcion: Esta es la función para poder multiplicar dos matrices, le pide al usuario que escriba los valores para las 2 matrices
				 luego este le imprime las dos matrices y finalmente le retorna el resultado de la multiplicación.
	Entradas:	la matriz global.
	Salidas:	Ninguna.
	Restricciones:	Las matrices deben tener el tamano correcto.

*/
void multiplicar(matriz *matrizGlobal){
   
    matriz *matriz1 = malloc(sizeof(matriz));
    matriz *matriz2 = malloc(sizeof(matriz));
    printf("A continuacion se le preguntara por la dimension de las dos matrices \n");
    llenarMatrices(matriz1);
    llenarMatrices(matriz2);


    if(matriz1->columnaDatos != matriz2->filaDatos){
        printf("Error, las dimensiones de las matrices son incorrectas \n");
        return ;
    }
    printf("Digite los datos de la primera matriz");
    llenarListas(matriz1);
    printf("\e[1;1H\e[2J");

    printf("Digite los datos de la segunda matriz");
    llenarListas(matriz2);
    printf("\e[1;1H\e[2J");

// -------------------------------------------------------------------------------------

    multiplicarMatrices(matriz1,matriz2);

    menu(matrizGlobal);
}

/**

	Descripcion: Esta función me permite multiplicar 2 matrices que le entren por parámetro.
	Entradas:	Las 2 matrices a multiplicar.
	Salidas:	Ninguna.
	Restricciones: Ninguna.

*/
void multiplicarMatrices(matriz *matriz1, matriz *matriz2){


    int i = 0;
    int j = 0;

    i = matriz1->filaDatos-1;
    j = matriz2->columnaDatos-1;

    int **multiply = (int **)malloc(i * sizeof(int*));
    for(int x = 0; x <= i; x++) multiply[x] = (int *)malloc(j * sizeof(int));

    int **first = (int **)malloc((matriz1->filaDatos-1) * sizeof(int*));
    for(int x = 0; x <= matriz1->filaDatos-1; x++) first[x] = (int *)malloc((matriz1->columnaDatos-1) * sizeof(int));


    int **second = (int **)malloc((matriz2->filaDatos-1) * sizeof(int*));
    for(int x = 0; x <= matriz2->filaDatos-1; x++) second[x] = (int *)malloc((matriz2->columnaDatos-1) * sizeof(int));

//-------------------------------------------------------------------------------------------------

    listaGlobal* actual1 = matriz1->inicio;
    while (actual1->siguiente != NULL) {
        first[actual1->filaDato][actual1->columnaDato] = actual1->valor;
        actual1 = actual1->siguiente;
    }
    first[actual1->filaDato][actual1->columnaDato] = actual1->valor;

//-------------------------------------------------------------------------------------------------

    listaGlobal* actual2 = matriz2->inicio;
    while (actual2->siguiente != NULL) {
    
        second[actual2->filaDato][actual2->columnaDato] = actual2->valor; 
        printf("second[%d][%d] %d val %d \n", actual2->filaDato, actual2->columnaDato, second[actual2->filaDato][actual2->columnaDato], actual2->valor);
        actual2 = actual2->siguiente;
        
    }
    second[actual2->filaDato][actual2->columnaDato] = actual2->valor;
    //printf("second[%d][%d] %d val %d \n", actual2->filaDato, actual2->columnaDato, second[actual2->filaDato][actual2->columnaDato], actual2->valor);


    for(int x = 0; x<=matriz2->filaDatos-1; x++){
        for(int y = 0; y <= matriz2->columnaDatos-1; y++)
            //printf("second[%d][%d] = %d ", x,y,second[x][y]);
        printf("\n");
    }
//-------------------------------------------------------------------------------------------------
    
    for (int c = 0; c <= matriz1->filaDatos-1; c++) {
      for (int d = 0; d <= matriz2->columnaDatos-1; d++) {
        for (int k = 0; k <= matriz1->columnaDatos-1; k++) {
          multiply[c][d] += first[c][k]*second[k][d];
          //printf("m[%d][%d] += first[%d][%d]*second[%d][%d] -> suma %d \n", c,d,c,k,k,d,multiply[c][d]);
        }
       }
    }

    printf ("Matrices a multiplicar: \n \n");

    for(int x = 0; x<=matriz1->filaDatos-1; x++){
        for(int y = 0; y <= matriz1->columnaDatos-1; y++)
            printf("%d ", first[x][y]);
        printf("\n");
    }

    printf("\n");
    printf("--------------------------------------\n");
    printf("\n");
    for(int x = 0; x<=matriz2->filaDatos-1; x++){
        for(int y = 0; y <= matriz2->columnaDatos-1; y++)
            printf("%d ", second[x][y]);
        printf("\n");
    }
    printf("\n");
    printf("--------------------------------------\n");
    
    printf ("Respuesta: \n \n");

    for(int x = 0; x<=i; x++){
        for(int y = 0; y <= j; y++)
            printf("%d ", multiply[x][y]);
        printf("\n");
    }

}



/**

	Descripcion: Esta funcion me permite poder llenar los datos de la matriz, en este caso serían todos los nodos que se deseen insertar,
				 el programa va parar de solicitarle valores cuando se le inserte un 0 en la pregunta
	Entradas:	La matriz a cambiar.
	Salidas:	Ninguna.
	Restricciones:	El tamaño de la matriz debe ser válido.

*/
void llenarListas(matriz *matriz){

    int x = 1;

    while (x == 1) {     

        printf("\n");
        
        listaGlobal* nuevo_listaGlobal = malloc(sizeof(listaGlobal));   
        
        printf("Digite el valor -> ");
        scanf("%d", &nuevo_listaGlobal->valor);
        
        printf("Digite la filaDato -> ");
        scanf("%d", &nuevo_listaGlobal->filaDato);
        
        printf("Digite la columnaDato -> ");
        scanf("%d", &nuevo_listaGlobal->columnaDato);
        
        if(nuevo_listaGlobal->filaDato > matriz->filaDatos || nuevo_listaGlobal->columnaDato > matriz->columnaDatos) {
            
            printf("\nPosicion invalida.");
            llenarListas(matriz);
        } else {
            
            if(matriz->inicio == NULL) {
                matriz->inicio = nuevo_listaGlobal;
            } else {
                nuevo_listaGlobal->siguiente = matriz->inicio;
                matriz->inicio = nuevo_listaGlobal;
            }  
        }
        printf("Digite 1 si quiere insertar otro dato o 0 si no -> ");
        scanf("%d", &x); 
    }

}


void llenarMatrices(matriz *matriz){

    printf("Digite la cantidad de filas en la matriz: \n");
    scanf("%d", &matriz->filaDatos);
    printf("Digite la cantidad de columnas en la matriz: \n");
    scanf("%d", &matriz->columnaDatos);
    matriz->inicio = NULL;
    printf("\e[1;1H\e[2J");

}

/**

	Descripcion: Esta funcion me permite poder llenar los datos de la matriz, en este caso serían todos los nodos que se deseen insertar,
				 el programa va parar de solicitarle valores cuando se le inserte un 0 en la pregunta
	Entradas:	La matriz a cambiar.
	Salidas:	Ninguna.
	Restricciones:	El tamaño de la matriz debe ser válido.

*/
void llenar_matriz(matriz *matriz) {

        printf("\n");
        
        listaGlobal* nuevo_listaGlobal = malloc(sizeof(listaGlobal));   
        
        printf("Digite el valor -> ");
        scanf("%d", &nuevo_listaGlobal->valor);
        
        printf("Digite la filaDato -> ");
        scanf("%d", &nuevo_listaGlobal->filaDato);
        
        printf("Digite la columnaDato -> ");
        scanf("%d", &nuevo_listaGlobal->columnaDato);
        
        if(nuevo_listaGlobal->filaDato > matriz->filaDatos || nuevo_listaGlobal->columnaDato > matriz->columnaDatos) {
            
            printf("\nPosicion invalida.");
            llenar_matriz(matriz);
        } else {
            
            if(matriz->inicio == NULL) {
                matriz->inicio = nuevo_listaGlobal;
            } else {
                nuevo_listaGlobal->siguiente = matriz->inicio;
                matriz->inicio = nuevo_listaGlobal;
            }  
        
    }
    printf("\e[1;1H\e[2J");
    menu(matriz);
}



/**

	Descripcion: Esta función me permite sumar la columna de una matriz, el usuario decide cuál columna seleccionar.
                 Se le resta 1 al numero que introdujo el usuario porque el 0 no se toma en cuenta, pero la maquina
                 si lo hace.
	Entradas:	La matriz a sumar.
	Salidas:	Ninguna.
	Restricciones:	El número de la columna debe ser correcto.

*/
void sumarColumna(matriz *matriz){

    int contador = 0;
    int numero = 0;

    if(matriz->inicio == NULL){
        printf("Error, la matriz está vacía \n");
        menu(matriz);
    }
    
    printf("Digite el numero de la columna que desea sumar -> ");
    scanf("%d", &numero);


    if(numero-1 > matriz->columnaDatos) {
        
        printf("\nOpcion invalida.");
        sumarColumna(matriz);
    }

    int suma = 0;
    listaGlobal* actual = matriz->inicio;
    
    if(actual == NULL){
        printf("Error, la lista esta vacia");
        return;
    }
    
    while (actual->siguiente != NULL) {
        if (actual->columnaDato == numero-1) 
            contador += actual->valor;
        actual = actual->siguiente;
    }

    if (actual->columnaDato == numero-1) 
        contador += actual->valor;
        

    printf("\n\n La suma es -> |%d|", contador);
    
    menu(matriz);

}

/**

	Descripcion: Esta función me permite sumar la fila de una matriz, el usuario decide cuál fila seleccionar.
                 Se le resta 1 al numero que introdujo el usuario porque el 0 no se toma en cuenta, pero la maquina
                 si lo hace.
	Entradas:	La matriz a sumar.
	Salidas:	Ninguna.
	Restricciones:	El número de la fila debe ser correcto.

*/
void sumarFila(matriz *matriz){

    int contador = 0;
    int numero = 0;

    if(matriz->inicio == NULL){
        printf("Error, la matriz está vacía \n");
        menu(matriz);
    }
    
    printf("Digite el numero de fila que desea sumar -> ");
    scanf("%d", &numero);

    if(numero-1 > matriz->filaDatos) {
        
        printf("\nOpcion invalida.");
        sumarFila(matriz);
    }

    
    int suma = 0;
    listaGlobal* actual = matriz->inicio;
    
    if(actual == NULL){
        printf("Error, la lista esta vacia");
        return;
    }
    
    while (actual->siguiente != NULL) {
        if (actual->filaDato == numero-1) contador += actual->valor;
        actual = actual->siguiente;
    }

    if (actual->filaDato == numero-1) 
        contador += actual->valor;
    
     
    printf("\n\n La suma es -> |%d|", contador);
    
    menu(matriz);
}

/**

	Descripcion: Esta función me permite imprimir una matriz que reciba por parámetro.
	Entradas:	La matriz a imprimir.
	Salidas:	Ninguna.
	Restricciones:	Ninguna.

*/
void imprimir(matriz *matriz) {

    if(matriz->inicio == NULL){
        printf("Error, la matriz está vacía \n");
        menu(matriz);
    }

    printf("\n Matriz: \n");

    listaGlobal* actual = malloc(sizeof(listaGlobal));   
    actual = matriz->inicio;
    if(actual == NULL){
        printf("La matriz esta vacia \n");
        return;
    }
    int matrizRecorrido [matriz->filaDatos][matriz->columnaDatos] ;
    for(int i = 0; i<=matriz->filaDatos;i++)
        for(int j = 0; j<=matriz->columnaDatos;j++)
            matrizRecorrido[i][j] = 0;
    while (actual->siguiente != NULL) {
    
        matrizRecorrido[actual->filaDato][actual->columnaDato] = actual->valor;
        actual = actual->siguiente;
    }
    matrizRecorrido[actual->filaDato][actual->columnaDato] = actual->valor;
    for(int i = 0; i<=matriz->filaDatos;i++){
        for(int j = 0; j<=matriz->columnaDatos;j++)
            printf("%d ", matrizRecorrido[i][j]);
        printf("\n");
    }
        
    menu(matriz);
}

/**

	Descripcion: Esta función me permite imprimir la transpuesta de una matriz.
	Entradas:	La matriz a imprimir.
	Salidas:	Ninguna.
	Restricciones:	Ninguna.

*/
void traspuesta(matriz *matriz) {
    

    if(matriz->inicio == NULL){
        printf("Error, la matriz está vacía \n");
        menu(matriz);
    }

    printf("\n Matriz: \n");

    listaGlobal* actual = matriz->inicio;
    if(actual == NULL){
        printf("La matriz esta vacia \n");
        return;
    }
    
    int **matrizRecorrido = (int **)malloc(matriz->filaDatos * sizeof(int*));
    for(int x = 0; x <= matriz->filaDatos; x++) matrizRecorrido[x] = (int *)malloc(matriz->columnaDatos * sizeof(int));

    int **matrizRecorridoTrans = (int **)malloc(matriz->columnaDatos * sizeof(int*));
    for(int x = 0; x <= matriz->columnaDatos; x++) matrizRecorridoTrans[x] = (int *)malloc(matriz->filaDatos * sizeof(int));

            
    while (actual->siguiente != NULL) {
    
        matrizRecorrido[actual->filaDato][actual->columnaDato] = actual->valor;
        actual = actual->siguiente;
    }
    matrizRecorrido[actual->filaDato][actual->columnaDato] = actual->valor;

    for(int i = 0; i<=matriz->filaDatos;i++)
        for(int j = 0; j<=matriz->columnaDatos;j++)
            matrizRecorridoTrans[j][i] = matrizRecorrido[i][j];

    for(int i = 0; i<=matriz->columnaDatos;i++){
        for(int j = 0; j<=matriz->filaDatos;j++)
            printf("%d ", matrizRecorridoTrans[i][j]);
        printf("\n");
    }
        
    menu(matriz);
    
}

/**

    Descripcion: Esta funcion permite guardar una matriz en un csv, el nombre del csv es datos.csv
    Entradas: La matriz a guardar.
    Salidas: Ninguna.
    Restricciones: La matriz debe ser creada.
*/
void guardar(matriz *matriz){

    if(matriz->filaDatos == 0 && matriz->columnaDatos == 0){
        printf("La matriz no ha sido creada...\n");
        crear(matriz);
    }

    FILE *fp = fopen("datos.csv","w");
    int i = 0;
    int j = 0;

    listaGlobal* actual = malloc(sizeof(listaGlobal));   
    actual = matriz->inicio;

    int matrizRecorrido [matriz->filaDatos][matriz->columnaDatos] ;
    for(int i = 0; i<=matriz->filaDatos;i++)
        for(int j = 0; j<=matriz->columnaDatos;j++)
            matrizRecorrido[i][j] = 0;
    while (actual->siguiente != NULL) {
    
        matrizRecorrido[actual->filaDato][actual->columnaDato] = actual->valor;
        actual = actual->siguiente;
    }
    matrizRecorrido[actual->filaDato][actual->columnaDato] = actual->valor;
    for(int i = 0; i<=matriz->filaDatos;i++){
        fprintf(fp,"\n%d",i+1);
        for(int j = 0; j<=matriz->columnaDatos;j++)
            fprintf(fp,",%d ", matrizRecorrido[i][j]);
    }
 
    fclose(fp);
    menu(matriz);
}

/**

	Descripcion: Esta función cargar una matriz en dicho path especificado y lo guarda en la matriz.
	Entradas:	La matriz a cargar.
	Salidas: 	Ninguna.
	Restricciones:	Ninguna.
	
*/
void cargar(matriz *matriz){
    
    printf("Recuerde que la matriz debe ser del mismo tamano que los datos del csv, la primera columna del csv se salta \n");
    if(matriz->filaDatos == 0 && matriz->columnaDatos == 0){
        printf("La matriz no ha sido creada...\n");
        crear(matriz);
    }
    FILE* fp = fopen("datos.csv", "r");
    char buf[1024];

    if (!fp) {
        printf("El archivo no se encuentra\n");
        return;
    }

    int j = 0;

    int posX = -1;

    while (fgets(buf, 1024, fp)) {
        //printf("%s\n", buf);
        
        int i = 0;
        int posY = 0;
        for (; i < strlen(buf); i++) {
            if(isdigit(buf[i]) && j>0){
                                
                 
                listaGlobal* nuevo_listaGlobal = malloc(sizeof(listaGlobal));   

                nuevo_listaGlobal->valor = (int)buf[i] - 48;

                nuevo_listaGlobal->filaDato = posX;
                    
                nuevo_listaGlobal->columnaDato = posY;
                    
                //printf("i: %d :j %d valor: %d \n", posX,posY,(int)buf[i] - 48);

                if(matriz->inicio == NULL) {
                    matriz->inicio = nuevo_listaGlobal;
                } else {
                    nuevo_listaGlobal->siguiente = matriz->inicio;
                    matriz->inicio = nuevo_listaGlobal;
                }
                
                posY++;  
            } 
            j++;
            
        }
        posX++;
        printf("\n");
        j = 0;
    }

    fclose(fp);

    //printf("\e[1;1H\e[2J");

    menu(matriz);

}


