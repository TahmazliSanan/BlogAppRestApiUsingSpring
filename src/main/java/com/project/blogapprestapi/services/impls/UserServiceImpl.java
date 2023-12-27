package com.project.blogapprestapi.services.impls;

import com.project.blogapprestapi.entities.User;
import com.project.blogapprestapi.exceptions.ResourceNotFoundException;
import com.project.blogapprestapi.payloads.UserDto;
import com.project.blogapprestapi.repositories.UserRepository;
import com.project.blogapprestapi.services.inters.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserDto.class);
    }

    @Override
    public UserDto getSingleUser(Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> listOfUser = userRepository.findAll();
        return listOfUser
                .stream()
                .map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setFullName(userDto.getFullName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setPassword(userDto.getPassword());
        user.setAboutBio(userDto.getAboutBio());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
    }
}