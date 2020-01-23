:-dynamic
	rpath/2.      % Camino invertido.

arista(a, b, 10, 5).
arista(b, c, 3, 1).
arista(b, e, 5, 2).
arista(b, d, 4, 2).
arista(e, h, 2, 1).
arista(d, h, 1, 0.5). % Base de conocimentos.
arista(c, f, 2, 1).
arista(f, i, 3, 2).
arista(f, j, 3, 2).
arista(g, j, 1, 0.5).
arista(h, f, 1, 0.5).
arista(h, i, 6, 3).

path(From, To, Dist, Com) :-
	arista(To, From, Dist, Com) ;       % Declaracion de caminos bidireccionales.
	arista(From, To, Dist, Com).

shorterPath([H|Path], Dist) :-		       % si el camino es menor al menor camino guarado, entonces lo reemplaza.
	rpath([H|T], D), !, Dist < D,          % coincide con el nodo objetivo [H|_]
	retract(rpath([H|_],_)),
        assert(rpath([H|Path], Dist)).
shorterPath(Path, Dist) :-		       % en otro caso, almacena un nuevo camino.
% Entrada : Un camino y la distancia.
% Salida : El camino mas pequeno.
% Restricciones : El camino debe ser valido
% Descripcion: Busca el menor camino
	assert(rpath(Path,Dist)).

traverse(From, Path, Dist) :-		    % atraviesa todos los nodos accesibles
	path(From, T, D, C),		    % para cada vecino
	not(memberchk(T, Path)),	    % que aun no se ha visitado
	shorterPath([T,From|Path], Dist+D), % actualiza el menor camino y la distancia
	traverse(T,[From|Path],Dist+D).	    % luego atraviesa al vecino

traverse(From) :-
% Entrada : Un nodo
% Salida : Todos los caminos existentes
% Restricciones : Debe ser un nodo valido
% Descripcion : Calcula todos los caminos existentes de un nodo
	retractall(rpath(_,_)),           % remueve las soluciones
	traverse(From,[],0).              % atraviesa desde el origen
traverse(_).

comision([_], 0).            % camino de tamano 0 tiene comision 0
comision([A, B | T], L) :-
% Entrada : Un lista de nodos y una distancia (0)
% Salida : La comision de total de los nodos recorridos
% Rstricciones : Debe ser un camino valido del grafo
% Descripcion : Calcula la comision total entre dos nodos
    path(A, B, D, L1),       % A y B estan conectados directamente, la comision entre ellos es L1
    comision([B|T], L2),     % la comision entre B y el ultimo elemento es L2
    L is L1 + L2.

inicio(From, To) :-
% Entrada : Dos nodos del grafo
% Salida : El camino mas corto, la distancia y la comision entre ellos.
% Restricciones : Nodos deben ser validos
% Descripcion : Busca el menor camino existente entre 2 nodos (Dijkstra)
	traverse(From),                   % busca todas las distancias
	rpath([To|RPath], Dist)->         % si el objetivo fue alcanzado
	  reverse([To|RPath], Path),      % devuelve el camino, la distancia y la comision
	  Distance is round(Dist),
	  comision(Path, Comision),
	  writef('Path = %w  Distancia = %w  Comision = %w\n',
	       [Path, Distance, Comision]);
	writef('There is no route from %w to %w\n', [From, To]).

