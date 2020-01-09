package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class FlatMapOperator {


    Random random = new Random();

    /**
     * Metodo que solicita a cada usuario el libro favorito
     * 1. Genera aleatoriamente una cantidad de valores enteros
     * 2. Cada uno de estos es mapeado a un titulo de un libro
     * 3. Realiza un delay de cada libro de 3 segundos para simular la comunicación con la base de datos
     */
    public Flux<String> requestBooks(String user) {
        return Flux.range(1, random.nextInt(3) + 1)
            .map(i -> "book-" + i)
                .delayElements(Duration.ofMillis(3));
    }

    /**
     * Se combina la ejecución de request books para tres usuarios
     *
     */
    @Test
    void flatMaspOperator(){
        Flux.just("user-1", "user-2", "user-3").flatMap(u -> requestBooks(u)
                .map(b -> u + "/" + b))
                .subscribe(r -> System.out.println("onNext: " + r));
    }
}
