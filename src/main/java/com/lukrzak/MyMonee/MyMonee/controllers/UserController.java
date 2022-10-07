package com.lukrzak.MyMonee.MyMonee.controllers;

import com.lukrzak.MyMonee.MyMonee.models.User;
import com.lukrzak.MyMonee.MyMonee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PostMapping("/users/change-balance")
    @ResponseStatus(code = HttpStatus.OK)
    public void changeUserBalance(User user, float changedValue){
        userService.changeUserBalance(user, changedValue);
    }
}
