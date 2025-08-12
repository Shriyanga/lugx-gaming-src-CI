package com.example.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 06-Dec-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestModel {
    private String email;
    private String password;
}
