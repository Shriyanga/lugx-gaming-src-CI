package com.example.gameservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse<T> {
    private Integer code;
    private String message;
    private T payload;
}
