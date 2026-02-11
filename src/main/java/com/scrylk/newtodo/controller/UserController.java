package com.scrylk.newtodo.controller;


import com.scrylk.newtodo.model.UserModel;
import com.scrylk.newtodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<UserModel> getAllUsers() {
        return service.getAllUser();
    }

    @GetMapping("{user_id}")
    public void getUserById(@PathVariable UUID user_id) {
        service.getUserById(user_id);
    }

    @PutMapping("{user_id}")
    public void updateUser(@PathVariable UUID user_id) {
        service.updateUser(user_id);
    }

    @DeleteMapping("{user_id}")
    public void deleteUser(@PathVariable UUID user_id) {
        service.deleteUser(user_id);
    }
}
