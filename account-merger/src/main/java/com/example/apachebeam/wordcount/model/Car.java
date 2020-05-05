package com.example.apachebeam.wordcount.model;

import java.io.Serializable;

public class Car implements Serializable {
    private int id;
    private String make;
    private String model;
    private String price;
    private int year;
    private String color;

    public Car(int id, String make, String model, String price, int year, String color) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.price = price;
        this.year = year;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
