package co.com.gcode.reactorflux;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class FluxTest {

    /**
     * Crea un flux que publica tres elementos de nombre de personas
     */
    @Test
    void firstFlux(){
        Flux.just("Andres", "Jose", "Ricardo")
                //.log()
                .subscribe(e -> System.out.println(e));

    }

    /**
     * Crea un flux de objetos de nombre desde un iterable. Es este caso la lista es
     * pasada como un solo objeto tipo array
     */
    @Test
    void fluxFromIterableAsOneElement(){
        Flux.just(Arrays.asList("Andres", "Jose", "Ricardo"))
                .log()
                .subscribe();
    }

    /**
     * Crea un flux de objetos de nombre desde un iterable. En este caso cada objeto de la lista es
     * procesado como un elemento individual del stream
     */
    @Test
    void fluxFromIterableAsSeveralElement(){
        Flux.fromIterable(Arrays.asList("Andres", "Jose", "Ricardo"))
                .log()
                .subscribe();
    }

    /**
     *Crea un flux de objetos enteros en un rango de 1 a 10
     */
    @Test
    void fluxFromRange(){
        Flux.range(1,10)
                .log()
                .subscribe();
    }


    /**
     *Crea un flux de en un intervalo de tiempo de segundos de 1. No se muestra nada
     * debido a que este metodo corre en otro hilo, por lo cual no bloquea el hilo actual.
     * Cuando la clase test finaliza todos los hilos son killed  y nada pasa en este caso.
     * Para poder observarlo se debe adicionar un sleep de 5000 milisegundos con el fin de
     * poder ver el momento en el que el otro hilo publica los stream
     * Este flux nunca terminara. La unica razon para detenerse es que el hilo principal es killed
     * cuando finaliza el programa. En un webserver, siempre estaría corriendo generando streams de
     * manera infinita. Para prevenir esto se usa la función take
     * @throws Exception
     */
    @Test
    void fluxFromIntervalTime() throws Exception{
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .subscribe();
        //Thread.sleep(10000);
    }

    /**
     * Crea un flux de en un intervalo de tiempo de segundos de 1. Toma solo 5 elementos del stream. luego
     * cancela la subscripción. En este caso no se maneja backpresure
     * @throws Exception
     */
    @Test
    void fluxFromIntervalTimeWithTake() throws Exception{
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .take(2)
                .subscribe();
        //Thread.sleep(5000);
    }

    /**
     *Crea un flux de objetos enteros en un rango de 1 a 10, pero le solicito solo 3 elementos.
     * Se emprea el metodo request en el objeto subscripción para la solicitud de los 3 elementos.
     * El primer parametro null es para hacer algo en el tiempo de la subscripción
     * No se llama el metodo onComplete porque solo se solicitó 3 elementos
     */
    @Test
    void fluxRequest() throws Exception{
        Flux.range(1,20)
                .log()
                .subscribe(null,null,null,s->s.request(3));
        //Thread.sleep(10000);
    }

    /**
     * Crea un flux de objetos en un rango de 1 a 10 limitando el envío de elementos a grupos fijo de a 3
     * elementos. El metodo request es llamado cada 3 elementos hasta que el flux se haya completado
     */
    @Test
    void fluxLimitRate(){
        Flux.range(1,10)
                .log()
                .limitRate(2)
                .subscribe();
    }


}
