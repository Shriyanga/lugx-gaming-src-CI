package com.example.orderservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse<T> {
    private Integer code;
    private String message;
    private T payload;
}
