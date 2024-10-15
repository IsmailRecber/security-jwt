package com.jwtexample.jwt.service;


import com.jwtexample.jwt.Dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}