package com.recipe.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping("newUser")
    public User addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }


}