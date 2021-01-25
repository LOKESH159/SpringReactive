package com.reactiveprogramming.ReactiveSpring.functionalrouters;


import com.reactiveprogramming.ReactiveSpring.documents.Reviews;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ReviewHandler {

    public Mono<ServerResponse> getAllReviews(ServerRequest serverRequest){
        return ServerResponse.ok().bodyValue(new Reviews("It's Ok",4));
    }
}
