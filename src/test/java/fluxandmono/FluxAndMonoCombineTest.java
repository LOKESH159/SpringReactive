package fluxandmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoCombineTest {

    @Test
    public void combineFluxAndMono(){

        //IN this scenario it won't give in order
        Flux<String> stringFlux1 = Flux.just("A","B","C");
        Flux<String> stringFlux2 = Flux.just("D","E","F");
        Flux<String> combinedFlux = Flux.merge(stringFlux1,stringFlux2);
        StepVerifier.create(combinedFlux.log())
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void combineFluxAndMono_UsingConcat(){

        //IN this scenario it won't give in order
        Flux<String> stringFlux1 = Flux.just("A","B","C");
        Flux<String> stringFlux2 = Flux.just("D","E","F");
        Flux<String> combinedFlux = Flux.concat(stringFlux1,stringFlux2);
        StepVerifier.create(combinedFlux.log())
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void combineFluxAndMono_UsingZip(){

        //IN this scenario it won't give in order
        Flux<String> stringFlux1 = Flux.just("A","B","C");
        Flux<String> stringFlux2 = Flux.just("D","E","F");
        Flux<String> combinedFlux = Flux.zip(stringFlux1,stringFlux2,(t1,t2)-> t1.concat(t2));
        StepVerifier.create(combinedFlux.log())
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }
}
