# Proyectos

## reactor-mono
Proyecto donde se explica la creación de publisher Mono mediante una clase test

## reactor-flux
Proyecto donde se explica la creación de publisher Flux mediante una clase test

## reactor-operator
Proyecto donde se explica los operadores soportados por Mono y Flux mediante clases test


# Pluiggins necesarios

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



# Ejercicio WebFlux con Anotaciones
1. Vamos a crear con Spring initializer un nuevo proyecto
- Generado con Gradle
- Java 1.11
- Springboot 2 (la última versión vigente) - Springb webflux solo funciona con Spring 2
- Escoger grupo: co.com.gcode.bookstoreweb
- Agregar los siguientes pluggins

  
