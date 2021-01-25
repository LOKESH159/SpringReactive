package com.reactiveprogramming.ReactiveSpring.functionalrouters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactiveprogramming.ReactiveSpring.documents.Item;
import com.reactiveprogramming.ReactiveSpring.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class ItemHandler {

    @Autowired
    ItemRepository itemRepository;

    public Mono<ServerResponse> getAllItems(ServerRequest serverRequest){
       return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(itemRepository.findAll().log().delayElements(Duration.ofSeconds(2)),Item.class);
    }

    public Mono<ServerResponse> getItemById(ServerRequest serverRequest){
        String id =  serverRequest.pathVariable("id");
        return ServerResponse.ok().body(itemRepository.findById(id).log(),Item.class);
    }

    public Mono<ServerResponse> createItem(ServerRequest serverRequest){
        Mono<Item> itemMono = serverRequest.bodyToMono(Item.class);
         return itemMono.flatMap(item ->  ServerResponse.ok().body(itemRepository.save(item),Item.class));
    }

    public Mono<ServerResponse> deleteItemById(ServerRequest serverRequest){
        String id =  serverRequest.pathVariable("id");
        System.out.println(id);
        itemRepository.deleteById(id);
        return ServerResponse.noContent().build();
    }
}
