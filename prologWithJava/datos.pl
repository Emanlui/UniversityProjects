% Esta es toda la informacion de la base de conocimientos

ordenAve(coraciiformes) :- (continente(america); continente(africa)),alimentacion(carnivoros), vuelan(yes),patas(pequenas), pico(mediano).
ordenAve(abubilla) :-  (continente(europa); continente(africa)),alimentacion(carnivoros),vuelan(yes),  diurno(yes), patas(pequenas),pico(largo),ala(larga),cola(pequena).
ordenAve(trogonidae) :-  (continente(america); continente(asia)),alimentacion(herbivoros), vuelan(yes), diurno(yes),patas(pequenas),  cola(pequena).
ordenAve(strigiformes) :- continente(america),alimentacion(carnivoros) , vuelan(yes), diurno(no), patas(largas),pico(largo),ala(larga).
ordenAve(coliidae) :- continente(europa),alimentacion(herbivoros), vuelan(yes), diurno(yes),patas(pequenas), pico(pequeno), ala(pequena), cola(pequena).
ordenAve(cariomidae) :-   alimentacion(carnivoros),vuelan(no), diurno(yes), patas(largas), cola(larga).
ordenAve(accipitridos) :- (continente(antartica);continente(artico);continente(oceania)), alimentacion(carnivoros),vuelan(yes), diurno(yes),  pico(pequeno),ala(larga), cola(mediana).
ordenAve(gavia) :- (continente(europa); continente(america)), alimentacion(carnivoros), vuelan(no), diurno(yes), patas(largas), pico(largo).
ordenAve(struthioniformes) :- continente(oceania), (alimentacion(herbivoros); alimentacion(carnivoros)), vuelan(no), diurno(yes), patas(largas), pico(pequeno).
ordenAve(passeriformes) :- (continente(africa);continente(europa)),alimentacion(carnivoros), vuelan(yes),diurno(yes),patas(pequenas), pico(pequeno),cola(pequena).
:- dynamic(known/3).
:- discontiguous menuask/3.
:- discontiguous ask/2.

% Esta el parte del menu, tiene 3 parámetros, cuando se llama a ask este verifica si el valor existe o no (booleano)
continente(X) :- menuask(continente, X, [europa, asia, africa, america, artico, antartica, oceania]).
alimentacion(X) :- menuask(alimentacion, X,[carnivoros, herbivoros]).
vuelan(X) :- ask(vuelan, X).
diurno(X) :- ask(diurno, X).
patas(X) :- menuask(patas, X, [pequenas, largas]).
pico(X) :- menuask(pico, X,[largo, mediano, pequeno]).
ala(X) :- menuask(ala, X, [pequena, larga]).
cola(X) :- menuask(cola, X, [larga, mediana, pequena]).

% Si en la base de conocimientos encuentra un SI, entonces retorna el valor
ask(Attr, Val) :- known(yes, Attr, Val), !.
menuask(Attr, Val, _) :- known(yes, Attr, Val), !.

% En caso de que el valor no exista en la base
ask(Attr, Val) :- known(_, Attr, Val), !, fail.
menuask(Attr, Val, _) :- known(_, Attr, Val), !, fail.

% Esta validación es en caso de que el valor no sea el mismo, y lo cambie
ask(Attr, Val) :- known(yes, Attr, V), V \== Val, !, fail.
menuask(Attr, Val, _) :- known(yes, Attr, V), V \== Val, !, fail.

% En caso de que el valor NO esté guardado en la base de conocimientos
ask(Attr, Val) :- write(Attr:Val), write('? '), read(Ans), asserta(known(Ans, Attr, Val)), Ans == yes.
menuask(Attr, Val, List) :- write('Cuál es el valor de: '),
write(Attr), write('? '), nl,
 write(List), nl,
 read(Ans),
 % Verifica si el valor es correcto
 check_val(Ans, Attr, Val, List),
 % Lo inserta en la base
 asserta(known(yes, Attr, Ans)),
 Ans == Val.

 % Esta funcion verifica si el valor se encuentra en la lista
check_val(Ans, _, _, List) :- member(Ans, List), !.
check_val(Ans, Attr, Val, List) :- write(Ans),
write(' No es una respuesta valida.'), nl,
 menuask(Attr, Val, List).

 %Esta es la funcion principal
go(Ave) :- ordenAve(Ave), write('El orden del ave es: '), write(Ave), nl.
