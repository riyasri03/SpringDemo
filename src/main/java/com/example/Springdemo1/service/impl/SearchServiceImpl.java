package com.example.Springdemo1.service.impl;

import com.example.Springdemo1.client.SearchClient;
import com.example.Springdemo1.constant.SolrFieldNames;
import com.example.Springdemo1.dto.Productdto;
import com.example.Springdemo1.dto.SearchRequestdto;
import com.example.Springdemo1.dto.SearchResponsedto;
import com.example.Springdemo1.service.SearchService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;

@Service
public class SearchServiceImpl implements SearchService {

    private  static final int POOL_SIZE = 2;
    @Autowired
    private SearchClient searchClient;
    @Override
    public SearchResponsedto getProducts(SearchRequestdto request) {

        SearchResponsedto responseDTO = new SearchResponsedto();
        ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);

        threadPool.execute(() -> {

            String searchTermQuery = request.getSearchTerm();
            List<Productdto> productDTOS = getSearchTermBaseProducts(searchTermQuery);
            responseDTO.setProducts(productDTOS);

        });

        threadPool.execute(() -> {

                String locationQuery = SolrFieldNames.STOCK_LOCATION + ":\"" + request.getLocation() + "\"";
                List<Productdto> locationProductDTOs = getSearchTermBaseProducts(locationQuery);
                responseDTO.setLocationBasedProducts(locationProductDTOs);


        });
        awaitTerminationAfterShutdown(threadPool);
        return responseDTO;
    }
    private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    private List<Productdto> getSearchTermBaseProducts(String query) {
        Map<String, Object> productResponse = searchClient.getProducts(query);
        System.out.println(query);
        Map<String, Object> responseObject = (Map<String, Object>) (productResponse.get("response"));
        List<Map<String, Object>> products = (List<Map<String, Object>>) responseObject.get("docs");
        List<Productdto> productDTOS = new ArrayList<>();
        for (Map<String, Object> productClientResponse :products) {
            String title = (String) productClientResponse.get(SolrFieldNames.NAME);
            boolean inStock = (int) productClientResponse.get(SolrFieldNames.IN_STOCK) == 1? true: false;
            String description = (String) productClientResponse.get(SolrFieldNames.DESCRIPTION);
            Productdto productDTO = new Productdto();
            productDTO.setInstock(inStock);
            productDTO.setTitle(title);
            productDTO.setDescription(description);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}