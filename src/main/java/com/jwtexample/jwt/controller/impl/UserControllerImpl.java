package com.jwtexample.jwt.controller.impl;

import com.jwtexample.jwt.Dto.AuthRequest;
import com.jwtexample.jwt.Dto.DtoUser;
import com.jwtexample.jwt.controller.IUserController;
import com.jwtexample.jwt.model.User;
import com.jwtexample.jwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {
    @Autowired
    IUserService userService;


    @PostMapping("/save")
    @Override
    public DtoUser saveUser(@RequestBody AuthRequest request){
        return userService.saveUser(request);
    }

    @GetMapping("/getUser")
    @Override
    public List<DtoUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public DtoUser getUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public DtoUser updateUser(Long userId, User user) {
        return null;
    }
}
