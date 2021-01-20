package com.reactiveprogramming.ReactiveSpring.controllers;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import javax.annotation.security.RunAs;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest
class FluxAndMonoControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void fluxUsingApplicationJson_Approach1() {

      Flux<Integer> integerFlux =  webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1,2,3,4)
                .verifyComplete();
    }

    @Test
    void streamFluxData_Approach2() {

         webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                 .hasSize(4);


    }

    @Test
    void streamFluxData_Approach3() {

        List<Integer> expectedOutput = Arrays.asList(1,2,3,4);

      EntityExchangeResult<List<Integer>> entityExchangeResult = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();
      assertEquals(expectedOutput,entityExchangeResult.getResponseBody());

    }

    @Test
    void streamFluxData_Approach4() {

        List<Integer> expectedOutput = Arrays.asList(1,2,3,4);

        Flux<Integer> integerFlux  = webTestClient.get()
                .uri("/fluxStream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .thenCancel()
                .verify();

    }


    @Test
    public void mono_Approach1(){
        webTestClient.get()
                .uri("/mono")
                .accept(MediaType.APPLICATION_JSON)

                .exchange()
                .expectStatus().isOk()

                .expectBody(Integer.class)
                .consumeWith((response)->{
                    assertEquals(1,response.getResponseBody());
                });
    }

}