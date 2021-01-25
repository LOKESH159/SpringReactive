package fluxandmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;


public class MonoTest {

    @Test
    public void mono_1(){
        Logger logger=   Loggers.getLogger(MonoTest.class.getName());
        Mono.just("Lokesh").flux().log(logger).parallel().subscribe(System.out::println);
    }

    @Test
    public void mono_Create(){
      Mono.create((x)->{
          System.out.println("hello");
      }).subscribe(System.out::println);
    }
}
