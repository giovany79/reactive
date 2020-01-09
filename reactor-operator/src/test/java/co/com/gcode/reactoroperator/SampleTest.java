package co.com.gcode.reactoroperator;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class SampleTest {

    /**
     * Metodo que genera eventos secuenciales cada milisegundo  pero solo muestra una fraccion de 20 milisegundos
     */
    @Test
    void generateOtherValues()throws Exception{
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(1))
                .sample(Duration.ofMillis(20))
                .log()
                .subscribe();
        Thread.sleep(9000);
    }
}
