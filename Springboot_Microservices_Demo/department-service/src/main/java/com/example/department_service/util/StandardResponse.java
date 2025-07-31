package com.example.department_service.util;

import com.example.department_service.dto.DepartmentDTO;
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
    private DepartmentDTO departmentDTO;
    private List<DepartmentDTO> departmentDTOList;
    private Object object;
}
