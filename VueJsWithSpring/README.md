# Proyecto de Diseño

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

El proyecto es una aplicación de hoteles creada con spring-boot y vue.js

# Instalación:

> Datos importantes, debe estar instalado spring junto con vue.js y sus dependencias 
> (Node JS y demás). Para el uso de spring se puede instalar intellij y los plug-ins
> estarán en la sección de settings.

  - Clonar el repo al escritorio.
  - Descomprimirlo.
  - Abrir intellij y configurar el método de ejecución, este debe estar corriendo al main.
- Se debe configurar correctamente el sdk e instalar las dependencias de maven correctas. 
- Disfrutar de la aplicación.

# Componentes

| Nombre del componente | Para qué sirve |
| ------ | ------ |
| Daos | Sirve para poder hacer consultas a la base de datos |
| Service | Sirve como una clase para la lógica del programa |
| Controller | Es el componente para poder hacer funciones REST |
| Modelo | Es el objeto que se utilizará durante todo el programa |


# ¿Qué hace la aplicación?

La aplicación es un sistema web para reservar hoteles, tiene una cantidad de funciones, que entre las cuales están:

- Listar todos los hoteles disponibles
- Listar los hoteles disponibles filtrados por puntuación 
- Listar los hoteles disponibles filtrados por país
- Listar los hoteles disponibles filtrados por región
- Buscar un hotel en particular por nombre, mediante una barra de búsqueda (en este caso, se puede colocar solo un fragmento del nombre del hotel).
- Listar todas las imágenes disponibles de hotel.
- Listar el nombre del hotel.
- Listar la región del hotel.
- Listar el país donde se ubica el hotel.
- Listar la puntuación del hotel.
- Listar todos los tipos de habitaciones disponibles para dicho hotel.
- Listar las imágenes disponibles para las habitaciones.
- Listar el precio de dicha habitación.
- Poder seleccionar un rango de fechas, y que cuando el rango de fechas esté seleccionado, aparezca un mensaje indicando si la habitación va a estar disponible o no, en ese rango de fechas.
- Poder hacer un POST con los siguientes campos: Nombre Completo, Fecha de nacimiento, Número de tarjeta de crédito.
- Listar cuáles usuarios hicieron una reservación
- Listar en cuáles hoteles se hicieron las reservaciones
- Listar en cuáles habitaciones se hicieron reservaciones.
- Listar reservaciones por medio de un rango de fechas.

# Dependencias

La siguiente lista son las dependencias del proyecto: 

| Plugin | Versión |
| ------ | ------ |
| com.google.code.gson | 2.8.5 |
| org.springframework.boot | Última versión por defecto |
| org.springframework.boot | Última versión por defecto |
| org.springframework.boot | Última versión por defecto |
| com.h2database | Última versión por defecto |
| org.springframework.boot | Última versión por defecto |
|javax.xml.bind|2.3.0|
|org.postgresql|42.2.8|
|org.springframework.security.oauth| Última versión por defecto |
|spring-boot-starter-security| Última versión por defecto |

# Creación del ejecutable (jar File)

Para poder crear un ejecutable simplemente se debe poner este comando en la terminal de intellij: 

```sh
$ mvn clean package
```

Esto va reconstruir el proyecto y agregar un jar en la carpeta de "Target".

# ¿Cómo correr el ejecutable?

Para poder ejecutar el programa, primero debemos tener instalado y configurado java en el sistema https://www.java.com/en/download/

Finalmente se deben correr los siguientes comandos para poder correr el programa:

```sh
$ cd C:\Users\{NombreDeUsuario}\Downloads\develop\proyecto_diseno\target
$ java -jar {NombreDelJar}.jar 
```
# Problemas conocidos

Existen varios problemas con respecto a la instalación del proyecto, entre estos están:

- Spring mal configurado.
- Sdk mal configurado.
- El main no se encuentra `Error: Could not find or load main class libTest`, para solucionar esto se debe eliminar la carpeta .idea o sino reconstruir el proyecto como un maven.

# Referencias 

| Información | Link |
| ------ | ------ |
|Cómo crear ejecutables|https://stackoverflow.com/questions/33593111/intellij-spring-boot-how-to-create-an-executable-jar|
|Cómo utilizar Spring|https://www.youtube.com/watch?v=35EQXmHKZYs|
|Cómo hacer Post y Gets |https://www.baeldung.com/spring-rest-template-list|
