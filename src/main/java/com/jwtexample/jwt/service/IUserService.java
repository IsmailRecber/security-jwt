package com.jwtexample.jwt.service;

import com.jwtexample.jwt.Dto.AuthRequest;
import com.jwtexample.jwt.Dto.DtoUser;
import com.jwtexample.jwt.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public DtoUser saveUser(AuthRequest request);

    public List<DtoUser> getAllUsers();

    public DtoUser getUserById(Long userId);

   // public Optional<User> getByUserName(String username);


        public void deleteUser(Long userId);

    public DtoUser updateUser(Long userId,User user);

}
