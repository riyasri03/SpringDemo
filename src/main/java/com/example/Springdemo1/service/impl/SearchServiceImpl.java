package com.example.Springdemo1.service.impl;

import com.example.Springdemo1.dto.Productdto;
import com.example.Springdemo1.dto.SearchRequestdto;
import com.example.Springdemo1.dto.SearchResponsedto;
import com.example.Springdemo1.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public SearchResponsedto getProducts(SearchRequestdto request){
        SearchResponsedto responsedto= new SearchResponsedto();
        Productdto productdto= new Productdto();

        productdto.setDescription("Samsung Galaxy");
        productdto.setTitle("S5");
        productdto.setInstock(true);
        responsedto.setProducts(Arrays.asList(productdto));
        return responsedto;
    }
}
