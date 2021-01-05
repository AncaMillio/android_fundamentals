package com.anca.c3;

public class Animal {

    private final String name;
    private final String type;
    private final int price;

    public Animal(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
