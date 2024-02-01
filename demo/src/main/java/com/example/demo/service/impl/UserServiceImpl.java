package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = this.dtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = this.userRepo.save(user);
        return entityToDto(save);
    }

    @Override
    public UserDto userLogin(UserDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<User> userNames = userRepo.findByUserName(dto.getUserName());
        for (User name : userNames) {
            boolean isPasswordMatches = passwordEncoder.matches(dto.getPassword(), name.getPassword());
            if (isPasswordMatches) {
                return entityToDto(name);
            }
        }
        return null;
    }


    private User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto entityToDto(User user) {
        return (user == null) ? null : modelMapper.map(user, UserDto.class);
    }
}
