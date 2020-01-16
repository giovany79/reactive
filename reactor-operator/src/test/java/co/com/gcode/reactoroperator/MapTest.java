package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class MapTest {


    /**
     * Obtiene una lista de numeros del 1 al 10 y a cada numero lo multiplica por 100
     */
    @Test
    void mapSecuencePlusTwo(){
        Flux.range(1,10)
                .map(value -> value * 100)
                .map(value -> value / 10)
                .log()
                .subscribe();
    }


}
