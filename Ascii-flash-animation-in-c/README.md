# Introducción
El propósito del presente trabajo es aprender el funcionamiento, estructura y cominicación de procesos e hilos y como estos son regidos por lo diferentes schedulers como Round-Robin, Sorteo y Tiempo real.

Para lograr este objetivo se realizó una animación de una simulación de naves espaciales viajando entre los diferentes espacios, donde cada nave en el fondo representa un hilo y el movimiento de esta un proceso, aquí es donde entra la implementación propia de los procesos e hilos.

Para hacerlo más interesante, las naves pueden viajar entre monitores mediante un protocolo, donde cada monitor representa un espacio como tal.

## Ambiente de desarrollo

* Lenguaje de programación: C
* Editor de texto: Sublime, Geany, Intellij
* Control de versiones: Github
* Oracle Virtual-Box: Emulador de ubuntu

## Estructuras de datos utilizadas y funciones

**Estructuras:**
- mythread_t
Struct molde de cada hilo.
- mythread_private_t
Struct molde de cada hilo.
- mythread_queue
Struct molde de cada lista de hilos.
- monitor_info
Struct molde de cada monitor.
- monitor_queue
Struct molde de cada lista de monitores.
- item_info
Struct molde de cada nave Ascii.
- config
Struct molde de las configuraciones del programa (lenguaje propuesto).

**Funciones:**
- my_mutex_destroy
Función para eliminar un mutex.
- my_mutex_lock
Función para setear un thread en lock.
- my_mutex_unlock
Función para setear un thread en unlock.
- mythread_chsched
Función para cambiar de scheduler un thread.
- mythread_create
Función crear un thread.
- mythread_detach
Función para desacoplar un thread.
- mythread_exit
Función para salir de un thread.
- mythread_yield
Función para ceder el control de un thread.
- process_language
Función para procesar el lenguaje propuesto.
- scheduler
Función para correr los diferentes tipos de schedulers.
- print_stack
Función para imprimir el stack.
- print_list
Función para imprimir una lista.
- main
Función main del programa.

## Instrucciones para ejecutar el programa

Para ejecutar el programa se debe de compilar el documeto main incluyendo los arcihivos individuales tanto de los pthread, como de los mutexs, schedulers y el GUI.

gcc main.c -o main mythread_.c mymutex_.c mythread.h gui.c -lncurses process_language.c -lncurses scheduler.c -lncurses

## Actividades realizadas por estudiante

**NOTA:** Se dá por hecho que las actividades descritas a continuación fueron realizadas por los tres integrantes del grupo.

* 10-11 noviembre - 4 horas:
Se investiga sobre algún tipo de implementación o referencia sobre hilos y procesos en C.
* 12 noviembre- 6 horas:
Se implementa una creación de threads mediante un ejemplo encontrado en internet.
* 28-29 noviembre - 5 horas:
Se implementa el yield, create, detatch, la cola de procesos y el scheduler.
* 30 noviembre 2 horas:
Permite el cierre de un thread.
* 1 diciembre - 5 horas:
Inclusión del mutex lock, unlock, destroy y el equeleto de proceso de lenguaje.
* 2 diciembre - 8 horas:
Inicio de definicion y parseo del lenguaje propuesto.
Proceso de parseo del lenguaje listo.
Cambios en Round-Robin
* 3 diciembre - 4 horas:
Cambios en propuesta del lenguaje, por ende su parseo.
Implementación de GUI, naves.
* 5 diciembre - 6 horas:
Implementación de exposición de naves.
Implementación de choques de naves.
Cambios en schedulers de tiempo real, round-robin y sorteo.
* 6 diciembre - 7 horas:
Se implementa una simulación de varios monitores.
Unión de código.
Documentación

## Autoevaluación

**Estado final**
El proyecto terminó en un estado funcional donde se implementan los threads, mutex, schedulers en el fondo, elementos los cuales son representados por naves en un espacio mediante el GUI.

**Problemas encontrados**
Problemas de lenguaje debido a poca práctica recientemente.
Comunicación de grupo.
Pobre conocimiento del tema.

**Limitaciones**
Limitaciones como tal creemos que no hubo ninguna, más alla del tiempo que se disponía para dedicarle al proyecto como tal.

**Reporte de commits**
commit adc8161becc5ccdea7b42afcdce978b1d60cb37c
Merge: 6b7539f bc662d8
Author: Parrot user <user@localhost.localdomain>
Date:   Mon Dec 7 05:36:16 2020 +0000

    Merge branch 'main' of https://github.com/Emanlui/Ascii-flash-animation-in-c into main

commit 6b7539f2ecfa7d853150ce937b15f2534dfcf487
Author: Parrot user <user@localhost.localdomain>
Date:   Mon Dec 7 05:35:49 2020 +0000

    Animacion terminada

commit 80ef715ada3909520945e2d3cb30506cf8c32f25
Author: Parrot user <user@localhost.localdomain>
Date:   Mon Dec 7 04:47:36 2020 +0000

    Movimientos terminados en su propio canvas

commit 2ace0ec35a8928962dcb37fccfdac6ec92c9dee8
Author: Parrot user <user@localhost.localdomain>
Date:   Mon Dec 7 02:32:32 2020 +0000

    Las naves se mueven

commit a3b76e549d5b780432b8ee01d03aaf0b5cf41c73
Author: Fabrizio <faoalv8@gmail.com>
Date:   Sun Dec 6 20:13:50 2020 -0600

    Uniendo todo 2.0

commit 07ae1c468e5694e2088ac0b876f5d8301fca5e5c
Author: Parrot user <user@localhost.localdomain>
Date:   Mon Dec 7 01:47:22 2020 +0000

    Estamos uniendo todo 1.0

commit bc662d8eb0c40f31c7581706967b13c87b9a09b1
Merge: 5504d81 80ef715
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 19:13:18 2020 -0600

    merge desde emanlui

commit 5504d81fcc40ae8a43aa98d198dd08c0efe8fd5d
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 19:12:15 2020 -0600

    Finaliza implementacion de planificadores

commit d45ad13367ee3fd9581866dcc34c29fce0447e00
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 18:47:39 2020 -0600

    planificador sorteo terminado

commit 036d6274e9aa4419bee2a6b9a32cf7605b8b3e4c
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 18:31:08 2020 -0600

    algoritmo sorteo encuentra el tiquete ganador

commit b04b6ff2d3179511c446cae29006f97394e4eaed
Merge: 3732615 16bd48b
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 10:36:26 2020 -0600

    merge con round robin

commit 16bd48bab5d521876d9c7d14d52457586d77aecb
Author: Fabrizio <faoalv8@gmail.com>
Date:   Sun Dec 6 14:45:41 2020 -0600

    Se implementan varios monitores

commit 0e3b9598b3a50ce23b61d92a1a3f6a5547d5a2ee
Author: Fabrizio <faoalv8@gmail.com>
Date:   Sat Dec 5 23:00:23 2020 -0600

    Se agregan windows

commit 66584c4c5bbce2b27de3191be343e741940efc5a
Author: Fabrizio <faoalv8@gmail.com>
Date:   Sat Dec 5 20:46:36 2020 -0600

    Explotan las naves

commit a8284d5a19bd59a565231d30bb0ec233a28dd001
Author: Fabrizio <faoalv8@gmail.com>
Date:   Sat Dec 5 20:28:26 2020 -0600

    Se identifican los choques en las naves

commit 9af6eb0315ae7c381a80a9eacd2972260a23fc1a
Author: Parrot user <user@localhost.localdomain>
Date:   Sat Dec 5 23:27:05 2020 +0000

    Se detectan colisiones de naves

commit 37326150430f389d5f760ed247e1bb4aa3305925
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 10:33:07 2020 -0600

    Se agrega el planificador round robin exitosamente

commit 7f506fcfc15abeff0d4066a6ad0875798357d137
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 09:35:26 2020 -0600

    Se eliminan comentarios y se empieza la fase de pruebas

commit 4ad1b5b4b559b5d87e4469f6b78782f4dcb06482
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 09:34:37 2020 -0600

    se agrega el struct de lista de ticketes y atributos a los nodos

commit 5e96b9b12057590cef0ba391a30865f48b769ec2
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 03:21:51 2020 -0600

    implementacion preliminar del planificador sorteo

commit 8d669f1f9f3bd1beb0ea8328532feb938adaf734
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Sat Dec 5 03:21:22 2020 -0600

    Se agrega variable global para scheduler de sorteo

commit 0eb6dd01c2a8199acdf02a29fe8e633c6f5f8e1c
Author: Parrot user <user@localhost.localdomain>
Date:   Fri Dec 4 03:53:06 2020 +0000

    Las naves se mueven

commit e924a5cf5202c551e8b67dbbbdb6d455d884581e
Author: Parrot user <user@localhost.localdomain>
Date:   Fri Dec 4 03:41:16 2020 +0000

    Se agrega el primer gui

commit e85d114e789c9c56fd7268f0c8ea5dadc25c5bb9
Author: Fabrizio <faoalv8@gmail.com>
Date:   Thu Dec 3 20:49:09 2020 -0600

    Correccion de error en lenguaje

commit 95caba26c81f43985ed15208b35b84ed022e9e1b
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Thu Dec 3 00:45:08 2020 -0600

    Se corrige error en process language

commit c89dd38207a8c6b3f1adb567b87e16670fc97fe9
Author: Fabrizio <faoalv8@gmail.com>
Date:   Thu Dec 3 00:19:40 2020 -0600

    Finalizacion de proceso de datos completo

commit a1ac31e4291fbd8f752995b9ae0c9334d683f302
Author: Fabrizio <faoalv8@gmail.com>
Date:   Wed Dec 2 23:50:51 2020 -0600

    Union con el programa en general

commit 83260cccaaa5310f475c187caba2bc4e9b7e818f
Merge: d593fac 15fbb6a
Author: Fabrizio <faoalv8@gmail.com>
Date:   Wed Dec 2 22:57:15 2020 -0600

    Merge remote-tracking branch 'origin/main' into main

commit d593fac86bf56a5d988ef4b56ad4fbd9fd834a3c
Author: Fabrizio <faoalv8@gmail.com>
Date:   Wed Dec 2 22:54:21 2020 -0600

    Finalizacion de proceso de configuracion

commit 8104b18f489d00ca1be7274a8ff53cb778ba796e
Author: Fabrizio <faoalv8@gmail.com>
Date:   Wed Dec 2 22:34:34 2020 -0600

    Procesamiento de configuracion casi listo

commit 15fbb6a2fc9c9a62582ff81cc662d586f86f9a6e
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Wed Dec 2 20:09:04 2020 -0600

    globalizacion de la variable ROUND_ROBIN

commit f15d7bf9c316aaa72ebcf4f19295defe209c50ed
Merge: e46de70 d1a33ae
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Wed Dec 2 20:04:18 2020 -0600

    merged from main

commit e46de70349ee2d8be15a7542d77b9194c5e35df4
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Wed Dec 2 20:02:34 2020 -0600

    No changes

commit 696e0a81520581935266e481fafd0889c8058e31
Author: Kevin Ledezma <kevinledezma20498@gmail.com>
Date:   Wed Dec 2 20:00:43 2020 -0600

    Implementacion de la clase scheduler con el primer intento de Round Robin

commit d1a33ae7bed38352bed43f48c4f6e77d9806133f
Author: Fabrizio <faoalv8@gmail.com>
Date:   Tue Dec 1 11:03:38 2020 -0600

    Inclusion de mutex lock, unlock, destroy y el esqueleto de proceso de lenguaje

commit 6a47bb1e76d2a7091973303c292066c0f758035e
Author: Parrot user <user@localhost.localdomain>
Date:   Tue Dec 1 05:10:16 2020 +0000

    Se puede salir el thread

commit efa5f944a16fc9b83144f76a8a39bd4f127a3e3d
Author: Parrot user <user@localhost.localdomain>
Date:   Mon Nov 30 03:42:38 2020 +0000

    Se termino el sched

commit 7e1b1361cf9ff23fdff4f1a154bf3708496151ad
Author: Parrot user <user@localhost.localdomain>
Date:   Mon Nov 30 02:09:04 2020 +0000

    Se agrego el yield, create, detach y la cola de procesos

commit a50a9df85f0b2193f0cb734079ce6d3f5b5b121e
Author: Parrot user <user@localhost.localdomain>
Date:   Thu Nov 12 06:03:51 2020 +0000

    Se completo el thread create

commit 48bbcc717d7921b1498cfc79b32efec4c8476d96
Author: Emanlui <emanuellejs1999@gmail.com>
Date:   Sun Nov 8 22:46:09 2020 -0600

    Initial commit

**Autoevaluación**
|Rubro|Valor %|Fabrizio|Kevin|Emanuelle
|----------------|-------------------------------|-----------------------------|-----------------------------|-----------------------------|
|Scheduler Round-Robin|5|5|5|5|
|Scheduler Sorteo|5|5|5|5|
|Scheduler Tiempo Real|5|3|3|3|
|Cambio de scheduler|5|3|3|3|
|Funciones de la biblioteca pthreads|10|9|9|9|
|Documentación|20|20|20|20|
|Diseño de lenguaje|10|10|10|10|
|Implementación de la animación|20|20|20|20|
|Funcionamiento en multiples displays|20|5|5|5|
|Extra|10|10|10|10|
|Kick-off|5|5|5|5|
|**Total**|115|95|95|95|


**Fabrizio:**
[1] [2]  3   [4] [5] Aprendizaje de Round Robin. 
[1] [2] [3]  4  [5] Aprendizaje de Tiempo Real. 
[1] [2] [3]  4  [5] Aprendizaje de Cambio de contexto. 
[1] [2]  [3] 4  [5] Aprendizaje de Sorte

**Kevin:**
[1] [2]  3   [4] [5] Aprendizaje de Round Robin. 
[1] [2] [3]  4  [5] Aprendizaje de Tiempo Real. 
[1] [2] [3]  4  [5] Aprendizaje de Cambio de contexto. 
[1] [2]  [3] 4  [5] Aprendizaje de Sorte

**Emanuelle:**
[1] [2]  3   [4] [5] Aprendizaje de Round Robin. 
[1] [2] [3]  4  [5] Aprendizaje de Tiempo Real. 
[1] [2] [3]  4  [5] Aprendizaje de Cambio de contexto. 
[1] [2]  [3] 4  [5] Aprendizaje de Sorte

## Lecciones aprendidas

**Fabrizio:**
Personalmente, creo que el trabajo me sirvió bastante para conocer aún mejor el funcionamiento de los hilos y los procesos realmente, ya que la verdad tenía algunas dudas de la tarea anterior.
Además me gusto la idea del proyecto en general, sobre una emulación de naves donde eran regidas por diferentes entidades (schedulers).
En cuanto a la asiganción como tal creo que si es bastante complicada de realizar, debido a que es un tema del cual no hay muchos ejemplos o implementaciones para aprender lo básico.

**Kevin:**
Durante este proyecto, es fácil recordar que incluso las estructuras de control que estamos tan acostumbrados a utilizar diariamente, pueden siempre ser un desafío nuevo. Específicamente, al trabajar con la abstracción de procesos, ha sido bastante desafiante implementar un sistema de tan alta coordinación entre los procesos.
    Definitivamente si alguien desea realizar esta asignación, va a tener dos grandes contratiempos: las lecturas necesarias para poder entender la abstracción de los planificadores, implementaciones y también el tiempo para investigar implementaciones similares que deberás adaptar a tus necesidades. Incluso con meses de tiempo, siento que un proyecto así nunca quedaría perfectamente terminado, a menos de tener experiencia previa a este conocimiento y ojalá alguna experiencia en implementar este tipo de abstracciones.
    El trabajo en equipo fue sumamente importante y puedo decir que, aunque el trabajo no llegó a un estado completo, el estado actual fue muy positivo de acuerdo al esfuerzo de cada miembro del equipo y la coordinación para acoplar los segmentos de código asignados.

**Emanuelle:**
En mi opinion, el proyecto ayudó para consolidar los conocimiento de relacionados al funcionamiento de los hilos y los procesos, los cuales son indispensables en los sistemas operativos.
En relación al tema de las naves y animación, siempre es bueno aprender nuevas herramientas, tal como ncurses.
Finalmente, creo que sería de utilidad para alguien que esté proximo a realizar el proyecto, el hecho de tener claros los conceptos que se están por implementar, ya que es indispensable para su correcto funcionamiento.

## Bibliografía

_jitesh1337/mythread_lib_. GitHub. (2020). Retrieved 7 December 2020, from https://github.com/jitesh1337/mythread_lib.

Youtube.com. (2020). Retrieved 7 December 2020, from https://www.youtube.com/watch?v=N0ET__T4sc0&ab_channel=StudyExtent.


list, C., bar, i., & Rankin, D. (2020). _C - Linked List - how to assign and go through the list_. Stack Overflow. Retrieved 7 December 2020, from https://stackoverflow.com/questions/52413584/c-linked-list-how-to-assign-and-go-through-the-list.

C++?, H., & Andersen, T. (2020). _How to generate different random numbers in a loop in C++?_. Stack Overflow. Retrieved 7 December 2020, from https://stackoverflow.com/questions/4926622/how-to-generate-different-random-numbers-in-a-loop-in-c.

_continue statement in C - Tutorialspoint_. Tutorialspoint.com. (2020). Retrieved 7 December 2020, from https://www.tutorialspoint.com/cprogramming/c_continue_statement.htm.

_box(3) - Linux man page_. Linux.die.net. (2020). Retrieved 7 December 2020, from https://linux.die.net/man/3/box.

_C Language: floor function (Floor)_. Techonthenet.com. (2020). Retrieved 7 December 2020, from https://www.techonthenet.com/c_language/standard_library_functions/math_h/floor.php.

_Perusall_. App.perusall.com. (2020). Retrieved 7 December 2020, from https://app.perusall.com/courses/operativos-ii2020/sistemas-operativos-modernos?.
