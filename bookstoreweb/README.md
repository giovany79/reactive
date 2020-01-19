# Proyecto bookstoreweb con anotaciones

  Aplicación  para la administración de una librería que permitirá realizar las siguientes operaciones:
  - Consultar todos los libros
  - Consultar un libro exacto
  - Adicionar libros a la libreria
  - Actualizar información de un libro
  - Eliminar un libro
  
  Los libros constan de la siguiente información:
  - titulo
  - Autor
  - Año de publicación
  - Número de páginas
  - Precio
  
 # Pluggins
  
  ## apply plugin: 'io.spring.dependency-management'
  Controla la versión de las dependencias directas y transitivas del proyecto y permite manejar exclusiones de 
  librerías.
  https://docs.spring.io/dependency-management-plugin/docs/current-SNAPSHOT/reference/html/
  

  ## apply plugin: 'java'
  Adiciona capacidades para compilación y testing a un proyecto.
  
  https://docs.gradle.org/current/userguide/java_plugin.html
  

  ## apply plugin: 'idea'
  Genera archivos que son usados por Intellij para hacer posible abrir el proyecto en intellij.
  https://docs.gradle.org/current/userguide/idea_plugin.html
  
  ## apply plugin: 'org.springframework.boot'
  Permite crear aplicaciones basadas en spring stand-alone que tu ejecutas fácilmente con poca configuración.
  https://plugins.gradle.org/plugin/org.springframework.boot

# Librerías

## org.projectlombok
Librería que simplifica la codificación evitando escribir getters, setters, constructores y variables de logs. En tiempo de compilación genera dichos métodos.

https://projectlombok.org/

## org.junit.jupiter
Es la combinacion de el nuevo modelo de programación y la extension del modelo para escribir test en Junit 5. Properciona TestEngine para correr test basados en Junit en la plataforma

https://junit.org/junit5/

## org.springframework.boot:spring-boot-starter-webflux
Adiciona todas las librerias de spring requeridas
* spring-boot y spring-boot-starter para configuración básico de springboot
* spring-webflux:  framework de webflux
* reactor-core: Librerías necesarias para trabajar con el framework de reactor

https://spring.io/projects/spring-boot
https://github.com/spring-projects/spring-framework

## org.springframework:spring-context
Interfase que proporciona configuración a la aplicación

https://spring.io/projects/spring-boot
https://github.com/spring-projects/spring-framework

## org.springframework.boot:spring-boot-starter-data-mongodb-reactive
Es una librería spring-boot-starter para usar base de datos mongodb reactivo. Setea las propiedades de logeo

https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb-reactive/2.0.0.M1

## de.flapdoodle.embed:de.flapdoodle.embed.mongo'
Base de datos mongodb enbebida usada para pruebas unitarias. Para este ejercicio se va  usar como la base de datos principal

https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo

# Componentes

### build.gradle
Archivo principal de gradle para la compilación, pruebas unitarias y generación del artefacto.

### ApplicationBookStoreWeb.java
Clase con que lanza la aplicación springboot. Adicionalmente contiene el método ** commandLineRunner** que permite ejecutar código una vez inicialice la aplicación. Para este caso se inicializa la base de datos con tres registros de libros:
* Einstein
* Elon Musk
* Por otro camino

## Paquete controller

### BookController.java
Clase controladora encargada de enrutar las peticiones http a los respectivos métodos

## Paquete model

### Book.java
Clase con la definición de entidad de libro. Usa la libreria lombok para simplificar la entidad

## Paquete repository

### BookRepository.java
Interfase que extiende de ReactiveMongoRepository lo cual me permite tener las operaciones crud para la entidad book

# Operaciones

## GetAllBooks
Obtiene todos los libros de la librería
   http://localhost:8080/books

### Request Body
NA

### Response body
```json
[
    {
        "id": "5e2486954595ff7d34ace5bc",
        "title": "Por otro camino",
        "author": "Carlos Raul Yepes",
        "yearPublisher": 2016,
        "pages": 244,
        "price": 49000.0
    },
    {
        "id": "5e2486954595ff7d34ace5bb",
        "title": "Elon Musk",
        "author": "Ashlee Vance",
        "yearPublisher": 2015,
        "pages": 400,
        "price": 60000.0
    },
    {
        "id": "5e2486954595ff7d34ace5ba",
        "title": "Einstein",
        "author": "Walter Isaacson",
        "yearPublisher": 2007,
        "pages": 675,
        "price": 90000.0
    }
]
````

## GetBook
Obtiene un libro especifico según el código
http://localhost:8080/books/5e2486954595ff7d34ace5bc

## RequestBody
NA

## Response Body
```json
{
    "id": "5e2486954595ff7d34ace5bc",
    "title": "Por otro camino",
    "author": "Carlos Raul Yepes",
    "yearPublisher": 2016,
    "pages": 244,
    "price": 49000.0
}
```

