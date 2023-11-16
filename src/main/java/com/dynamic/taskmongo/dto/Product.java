package com.dynamic.taskmongo.dto;

import lombok.Data;

@Data
public class Product {

    private String orderOn;
    private String name;
    private String location;
    private String product;
    private int quantity;
    private String orderedDay;
    private String price;
    private String id;
}
