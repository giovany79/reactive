package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class PeekingElementTest {

    /**
     * Usa el operador ConcatWith para concatenar con el evento de error. Recibe tanto eventos
     * de ONext como eventos de OnError
     */
    @Test
    void generateOtherValues()throws Exception{
        Flux.just(1, 2, 3)
                .concatWith(Flux.error(new RuntimeException("Conn error")))
                .doOnEach(s -> System.out.println("signal: "+ s))
                .subscribe();

    }
}
