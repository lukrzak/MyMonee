package com.lukrzak.MyMonee.MyMonee.services;

import com.lukrzak.MyMonee.MyMonee.models.User;
import com.lukrzak.MyMonee.MyMonee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void changeUserBalance(User user, double changedValue){
        user.setBalance(user.getBalance() + changedValue);
        userRepository.save(user);
    }

}
