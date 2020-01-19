## Proyecto bookstoreweb con anotaciones

# Pluggins

# Librerías

# Componentes

## build.gradle
Archivo principal de gradle para la compilación, pruebas unitarias y generación del artefacto.

## ApplicationBookStoreWeb.java
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

