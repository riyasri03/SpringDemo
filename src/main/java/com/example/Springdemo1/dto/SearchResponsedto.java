package com.example.Springdemo1.dto;

import java.util.List;

public class SearchResponsedto {
    private List<Productdto> products;

    public List<Productdto> getProducts() {
        return products;
    }

    public void setProducts(List<Productdto> products) {
        this.products = products;
    }
}
