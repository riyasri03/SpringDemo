package com.example.Springdemo1.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

@FeignClient(name="searh-client", url="10.177.68.77:8983")
public interface SearchClient {

    /**
     *
     * API ref
     * [GET] 10.177.68.40:8983/solr/productCollection/select?q=samsung
     */
    @RequestMapping(method =RequestMethod.GET, path="/solr/productCollection/select")
    public Map<String,Object> getProducts(@RequestParam("q") String query);
}
