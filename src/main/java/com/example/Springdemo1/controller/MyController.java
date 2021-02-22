package com.example.Springdemo1.controller;

import com.example.Springdemo1.dto.MyPackagedto;
import com.example.Springdemo1.dto.MyResponsedto;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

@GetMapping(path="/hello")
    public String helloWorld(){
    return "Success!!";
}
@PostMapping(path="/hello-post")
    public String postApi(){
    return "Post Success!!";
}
//@PutMapping
    //@DeleteMapping

    @GetMapping(path="/hello-query")
    public String helloquery(@RequestParam String query){
    return "query"+query;
    }

    @PostMapping(value="/regist")
    public String resisterUser(@RequestBody MyPackagedto request){
    return request.toString();
    }
    @GetMapping(path="/employee/{employeeid}")
    public MyResponsedto getEmployeeDetails(@PathVariable String employeeid) {
        MyResponsedto response = new MyResponsedto();
        response.setId(employeeid);
        return response;
    }

}
