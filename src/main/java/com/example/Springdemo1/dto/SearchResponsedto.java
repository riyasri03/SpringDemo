package com.example.Springdemo1.dto;

import java.util.List;

public class SearchResponsedto {
    private List<Productdto> products;
    private List<Productdto> locationBasedProducts;


    public List<Productdto> getProducts() {
        return products;
    }

    public void setProducts(List<Productdto> products) {
        this.products = products;
    }

    public List<Productdto> getLocationBasedProducts() {
        return locationBasedProducts;
    }

    public void setLocationBasedProducts(List<Productdto> locationBasedProducts) {
        this.locationBasedProducts = locationBasedProducts;
    }
}
