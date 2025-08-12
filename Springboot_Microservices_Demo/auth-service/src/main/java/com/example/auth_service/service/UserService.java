package com.example.auth_service.service;

import com.example.auth_service.dto.UserDTO;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-24
 */

public interface UserService {
    UserDTO signUp(UserDTO userDTO);
    List<UserDTO> getAllUsers();
}
