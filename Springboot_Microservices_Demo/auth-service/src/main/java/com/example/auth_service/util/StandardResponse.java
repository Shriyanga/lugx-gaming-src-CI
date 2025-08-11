package com.example.auth_service.util;

import com.example.auth_service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse<T> {
    private Integer code;
    private String message;
    private T payload;
}
