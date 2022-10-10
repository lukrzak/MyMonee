package com.lukrzak.MyMonee.MyMonee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukrzak.MyMonee.MyMonee.dto.ChangeBalance;
import com.lukrzak.MyMonee.MyMonee.models.User;
import com.lukrzak.MyMonee.MyMonee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String test(){
        return "Test";
    }

    @PostMapping("/users")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PostMapping("/users/change-balance")
    @ResponseStatus(code = HttpStatus.OK)
    public void changeUserBalance(@RequestBody ChangeBalance changeBalance) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = changeBalance.getUser();
        double changedValue = changeBalance.getBalance();
        userService.changeUserBalance(user, changedValue);
    }
}
