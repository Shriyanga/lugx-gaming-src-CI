package com.example.department_service.dto;

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
public class DepartmentDTO {
    private Long id;
    private String name;
    private List<EmployeeDTO> employeeDTOS;
}
