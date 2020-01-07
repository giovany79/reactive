package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Comparator;

public class CollectTest {


    /**
     * Recolecta una secuencia de números y los ordena descendentemente
     */
    @Test
    void collectSorted(){
        Flux.just(1, 6, 2, 8, 3, 1, 5, 1)
                .collectSortedList(Comparator.reverseOrder())
                .subscribe(System.out::println);
    }

    /**
     * Recolecta una secuencia de números y filtra los valores repetidos
     */
    @Test
    void collectDistinc(){
        Flux.just(1, 6, 2, 8, 3, 1, 5, 1)
                .distinct()
                .subscribe(System.out::println);
    }


}
