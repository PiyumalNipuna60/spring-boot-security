package com.example.demo.service;

import com.example.demo.dto.UserDto;


public interface UserService {
    UserDto registerUser(UserDto userDto);

    UserDto userLogin(UserDto dto);
}
