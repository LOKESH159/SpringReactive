package com.reactiveprogramming.ReactiveSpring.repositories;

import com.reactiveprogramming.ReactiveSpring.documents.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItemRepository extends ReactiveMongoRepository<Item,String> {
}
