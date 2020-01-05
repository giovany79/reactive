# Estructura del proyecto

* **build.gradle:** Archivo que automatiza el manejo de dependencias, compilación y pruebas unitarias

* **.gitignore:** Archivo que ignora archivos de trabajo para ser publicados en github

* **src/test/java/co.com.gcode.reactormono/MonoTest.java:** Archivo con las pruebas unitarias para explicar los conceptos de mono

# Mono
Publica 0 o 1 elementos. Se usa normalmente cuando retorna un solo elemento o retorno void. 

Un mono vacío es útil para emular retornos vacíos en programación tradicional. No retorna un valor pero emite una señal de finalizado para avisarte que el procesamiento ha finalizado

![](https://image.slidesharecdn.com/reactive-card-magic-180905013644/95/reactive-card-magic-understanding-spring-webflux-and-project-reactor-30-638.jpg?cb=1536164915)

# Librerías

## io.projectreactor:reactor-core
https://mvnrepository.com/artifact/io.projectreactor/reactor-core
https://projectreactor.io/docs/core/release/reference/index.html#getting

Implementación de la librería Reactive Stream

## io.projectreactor:reactor-test
https://mvnrepository.com/artifact/io.projectreactor/reactor-test

Libreria especializada para las pruebas unitarias de reactor

## org.junit.jupiter:junit-jupiter-api
https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
https://junit.org/junit5/

Versión actualizada de Junit centrada en java 8 y superior
