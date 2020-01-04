package co.com.gcode.reactormono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    /**
     * Publica un solo elemento A. No se pueden pasar mas de 1 elemento
     */
    @Test
    void firstMono(){
        Mono.just("A")
        .log() // Usa Log4j para dejar registro de eventos
        .subscribe(); // No pasa nada si no se pone la sentencia subscribe
    }


    /**
     * Publica un solo elemento A. Utiliza la interface del subscribe que usa el consumer
     */
    @Test
    void monoWithConsumer(){
        Mono.just("A")
                .log() // Usa Log4j para dejar registro de eventos
                .subscribe(s -> System.out.println(s)); // Se usa la implementación del consumer
    }


    /**
     * Publica un solo elemento A. Muestra el comportamiento en cada evento
     */
    @Test
    void monoWithDoOn(){
        Mono.just("A")
                .log() // Usa Log4j para dejar registro de eventos
                .doOnSubscribe(sub -> System.out.println("Subscribed: " + sub))
                .doOnRequest(req -> System.out.println("Requst: " + req))
                .doOnSuccess(suc -> System.out.println("Complete: " + suc))
                .subscribe(System.out::println);
    }

    /**
     * Publica un vacio
     */
    @Test
    void emptyMono(){
        Mono.empty() // crea un mono vacio
                .log() // Usa Log4j para dejar registro de eventos
                .subscribe(System.out::println); // No pasa nada si no se pone la sentencia subscribe
    }


    /**
     * Publica un vacio con un consumidor
     */
    @Test
    void emptyCompleteConsumerMono(){
        Mono.empty() // crea un mono vacio
                .log() // Usa Log4j para dejar registro de eventos
                .subscribe(System.out::println,
                        null,
                        () -> System.out.println("Done"));
    }


    /**
     * Método para simular una Runtime excepción
     */
    @Test
    void errorRuntimeExceptionMono(){
        Mono.error(new RuntimeException())
                .log()
                .subscribe();
    }


    /**
     * Método para simular una Excepción
     */
    @Test
    void errorConsumernMono(){
        Mono.error(new Exception())
                .log()
                .subscribe(System.out::println,
                        e -> System.out.println("Error: " + e));
    }


    /**
     * Método para simular un catch de una excepcion devolviendo un nuevo Mono
     */
    @Test
    void catchErrorResume(){
        Mono.error(new Exception())
                .onErrorResume(e-> {
                    System.out.println("Catch: " + e);
                    return Mono.just("B");
                })
                .log()
                .subscribe();
    }
}
