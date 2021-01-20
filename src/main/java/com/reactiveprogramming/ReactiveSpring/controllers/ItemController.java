package com.reactiveprogramming.ReactiveSpring.controllers;


import com.reactiveprogramming.ReactiveSpring.documents.Item;
import com.reactiveprogramming.ReactiveSpring.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public Flux<Item> getAllItems(){
       return itemRepository.findAll();
    }

    @GetMapping("/item/{id}")
    public Mono<ResponseEntity<Item>> getItemById(@PathVariable("id")String id){
        return itemRepository.findById(id).map(item-> ResponseEntity.status(200).body(item)).defaultIfEmpty(ResponseEntity.status(404).body(null));
    }
}
