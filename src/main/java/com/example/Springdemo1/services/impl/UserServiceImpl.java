package com.example.Springdemo1.services.impl;

import com.example.Springdemo1.dto.MyPackagedto;
import com.example.Springdemo1.services.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("inside user service constructor");
    }
    @PostConstruct
    public void init(){
        System.out.println("Inside userservice Post Constructor");
    }

    @Override
    public boolean updateEmployee(MyPackagedto request, String id) {
        System.out.println("Inside user service"+ request+" "+id);
        return false;
    }
}
