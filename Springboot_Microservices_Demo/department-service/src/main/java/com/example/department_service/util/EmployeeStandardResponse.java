package com.example.department_service.util;

import com.example.department_service.dto.EmployeeDTO;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

public record EmployeeStandardResponse(Integer code, String message, EmployeeDTO employeeDTO,
                                       List<EmployeeDTO> employeeDTOList, Object object) {
}