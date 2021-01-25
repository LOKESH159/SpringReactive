package com.reactiveprogramming.ReactiveSpring.functionalrouters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ItemRouter {

    @Bean
    public RouterFunction<ServerResponse> itemRoute(ItemHandler itemHandler){
      return   RouterFunctions.route(
                RequestPredicates.GET("/flux/items")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)),itemHandler::getAllItems)
              .andRoute(RequestPredicates.GET("/flux/items/{id}")
              .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)),itemHandler::getItemById)
        .andRoute(RequestPredicates.POST("/flux/items")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),itemHandler::createItem)
              .andRoute(RequestPredicates.DELETE("/flux/items/{id}")
                      .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),itemHandler::deleteItemById);
    }
}
