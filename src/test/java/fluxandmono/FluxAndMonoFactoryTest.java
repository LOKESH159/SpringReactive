package fluxandmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class FluxAndMonoFactoryTest {

    List<String> names = Arrays.asList("Lokesh","Ravi","Phani");

    @Test
    public void fluxUsingIterable(){
      Flux<String> namesFlux =   Flux.fromIterable(names);
        StepVerifier.create(namesFlux)
                .expectNext("Lokesh","Ravi","Phani")
                .verifyComplete();
    }

    @Test
    public void fluxUsingArray(){
        Flux<Object> namesFlux =   Flux.fromArray(names.toArray());
        StepVerifier.create(namesFlux)
                .expectNext("Lokesh","Ravi","Phani")
                .verifyComplete();
    }


}
