package com.example.employee_service.util;

import com.example.employee_service.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {
    private Integer code;
    private String message;
    private EmployeeDTO employeeDTO;
    private List<EmployeeDTO> employeeDTOList;
    private Object object;
}
