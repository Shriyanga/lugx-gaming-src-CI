package com.example.auth_service.controller;

import com.example.auth_service.dto.JwtRequestModel;
import com.example.auth_service.dto.JwtResponseModel;
import com.example.auth_service.dto.UserDTO;
import com.example.auth_service.util.JwtUtil;
import com.example.auth_service.util.StandardResponse;
import com.example.auth_service.service.JwtUserDetailService;
import com.example.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailService jwtUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        UserDTO b = userService.signUp(userDTO);
        return new ResponseEntity<>(new StandardResponse<>(201, "Success",  b), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestModel jwtRequestModel) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequestModel.getEmail(),
                    jwtRequestModel.getPassword()
            ));
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequestModel.getEmail());
            final String userRole = userDetails.getAuthorities().toString();
            final String token = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new StandardResponse<>(200, "Success", new JwtResponseModel(token, userRole)), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(new StandardResponse<>(200, "Success", allUsers), HttpStatus.OK);
    }
}
