package fluxandmono;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Comparator;
import java.util.function.LongConsumer;

public class FluxAndMonoTest {

    @Test
    public void testFlux(){
        //Subscription --> request() ---->onNext() ----> onComplete()
//       Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar").log();
//       fluxString.subscribe(System.out::println);

        Publisher<String> fluxString1 =  Flux.just("Talari","Lokesh","Kumar","Ravi").log();
//        ((Flux<String>) fluxString1).subscribe(System.out::println,err-> System.out.println(err.getMessage()),()-> System.out.println("completed"));
       ((Flux<String>) fluxString1).collectSortedList(Comparator.naturalOrder()).subscribe(System.out::println);

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


    @Test
    public void  multipeSubscribers(){
        Flux<String> fluxString =  Flux.just("Talari","Lokesh","Kumar");

        int  i=0;
        while(i < 10){
            fluxString.log("Step ::"+i).subscribe(System.out::println);
            i++;
        }
    }







}
