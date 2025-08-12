package com.example.auth_service.service;

import com.example.auth_service.dto.UserDTO;

import java.util.List;



public interface UserService {
    UserDTO signUp(UserDTO userDTO);
    List<UserDTO> getAllUsers();
}
