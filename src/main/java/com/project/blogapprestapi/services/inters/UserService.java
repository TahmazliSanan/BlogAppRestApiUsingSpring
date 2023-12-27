package com.project.blogapprestapi.services.inters;

import com.project.blogapprestapi.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getSingleUser(Integer userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto, Integer userId);
    void deleteUser(Integer userId);
}