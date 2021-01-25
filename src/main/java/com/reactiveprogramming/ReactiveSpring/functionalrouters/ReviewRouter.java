package com.reactiveprogramming.ReactiveSpring.functionalrouters;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ReviewRouter {

    public RouterFunction<ServerResponse> reviewRoutes(ReviewHandler reviewHandler){

        RouterFunction<ServerResponse> responseRouterFunction = RouterFunctions.route(RequestPredicates.GET("").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),reviewHandler::getAllReviews);
        HttpHandler handler = RouterFunctions.toHttpHandler(responseRouterFunction);
//        ReactorHttpHandlerAdapter
        return responseRouterFunction;

    }
}
