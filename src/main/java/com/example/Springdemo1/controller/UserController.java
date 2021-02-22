package com.example.Springdemo1.controller;

import com.example.Springdemo1.dto.MyPackagedto;
import com.example.Springdemo1.dto.MyResponsedto;
import com.example.Springdemo1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.print.DocFlavor;

@RestController
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostConstruct
    public void init(){
        System.out.println("Inside user controller post constructor ");
    }

    @Autowired
    private UserService userService;
    @PostMapping(value="/update/employee/{id}")
    public boolean updateEmployee(@PathVariable String id, @RequestBody MyPackagedto request){



        return userService.updateEmployee(request,id);

    }
}
