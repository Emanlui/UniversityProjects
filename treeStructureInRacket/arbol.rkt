#lang racket



(require racket/trace)    ; este import me permite hacer debug del codigo

(define arbol (list))      ; esta es una variable global que tiene cada uno de los nodos del arbol

(struct nodoEstructura(idDelNodo idDelPadre listaDeNodos datos) #:mutable #:transparent)   ; este es el struct del nodo, contiene el id, el padre, los hijos y el datos

;----------------------------------------------------------------------------------------------------------------------------------------------------

; Nombre: insert-node
; Descripcion: Esta funcion insertar un nodo en la variable global (sin hijos) y luego llama a la funcion agregarHijo para agregarlos al arbol
; Entradas:
;          idNodo = El id del nodo que se desea insertar
;          idPadre = El id del padre del nodo que se desea insertar
;          arbolEntero = Es la variable global que contiene todos los nodos
;          datos = Es el dato que tendra el nodo
; Salidas: Esta funcion no retorna nada, solo insertar
; Restricciones:
;               - idNodo debe ser un numero
;               - idNodo debe ser positivo
;               - idPadre debe ser un numero
;               - idPadre debe ser positivo
;               - arbolEntero debe ser una lista
;               - datos tiene que ser string

(define (insert-node idNodo idPadre datos arbolEntero)
  (cond [(not(positive? idNodo)) "el id del nodo no es numero o positivo"]
        [(not(number? idPadre)) "el id del padre no es numero o positivo"]
        [(not(string? datos)) "el dato no es string"]
        [(not(list? arbolEntero)) "la lista de nodos no es una lista"]
        [(set! arbol  (append arbol (list (nodoEstructura idNodo idPadre (list) "datos"))))
  (agregarHijo idPadre idNodo arbol)]))

; Nombre: agregarHijo
; Descripcion: Esta funcion agregar a los hijos al padre del nodo insertado
; Entradas:
;          idNodo = El id del nodo que se desea insertar
;          idPadre = El id del padre del nodo que se desea insertar
;          arbolEntero = Es la variable global que contiene todos los nodos
; Salidas: Esta funcion no retorna nada, solo insertar los hijos al padre
; Restricciones:
;               - las restricciones ya fueron validadas 

(define (agregarHijo idPadre idNodo arbol)
  (cond[(empty? arbol) empty]
       [(if (= (nodoEstructura-idDelNodo (first arbol)) idPadre)
        (set-nodoEstructura-listaDeNodos! (first arbol) (append  (nodoEstructura-listaDeNodos (first arbol)) (list idNodo)))
        (agregarHijo idPadre idNodo (rest arbol)))]))

;----------------------------------------------------------------------------------------------------------------------------------------------------

; Nombre: delete-node
; Descripcion: Esta funcion elimina el nodo que se le envia por paramentro, en caso de tener hijos entonces los sigue eliminando recursivamente
;              En caso de que el nodo se encuentre, entonces llama a delete-first-node, que eliminar el primero nodo, de caso contrario sigue buscando
; Entradas:
;          idNodo = El id del nodo que se desea eliminar
;          arbol = Es la variable global que contiene todos los nodos
; Salidas: Esta funcion no retorna nada, solo elimina los nodos correspondientes
; Restricciones:
;               - idNodo debe ser un numero
;               - idNodo debe ser positivo
;               - arbol debe ser una lista

(define (delete-node idNodo arbol)
  (cond [(empty? arbol) "el nodo no existe"]
        [(not(positive? idNodo)) "el id del nodo no es numero o positivo"]
        [(not(list? arbol)) "la lista de nodos no es una lista"]
        [(if (= (nodoEstructura-idDelNodo (first arbol)) idNodo)
         (delete-first-node arbol)
         (delete-node idNodo (rest arbol)))]))

; Nombre: delete-first-node
; Descripcion: Esta funcion elimina el primer nodo de la lista
;              Llama a delete-node-loop, con los hijos del nodo a eliminar y finalmente se elimina el nodo (primero se eliminan los hijos, luego en nodo)
; Entradas:
;          arbol = Es la variable global que contiene todos los nodos
; Salidas: Esta funcion no retorna nada, solo elimina los hijos y luego el nodo
; Restricciones:
;               - Las restricciones ya fueron verificadas

(define (delete-first-node arbol)
  (delete-node-loop (nodoEstructura-listaDeNodos (first arbol)) arbol)
                    (delete-first-from-tree) )

; Nombre: delete-node-loop
; Descripcion: Esta funcion recorre la lista de hijos y vuelve a llamar a delete-node con el id y el arbol, despues continua recorriendo la lista recursivamente
; Entradas:
;          arbol = Es la variable global que contiene todos los nodos
;          hijos
; Salidas: Esta funcion no retorna nada, solo elimina los hijos y llama a la funcion inicial 
; Restricciones:
;               - Las restricciones ya fueron verificadas

(define (delete-node-loop hijos arbol)
  (cond [(empty? hijos) 1]
        [(begin (delete-node (first hijos) arbol)(delete-node-loop (rest hijos) arbol))]))

; Nombre: delete-first-from-tree
; Descripcion: Esta funcion me permite setear el arbol GLOBAL sin el nodo que se desea eliminar
; Entradas:
;          - no hay porque siempre es el primero
; Salidas: Esta funcion no retorna nada, solo elimina el nodo de la variable global 
; Restricciones:
;               - Las restricciones ya fueron verificadas

(define (delete-first-from-tree)
  (set! arbol (remove (first arbol) arbol)))

;-----------------------------------------------------------------------------------------------------------------------------------------------------

; Nombre: nicePrint
; Descripcion: Esta funcion imprime de forma bonita un nodo del arbol
; Entradas:
;          - nodo: El nodo a imprimir
; Salidas: Esta funcion no retorna nada, pero imprime los datos del nodo 
; Restricciones:
;               - El nodo tiene que ser de tipo nodo

(define (nicePrint nodo)
  (cond [(empty? nodo) "El nodo no puede ser vacio"]
        [(number? nodo) "El nodo no puede ser numero"]
        [(string? nodo) "El nodo no puede ser string"]
        [(write 'El_id_del_nodo_es:) (println (nodoEstructura-idDelNodo nodo))
         (display "\n")
         (write 'El_id_del_padre_es:) (println (nodoEstructura-idDelPadre nodo))
         (display "\n")
         (write 'Los_hijos_del_nodo_son:) (println (nodoEstructura-listaDeNodos nodo))
         (display "\n")
         (write 'El_dato_del_nodo_es:) (println(nodoEstructura-datos nodo))
         (display "\n")
         (write '--------------------------------------------------------------------)
         (display "\n")]))
  

;-------------------------------------------------------------------------------------------------------------------------------------------------------

; Nombre: list-all-nodes
; Descripcion: Por defecto el nodo raiz es el 0, es por esto que lo tomo el nodo raiz, entonces llamamos a list-some-nodes con el nodo raiz de parametro
;              La funcion llama a find-node-father, que busca el nodo que tenga como padre el indice 0 
; Entradas:
;          - arbol: Como unico parametro es el arbol, que es la variable global
; Salidas: Esta funcion no retorna nada, solo imprime todos los nodos apartir del nodo padre 
; Restricciones:
;               - arbol tiene que ser una lista

(define (list-all-nodes arbol)
  (cond  [(not(list? arbol)) "El arbol no es una lista"]
         [(empty? arbol) "la lista esta vacia"] 
         [(list-some-nodes (find-node-father 0 arbol) arbol)]))


; Nombre: list-some-nodes
; Descripcion: Esta funcion imprime el nodo actual y los hijos de el.
;              Primero imprime el nodo actual, luego llama a nicePrintChildren, que recibe la lista de hijos y el arbol
; Entradas:
;          - idNodo = Es el id del nodo a imprimir
;          - arbol: Como unico parametro es el arbol, que es la variable global
; Salidas: Esta funcion no retorna nada, solo imprime el nodo actual y el hijo
; Restricciones:
;               - idNodo debe ser un numero
;               - idNodo debe ser positivo
;               - arbol debe ser una lista

(define (list-some-nodes idNodo arbol)
  (cond [(not(positive? idNodo)) "El id del nodo no es numero o positivo"]
        [(not(list? arbol)) "La lista de nodos no es una lista"]
        [(empty? arbol) empty]
        [(find-node-print idNodo arbol) (nicePrintChildren (nodoEstructura-listaDeNodos (find-node idNodo arbol)) arbol)]))

; Nombre: find-node-print
; Descripcion: Esta funcion busca el nodo a imprimir en el arbol
;              Si el idNodo es igual al id del nodo actual, entonces lo imprime, sino llama a la misma funcion recursivamente sin el primero de la lista
; Entradas:
;          - idNodo = Es el id del nodo a imprimir
;          - arbol: Como unico parametro es el arbol, que es la variable global
; Salidas: Esta funcion no retorna nada, solo imprime el nodo actual
; Restricciones:
;               - Ya fueron validadas

(define (find-node-print idNodo arbol)
  (cond [(empty? arbol) empty]
        [(if (= idNodo (nodoEstructura-idDelNodo(first arbol)))
             (nicePrint (first arbol))
             (find-node-print idNodo (rest arbol)))]))

; Nombre: find-node
; Descripcion: Esta funcion busca el nodo y lo devuelve
;              Si el idNodo es igual al id del nodo actual, entonces lo devuelve, sino llama a la misma funcion recursivamente sin el primero de la lista
; Entradas:
;          - idNodo = Es el id del nodo a devolver
;          - arbol: Como unico parametro es el arbol, que es la variable global
; Salidas: Esta funcion retorna el nodo a buscar segun el indice
; Restricciones:
;               - Ya fueron validadas

(define (find-node idNodo arbol)
  (cond [(empty? arbol) empty]
        [(if (= idNodo (nodoEstructura-idDelNodo(first arbol)))
             (first arbol)
             (find-node idNodo (rest arbol)))]))

; Nombre: find-node-father
; Descripcion: Esta funcion busca el indice del nodo con un indice del padre dado, por ejemplo si recibe 1, entonces devuelve el primer nodo con ese padre
;              Esta funcion me sirve para buscar el nodo raiz
; Entradas:
;          - idNodo = Es el id del nodo a devolver
;          - arbol: Como unico parametro es el arbol, que es la variable global
; Salidas: Esta funcion retorna el id del nodo segun el id del padre a buscar
; Restricciones:
;               - Ya fueron validadas

(define (find-node-father idNodo arbol)
  (cond [(empty? arbol) empty]
        [(if (= idNodo (nodoEstructura-idDelPadre(first arbol)))
             (nodoEstructura-idDelNodo(first arbol))
             (find-node-father idNodo (rest arbol)))]))

; Nombre: nicePrintChildren
; Descripcion: Esta funcion recibe una lista de hijos que se desean imprimir, entonces recorre toda la lista y llama a list-some-nodes con el primero de la lista
; Entradas:
;          - lst: La lista con los hijos
;          - arbol: la lista con todos los hijos
; Salidas: Esta funcion no retorna nada, solo llama a otra función que imprime
; Restricciones:
;               - Ya fueron validadas

(define (nicePrintChildren lst arbol)
  (cond [(empty? lst) empty]
        [(list-some-nodes (first lst) arbol) (nicePrintChildren (rest lst) arbol)]))

;----------------------------------------------------------------------------------------------------------------


; Nombre: ancestor
; Descripcion: Esta funcion retorna el padre del nodo que se inserte como parametro
; Entradas:
;          idNodo = El id del nodo que se desea buscar el padre
;          arbol = Es la variable que contiene el arbol completo para buscar al padre
; Salidas: Esta funcion va imprimir el padre del nodo a buscar
; Restricciones:
;               - idNodo debe ser un numero
;               - idNodo debe ser positivo
;               - arbol tiene que ser lista
;               - arbol no puede ser vacia

(define (ancestor idNodo arbol)
  (cond [(not (number? idNodo)) "El id no es un numero"]
        [(not (positive? idNodo)) "El id no es positivo"]
        [(not (list? arbol)) "El arbol no es una lista"]
        [(empty? arbol) "No existe el padre"]                                   ; en caso de que la lista sea vacia retorna vacio
        [(if (= (nodoEstructura-idDelNodo(first arbol)) idNodo)  ; si el id del nodo se encuentra en el arbol, entonces busca al padre de ese nodo y lo imprime
              (nodoEstructura-idDelPadre(first arbol))         
             (ancestor idNodo (rest arbol)))]))                  ; de lo contrario llama a la funcion nuevamente sin el primero de la lista

;-------------------------------------------------------------------------------------------------

; Nombre: search
; Descripcion: Esta funcion retorna el padre del nodo que se inserte como parametro
; Entradas:
;          idNodo = El id del nodo que se desea buscar el padre
;          arbol = Es la variable que contiene el arbol completo para buscar al padre
; Salidas: Esta funcion va imprimir el padre del nodo a buscar
; Restricciones:
;               - idNodo debe ser un numero
;               - idNodo debe ser positivo
;               - arbol tiene que ser lista
;               - arbol no puede ser vacia

(define (search idNodo arbol listaDeNodosVarArbol)
  (cond [(not (number? idNodo)) "El id no es un numero"]
        [(not (positive? idNodo)) "El id no es positivo"]
        [(not (list? arbol)) "El arbol no es una lista"]
        [(empty? arbol) empty]
        [(if(= idNodo (nodoEstructura-idDelNodo (first arbol)))
           (find-node (nodoEstructura-idDelPadre (first arbol)) listaDeNodosVarArbol)
           (search idNodo (rest arbol) listaDeNodosVarArbol))]))


; Nombre: find-right-sibling
; Descripcion: Esta funcion retorna el hermano derecho del nodo que se inserte como parametro
; Entradas:
;          idNodo = El id del nodo que se desea buscar el padre
;          arbol = Es la variable que contiene el arbol completo para buscar al padre
; Salidas: Esta funcion va imprimir el hermano derecho del nodo a buscar
; Restricciones:
;               - idNodo debe ser un numero
;               - idNodo debe ser positivo
;               - arbol tiene que ser lista
;               - arbol no puede ser vacia


(define (find-right-sibling idNodo arbol)
  (cond [(not(number? idNodo)) "El id no es un numero"]
        [(not(list? arbol)) "El arbol no es una lista"]
        [(print-right (nodoEstructura-listaDeNodos(search idNodo arbol arbol)) idNodo)]))  ; esta funcion reutiliza otras funciones, las cuales son buscar,
                                                                              ; que retorna el nodo dependiendo del id que se le pide e imprimir derecho
; Nombre: imprimir-derecho
; Descripcion: Esta funcion retorna el hermano derecho del nodo que se inserte como parametro
;              En este caso recibe una lista con hijos, donde esta el id que se busca
; Entradas:
;          idNodo = El id del nodo que se desea buscar el padre
;          lst = Es la lista de hijos que contiene el padre del nodo (la lista con los hermanos)
; Salidas: Esta funcion va imprimir el hermano derecho del nodo
; Restricciones:
;               - las restricciones ya fueron validadas 

(define (print-right lst id)
  (cond [(= (length lst) 1) "no hay hermano"]                                      ; si solo existe un elemento entre los hijos, entonces no hay un hermano respectivo
        [(and (= (length lst) 2) (= (first lst) id)) (second lst)]                 ; si el tamano de la lista es 2 y el primer elemento es el id del nodo, entonces el hermano es el derecho
        [(if (= (first lst) id) (second lst) (print-right (rest lst) id))]))  ; si el tamano no es 2, pero el nodo a buscar es el primero de la lista, entonces retorna el segundo (el hermano)}
                                                                                   ; de caso contrario llama a la misma funcion si el hijo que se encuentra de primero.

; Nombre: find-left-sibling
; Descripcion: Esta funcion retorna el hermano izquierdo del nodo que se inserte como parametro
; Entradas:
;          idNodo = El id del nodo que se desea buscar el padre
;          arbol = Es la variable que contiene el arbol completo para buscar al padre
; Salidas: Esta funcion va imprimir el hermano izquierdo del nodo a buscar
; Restricciones:
;               - idNodo debe ser un numero
;               - idNodo debe ser positivo
;               - arbol tiene que ser lista
;               - arbol no puede ser vacia

(define (find-left-sibling idNodo arbol)
  (cond [(not(number? idNodo)) "El id no es un numero"]
        [(not(list? arbol)) "El arbol no es una lista"]
        [(print-left (nodoEstructura-listaDeNodos(search idNodo arbol arbol)) idNodo)]))

; Nombre: print-left
; Descripcion: Esta funcion retorna el hermano derecho del nodo que se inserte como parametro
;              En este caso recibe una lista con hijos, donde esta el id que se busca
; Entradas:
;          idNodo = El id del nodo que se desea buscar el padre
;          lst = Es la lista de hijos que contiene el padre del nodo (la lista con los hermanos)
; Salidas: Esta funcion va imprimir el hermano derecho del nodo
; Restricciones:
;               - las restricciones ya fueron validadas


(define (print-left lst id)
  (cond [(= (length lst) 1) "no hay hermano"]
        [(and (= (length lst) 2) (= (second lst) id)) (first lst)]
        [(if (= (second lst) id) (first lst) (print-left (reverse(rest (reverse lst))) id))]))

