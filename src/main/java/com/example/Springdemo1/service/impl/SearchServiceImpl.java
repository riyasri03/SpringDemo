package com.example.Springdemo1.service.impl;

import com.example.Springdemo1.client.SearchClient;
import com.example.Springdemo1.dto.Productdto;
import com.example.Springdemo1.dto.SearchRequestdto;
import com.example.Springdemo1.dto.SearchResponsedto;
import com.example.Springdemo1.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchClient searchClient;

    @Override
    public SearchResponsedto getProducts(SearchRequestdto request){

        SearchResponsedto responsedto= new SearchResponsedto();
        Map<String, Object> location= searchClient.getProducts("stockLocation"+request.getLocation());
        List<LinkedHashMap<String, Object>> m=(List<LinkedHashMap<String, Object>>) ((Map)location.get("response")).get("docs");
        List<Productdto> product2= new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task1=() ->{
            System.out.println(Thread.currentThread().getId());
            List<Productdto> product1 =getStringObjectMap(request.getSearchTerm());
            responsedto.setProducts(product1);
        };



     Runnable task2=() ->{
         System.out.println(Thread.currentThread().getId());
         for(LinkedHashMap<String,Object> k:m){
             Productdto productdto= new Productdto();
             productdto.setDescription((String) k.get("description"));
             productdto.setInstock((int) k.get("isInStock") == 1? true: false );
             productdto.setTitle((String)k.get("nameSearch") );
             productdto.setSalePrice(((Double)k.get("offerPrice")).intValue());
             product2.add(productdto);
         }

         responsedto.setLocationBasedProducts(product2);

     };
        executor.execute(task1);
        executor.execute(task2);
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }



        return responsedto;
    }

    private List<Productdto> getStringObjectMap(String query) {
        Map<String, Object> products= searchClient.getProducts(query);
        List<LinkedHashMap<String, Object>> l=(List<LinkedHashMap<String, Object>>) ((Map)products.get("response")).get("docs");
        List<Productdto> product1= new ArrayList<>();
        for(LinkedHashMap<String,Object> k:l){
            Productdto productdto= new Productdto();
            productdto.setDescription((String) k.get("description"));
            productdto.setInstock((int) k.get("isInStock") == 1? true: false );
            productdto.setTitle((String)k.get("nameSearch") );
            productdto.setSalePrice(((Double)k.get("offerPrice")).intValue());
            product1.add(productdto);
        }
        return product1;
    }
}
