package com.reactiveprogramming.ReactiveSpring.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxAndMonoController {


    @GetMapping(value = "/flux",produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Integer>  fluxUsingApplicationJson(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/fluxStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer>  streamFluxData(){
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/mono",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Integer>  monoluxData(){
        return Mono.just(1)
                .log();
    }

}
