package com.example.Springdemo1.service;


import com.example.Springdemo1.dto.SearchRequestdto;
import com.example.Springdemo1.dto.SearchResponsedto;

public interface SearchService {
    public SearchResponsedto getProducts(SearchRequestdto request);
}
