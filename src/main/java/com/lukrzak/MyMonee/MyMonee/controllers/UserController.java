package com.lukrzak.MyMonee.MyMonee.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lukrzak.MyMonee.MyMonee.models.User;
import com.lukrzak.MyMonee.MyMonee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.Map;

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
    public void changeUserBalance(@RequestBody ObjectNode objectNode) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String str = objectNode.get("user").toString();
        User user = mapper.readValue((DataInput) objectNode.get("user"), User.class);  // maps json body to object
        double changedValue = Double.parseDouble(json.get("balance"));
        userService.changeUserBalance(user, changedValue);
    }
}
