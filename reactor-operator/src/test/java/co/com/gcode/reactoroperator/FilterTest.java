package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FilterTest {

    /**
     * Filtra de una secuencia de 1 a 10 los numeros pares, dejando solo los impares
     */
    @Test
    void filterOddNumbers(){
        Flux.range(1,10)
                .filter(value -> (value % 2)!=0)
                .log()
                .subscribe();
    }
}
