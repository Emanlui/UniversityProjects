#lang racket

(define f00 (list 0 0))
(define f01 (list 0 1))
(define f11 (list 1 1))
(define f02 (list 0 2))
(define f12 (list 1 2))
(define f22 (list 2 2))
(define f03 (list 0 3))
(define f13 (list 1 3))
(define f23 (list 2 3))
(define f33 (list 3 3))
(define f04 (list 0 4))
(define f14 (list 1 4))
(define f24 (list 2 4))
(define f34 (list 3 4))
(define f44 (list 4 4))   ; Las 28 fichas existentes del domino.
(define f05 (list 0 5))
(define f15 (list 1 5))
(define f25 (list 2 5))
(define f35 (list 3 5))
(define f45 (list 4 5))
(define f55 (list 5 5))
(define f06 (list 0 6))
(define f16 (list 1 6))
(define f26 (list 2 6))
(define f36 (list 3 6))
(define f46 (list 4 6))
(define f56 (list 5 6))
(define f66 (list 6 6))

(define lista-por-defecto (list f00 f01 f11 f02 f12 f22 f03 f13 f23 f33 f04 f14 f24 f34 f44 f05 f15 f25 f35 f45 f55 f06 f16 f26 f36 f46 f56 f66)) ; Lista con las 28 fichas del domino.
(define lista-alternativa (list)) ; Lista vacia para anadir fichas deseadas.

(define (anadir-ficha ficha)
  #|
  Entradas: Una unica ficha de las previamente creadas.
  Salidas: Nada.
  Restricciones: Unicamente puede ingresar fichas previamentes creadas o de ser el caso que quiera agregar fichas externas al juego deben tener formato valido, tal como : (list # #).
  Descripcion: Ande una ficha a la lista alternativa.
  |#
  (set! lista-alternativa (cons ficha lista-alternativa)))

(define (eliminar-ficha ficha)
  #|
  Entradas: Una unica ficha de las previamente creadas.
  Salidas: Nada.
  Restricciones: Unicamente puede ingresar fichas previamentes creadas o de ser el caso que quiera agregar fichas externas al juego deben tener formato valido, tal como : (list # #).
  Descripcion: Elimina una ficha de la lista por defecto.
  |#
  (set! lista-por-defecto (remove ficha lista-por-defecto)))

(define (jugar lista cadena iteraciones)
  #|
  Entradas: Una lista de fichas (lista) | Una cadena de fichas (lista) | Cantidad de iteraciones que lleva recorriendo la lista de fichas (numero).
  Salidas: La cadena de fichas mas larga posible (lista).
  Restricciones: La lista debe contener fichas con el formato valido.
  Descripcion: Funcion inicial del juego, verifica cuando ya no hay mas jugadas y retorna la cadena mas larga posible.
  |#
  (cond
    [(= iteraciones (length lista)) cadena]
    [else (if (empty? cadena) (jugar (rest lista)(append cadena (list (first lista))) 0) (verificar-extremos lista cadena iteraciones))]))

(define (verificar-extremos lista cadena iteraciones)
  #|
  Entradas: Una lista de fichas (lista) | Una cadena de fichas (lista) | Cantidad de iteraciones que lleva recorriendo la lista de fichas (numero).
  Salidas: Ninguna.
  Restricciones: La lista debe contener fichas con el formato valido.
  Descripcion: Funcion que verifica las 4 posibles formas de anadir una ficha a la cadena.
  |#
  (cond
    [(= (first (first cadena)) (second (first lista))) (jugar (rest lista) (append (list (first lista)) cadena) 0)] ; verifica la primera ficha de la lista con la primera ficha de la cadena.    
    [(= (second (last cadena)) (first (first lista))) (jugar (rest lista) (append cadena (list (first lista))) 0)] ; verifica la primera ficha de la lista con la ultima ficha de la cadena.
    [(= (first (first cadena)) (first (first lista))) (jugar (rest lista) (append (list (reverse (first lista))) cadena) 0)] ; verifica la primera ficha (de forma invertida) de la lista con la primera ficha de la cadena. 
    [(= (second (last cadena)) (second (first lista))) (jugar (rest lista) (append cadena (list (reverse (first lista)))) 0)] ; verifica la primera ficha (de forma invertida) de la lista con la ultima ficha de la cadena.
    [else (jugar (rest (append lista (list (first lista)))) cadena (+ 1 iteraciones))]))

;; Ejemplo de corrida del juego domino: (jugar lista-por-defecto (list) 0)