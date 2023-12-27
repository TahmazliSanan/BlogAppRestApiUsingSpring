package com.project.blogapprestapi.controllers;

import com.project.blogapprestapi.payloads.ApiResponse;
import com.project.blogapprestapi.payloads.UserDto;
import com.project.blogapprestapi.services.inters.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserDto userDto,
            @PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(userService.updateUser(userDto, userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(userService.getSingleUser(userId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User is deleted successfully!", true),
                HttpStatus.OK);
    }
}