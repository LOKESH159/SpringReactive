package fluxandmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void testFlux(){
        //Subscription --> request() ---->onNext() ----> onComplete()
       Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar").log();
       fluxString.subscribe(System.out::println);

    }

    @Test
    public void testFluxWithException(){
        //Subscription --> request() ---->onNext() ----> onComplete()
        Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar")
                .concatWith(Flux.error(new RuntimeException("Exception throwing")))
                .log();
        fluxString.subscribe(System.out::println,(exc)->System.out.println("catched exception ::"+ exc.getMessage()));

    }

    @Test
    public void testFluxWithOnComplete(){
        //Subscription --> request() ---->onNext() ----> onComplete()
        Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar")
                .concatWith(Flux.just("Testing on complete"))
                .log();
        fluxString.subscribe(System.out::println,(exc)->System.out.println("catched exception ::"+ exc.getMessage()),()->{
            System.out.println("On Complete Executed");
        });

    }


    //TestCasesForFlux

    @Test
    public void testFluxVerfiyData(){
        //Subscription --> request() ---->onNext() ----> onComplete()
        Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar").log();
        StepVerifier.create(fluxString)
                .expectNext("Talari")
                .expectNext("Lokesh")
                .expectNext("Kumar")
                .verifyComplete();

    }

    @Test
    public void testFluxVerfiyDataWithExpectNextCount(){
        //Subscription --> request() ---->onNext() ----> onComplete()
        Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar").log();
        StepVerifier.create(fluxString)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    public void testFluxVerfiyDataWithError(){
        //Subscription --> request() ---->onNext() ----> onComplete()
        Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar").concatWith(Flux.error(new RuntimeException("Exception throwing")));
        StepVerifier.create(fluxString)
                .expectNextCount(3)
                .expectError()
        .verify();

    }







}
