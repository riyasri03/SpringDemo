package com.example.Springdemo1.controller;

import com.example.Springdemo1.dto.SearchRequestdto;
import com.example.Springdemo1.dto.SearchResponsedto;
import com.example.Springdemo1.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;
    @PostMapping(path="/search")
    public SearchResponsedto getProductsDetails(@RequestBody SearchRequestdto request){
        return searchService.getProducts(request);
    }
}
