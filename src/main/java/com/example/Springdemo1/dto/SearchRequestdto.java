package com.example.Springdemo1.dto;

public class SearchRequestdto {
    private String searchTerm;
    private String location;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setProduct(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}