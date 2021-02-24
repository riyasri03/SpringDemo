package com.example.Springdemo1.service.impl;

import com.example.Springdemo1.client.SearchClient;
import com.example.Springdemo1.dto.SearchRequestdto;
import com.example.Springdemo1.dto.SearchResponsedto;
import com.example.Springdemo1.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class SearchServiceImplTest {
  @InjectMocks
  private SearchServiceImpl searchService;
  @Mock
  private SearchClient searchClient;

  @BeforeEach
  public void init(){
      MockitoAnnotations.openMocks(this);
  }
 @AfterEach
 public void teardown(){

}

 @Test
    void getProduct() throws IOException {
     ObjectMapper objectMapper= new ObjectMapper();
     Map<String,Object> searchTermMockObject=objectMapper.readValue(new URL("file:src/main/resources/search.mock"),Map.class);
     Map<String,Object> locationMockObject=objectMapper.readValue(new URL("file:src/main/resources/location.mock"),Map.class);
     Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
     Mockito.when(searchClient.getProducts("stockLocation:\"jakarta\"")).thenReturn(locationMockObject);

     SearchRequestdto requestdto=new SearchRequestdto();
     requestdto.setProduct("samsung");
     requestdto.setLocation("jakarta");
     searchService.getProducts(requestdto);
     SearchResponsedto response = searchService.getProducts(requestdto);
     assertEquals(response.getProducts().size(), 10);
     assertEquals(response.getLocationBasedProducts().size(),10);
//     Mockito.verify(searchClient.getProducts("samsung"));
//     Mockito.verify(searchClient.getProducts("stockLocation:\"jakarta\""));

 }
 @Test
    public void testGetProductsexceptionTest() throws IOException{
     ObjectMapper objectMapper= new ObjectMapper();
     Map<String,Object> searchTermMockObject=objectMapper.readValue(new URL("file:src/main/resources/search.mock"),Map.class);
     Map<String,Object> locationMockObject=objectMapper.readValue(new URL("file:src/main/resources/location.mock"),Map.class);
     Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
     Mockito.when(searchClient.getProducts("stockLocation:\"jakarta\"")).thenThrow(NullPointerException.class);

     SearchRequestdto requestdto=new SearchRequestdto();
     requestdto.setProduct("samsung");
     requestdto.setLocation("jakarta");
     SearchResponsedto response = searchService.getProducts(requestdto);
     assertEquals(response.getProducts().size(), 10);
     assertEquals(response.getLocationBasedProducts(),null);
 }
}