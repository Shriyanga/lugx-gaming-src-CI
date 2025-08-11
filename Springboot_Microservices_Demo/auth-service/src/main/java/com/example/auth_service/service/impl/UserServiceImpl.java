package com.example.auth_service.service.impl;

import com.example.auth_service.dto.UserDTO;
import com.example.auth_service.repo.UserRepo;
import com.example.auth_service.entity.User;
import com.example.auth_service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO signUp(UserDTO userDTO) {
        if (!Objects.isNull(userDTO)) {
            if (userDTO.getEmail() == null || userDTO.getEmail().equals("")) {
                throw new RuntimeException("not found email");
            }
            if (userDTO.getPassword() == null || userDTO.getPassword().equals("")) {
                throw new RuntimeException("not found password");
            }
            if (userDTO.getContact() == null || userDTO.getContact().equals("")) {
                throw new RuntimeException("not found contact");
            }
            if (userDTO.getRole() == null || userDTO.getRole().equals("")) {
                throw new RuntimeException(" not found userRole");
            }
            if (userDTO.getName() == null || userDTO.getName().equals("")) {
                throw new RuntimeException(" not found userName");
            }
            if (!userRepo.existsByEmail(userDTO.getEmail())) {
                userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
                User savedUser = userRepo.save(modelMapper.map(userDTO, User.class));
                if (savedUser.getId() < 1) {
                    throw new RuntimeException("User registration failed");
                }
                return modelMapper.map(savedUser, UserDTO.class);
            } else {
                throw new RuntimeException("Email already exists...");
            }
        } else {
            throw new RuntimeException("Invalid user inputs...");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
