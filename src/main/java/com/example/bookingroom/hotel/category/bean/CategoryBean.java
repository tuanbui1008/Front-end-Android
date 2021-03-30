package com.example.bookingroom.hotel.category.bean;

public class CategoryBean {
    private int id;
    private String name;

    public CategoryBean() {
    }

    public CategoryBean(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
