package com.scrylk.newtodo.controller;


import com.scrylk.newtodo.model.UserModel;
import com.scrylk.newtodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public void createUser(@RequestBody UserModel user) {
        service.createUser(user);
    }

    @GetMapping
    public void getAllUsers() {
        service.getAllUser();
    }

    @GetMapping
    public void getUserById(UUID user_id) {
        service.getUserById(user_id);
    }

    @DeleteMapping
    public void deleteUser(UUID user_id) {
        service.deleteUser(user_id);

    }
}
