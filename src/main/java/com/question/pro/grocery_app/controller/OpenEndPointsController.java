package com.question.pro.grocery_app.controller;

import com.question.pro.grocery_app.entity.Users;
import com.question.pro.grocery_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenEndPointsController {
    @Autowired
    private UserService userService;

    /**Tested**/
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Users register(@RequestBody Users user){
        return userService.register(user);
    }

    /**Tested**/
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }
}
