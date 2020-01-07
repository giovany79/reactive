package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class ReduceTest {

    /**
     * Metodo que determina si almenos un numero cumple con la condicion de ser par
     */
    @Test
    void reduceNotOddNumber(){
        Flux.just(3, 5, 7, 9, 11, 15, 16, 17)
                .any(e -> e % 2 == 0)
                .subscribe(hasEvens -> System.out.println("Has evens: " + hasEvens));
    }


    /**
     * Metodo que suma los numeros del 1 al 5
     */
    @Test
    void reduceSumRange(){
        Flux.range(1, 5)
                .reduce(0, (acc, elem) -> acc + elem).subscribe(result -> System.out.println("Result: " +  result));
    }



    /**
     * Ingresa los valores 1 2 y 3 y genera los valores 4 y 5.
     * Lambda en el metodo subscribe muestra 4 y 5 a pesar de que 1 2 y 3 son generados y procesados por el stream
     */
    @Test
    void generateOtherValues(){
        Flux.just(1, 2, 3)
                .thenMany(Flux.just(4, 5))
                .subscribe(e -> System.out.println("onNext: " + e));
    }
}

