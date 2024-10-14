package com.jwtexample.jwt.controller;

import com.jwtexample.jwt.Dto.AuthRequest;
import com.jwtexample.jwt.Dto.DtoUser;
import com.jwtexample.jwt.model.User;

import java.util.List;

public interface IUserController {

    public DtoUser saveUser(AuthRequest request);

    public List<DtoUser> getAllUsers();

    public DtoUser getUserById(Long userId);

    public void deleteUser(Long userId);

    public DtoUser updateUser(Long userId,User user);


}
