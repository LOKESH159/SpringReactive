package com.reactiveprogramming.ReactiveSpring.documents;


public class Reviews {

    private String  content;

    private int rating;

    public Reviews(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }
}
