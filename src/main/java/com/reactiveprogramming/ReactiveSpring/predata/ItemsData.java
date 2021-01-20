package com.reactiveprogramming.ReactiveSpring.predata;

import com.reactiveprogramming.ReactiveSpring.documents.Item;
import com.reactiveprogramming.ReactiveSpring.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemsData  implements CommandLineRunner {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> data(){
        return Arrays.asList(new Item("101","Book",20.0),
                new Item("102","Pen",30.0));
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.deleteAll().thenMany(Flux.fromIterable(data())).flatMap(itemRepository::save).blockLast();
    }
}
