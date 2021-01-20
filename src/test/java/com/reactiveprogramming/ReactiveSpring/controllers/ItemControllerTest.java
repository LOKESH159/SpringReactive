package com.reactiveprogramming.ReactiveSpring.controllers;

import com.reactiveprogramming.ReactiveSpring.documents.Item;
import com.reactiveprogramming.ReactiveSpring.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@AutoConfigureWebTestClient
//@DataMongoTest
@SpringBootTest
class ItemControllerTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WebTestClient webTestClient;

    public List<Item> data(){
        return Arrays.asList(new Item("101","Book",20.0),
                new Item("102","Pen",30.0));
    }

    @BeforeEach
    void setUp() {
        itemRepository.deleteAll().thenMany(Flux.fromIterable(data())).flatMap(itemRepository::save).blockLast();
    }

    @Test
    void getAllItems() {
        List<Item> itemsList  = webTestClient.get().uri("/items").exchange().expectBodyList(Item.class).returnResult().getResponseBody();
        itemsList.stream().forEach(item -> System.out.println(item.getName()));
    }

    @Test
    void getItemById() {
        Item item = webTestClient.get().uri(uriBuilder -> uriBuilder.path("/item/{id}").build("101"))
                .exchange().expectBody(Item.class).returnResult().getResponseBody();
        System.out.println(item.getName());

    }

    @Test
    void getItemById_UsingRetrieve() {
       webTestClient.get().uri(uriBuilder -> uriBuilder.path("/item/{id}").build("101"));

        WebClient.create("").get().exchange().

    }
}