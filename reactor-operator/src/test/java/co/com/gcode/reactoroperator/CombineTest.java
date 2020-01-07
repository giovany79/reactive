package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class CombineTest {

    /**
     * Combina varios streams en orden secuencial
     */
    @Test
    void concatStream(){
        Flux.concat( Flux.range(1, 3),
                     Flux.range(4, 2),
                     Flux.range(6, 5)
        ).subscribe(e -> System.out.println("onNext: " + e));

    }


    /**
     * Combina varios streams ordenandolos en orden de llegada
     */
    @Test
    void mergeStream(){
        Flux.merge( Flux.range(1, 3),
                Flux.range(4, 2),
                Flux.range(6, 5)
        ).subscribe(e -> System.out.println("onNext: " + e));

    }


    /**
     * Combina varios streams de diferentes fuentes
     */
    @Test
    void zipStream(){
        Flux.zip( Flux.just("A", "B", "C"),
                Flux.range(1, 3)
        ).subscribe(e -> System.out.println("onNext: " + e));

    }
}
