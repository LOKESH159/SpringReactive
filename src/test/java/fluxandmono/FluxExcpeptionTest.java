package fluxandmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxExcpeptionTest {

    @Test
    public void fluxErrorResume(){
       Flux<String> stringFlux =  Flux.just("A","B","C")
               .concatWith(Flux.error(new RuntimeException("Exception occ")))
               .onErrorResume((e)->{
                   System.out.println("catched excption ::"+e.getMessage());
                   return Flux.just("Publisher is the return");
               });

        StepVerifier.create(stringFlux.log())
                .expectNext("A","B","C","Publisher is the return")
                .verifyComplete();
    }

    @Test
    public void fluxErrorReturn(){
        Flux<String> stringFlux =  Flux.just("A","B","C")
                .concatWith(Flux.error(new RuntimeException("Exception occ")))
                .onErrorReturn("Publisher is the return");

        StepVerifier.create(stringFlux.log())
                .expectNext("A","B","C","Publisher is the return")
                .verifyComplete();
    }
}
