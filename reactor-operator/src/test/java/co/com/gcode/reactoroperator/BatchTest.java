package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.LinkedList;

public class BatchTest {


    /**
     * Realiza un batch de elementos agrupandolos en grupos de 4 elementos
     */
    @Test
    void batchStream(){
        Flux.range(1, 13)
                .buffer(4)
                .subscribe(e -> System.out.println("onNext: " +  e));

    }

    /**
     * Crea una nueva ventana cada que cumple la condición, en este caso que sea divisible por 5
     */
    @Test
    void windowStream(){
        Flux<Flux<Integer>> windowedFlux = Flux.range(102, 20)
                                               .windowUntil(e -> (e % 2) == 0);
        windowedFlux.subscribe(window -> window .collectList()
                .subscribe(e -> System.out.println("window: " +  e)));
    }




}
