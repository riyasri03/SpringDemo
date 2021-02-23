package com.example.Springdemo1.service.impl;

import com.example.Springdemo1.client.SearchClient;
import com.example.Springdemo1.dto.Productdto;
import com.example.Springdemo1.dto.SearchRequestdto;
import com.example.Springdemo1.dto.SearchResponsedto;
import com.example.Springdemo1.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchClient searchClient;

    @Override
    public SearchResponsedto getProducts(SearchRequestdto request){

        Map<String, Object> products= searchClient.getProducts(request.getSearchTerm());
        Map<String, Object> location= searchClient.getProducts("q="+request.getLocation());

        List<LinkedHashMap<String, Object>> l=(List<LinkedHashMap<String, Object>>) ((Map)products.get("response")).get("docs");
        List<LinkedHashMap<String, Object>> m=(List<LinkedHashMap<String, Object>>) ((Map)location.get("response")).get("docs");

        SearchResponsedto responsedto= new SearchResponsedto();

        List<Productdto> product1= new ArrayList<>();
        List<Productdto> product2= new ArrayList<>();

        for(LinkedHashMap<String,Object> k:l){
            Productdto productdto= new Productdto();
            productdto.setDescription((String) k.get("description"));
            productdto.setInstock((int) k.get("isInStock") == 1? true: false );
            productdto.setTitle((String)k.get("nameSearch") );
            productdto.setSalePrice(((Double)k.get("offerPrice")).intValue());
            product1.add(productdto);
        }

        for(LinkedHashMap<String,Object> k:m){
            Productdto productdto= new Productdto();
            productdto.setDescription((String) k.get("description"));
            productdto.setInstock((int) k.get("isInStock") == 1? true: false );
            productdto.setTitle((String)k.get("nameSearch") );
            productdto.setSalePrice(((Double)k.get("offerPrice")).intValue());
            product2.add(productdto);
        }



        responsedto.setProducts(product1);
        responsedto.setLocationBasedProducts(product2);
        return responsedto;
    }
}
