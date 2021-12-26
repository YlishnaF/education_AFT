package com.lession1;

public abstract class Candy {
    String name;
    double weight;
    double price;
    String uniqueness;

    public Candy(String name, double weight, double price, String uniqueness) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.uniqueness = uniqueness;
    }
}
