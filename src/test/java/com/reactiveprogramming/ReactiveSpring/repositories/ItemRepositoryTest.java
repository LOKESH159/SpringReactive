package com.reactiveprogramming.ReactiveSpring.repositories;

import com.reactiveprogramming.ReactiveSpring.documents.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@DataMongoTest

public class ItemRepositoryTest {


    @Autowired
    ItemRepository itemRepository;

    List<Item>  itemsList = Arrays.asList(new Item("101","Book",20.0),new Item("102","Pen",20.0));

    @BeforeEach
    public void setUp(){
        itemRepository.deleteAll()
                .thenMany(Flux.fromIterable(itemsList))
                .flatMap(itemRepository::save)
              .doOnNext(item-> System.out.println(item.getId()))
        .blockLast();
    }

    @Test
    public void getAllItems(){
        StepVerifier.create( itemRepository.findAll().log())
        .expectSubscription()
                .expectNextCount(2).verifyComplete();
    }

    @Test
    public void getById(){
        StepVerifier.create(itemRepository.findById("101"))
                .expectSubscription()
                .expectNextCount(1).verifyComplete();
    }

    @Test
    public void saveItem(){
        Item item = new Item("103","Pencil",5.0);
        StepVerifier.create(itemRepository.save(item))
                .expectSubscription()
                .expectNextCount(1).verifyComplete();
    }
}
