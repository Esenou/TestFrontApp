package com.example.demo.service;

import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserCreat;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserPage;

public interface UserService {

    void signIn(UserCreat userCreat);

    void addUser(UserModel userModel);

    void deleteUser(Long id);

    UserModel findById(Long id);

    void updateUser(Long id, UserModel userModel);

    UserPage findAll(String username);

    UserPage findAllByUsername(String username, String page);

    UserModel findByUsernameAndStatus(String username, UserStatus active);
}
