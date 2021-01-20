package com.reactiveprogramming.ReactiveSpring.functionalrouters;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterClass {

    @Bean
    public RouterFunction<ServerResponse> routes(HandlerClass handlerClass){
       return RouterFunctions
               .route(RequestPredicates.GET("/router/mono").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handlerClass::monoRouterHandler)
               .andRoute(RequestPredicates.GET("/router/flux").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handlerClass::fluxRouterHandler);
    }


}
