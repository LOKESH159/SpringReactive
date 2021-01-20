package com.reactiveprogramming.ReactiveSpring.documents;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    @Id
    private String id;

    private String name;

    private double price;
}
