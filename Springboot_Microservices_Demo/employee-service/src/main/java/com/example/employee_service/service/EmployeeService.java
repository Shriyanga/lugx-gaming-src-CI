package com.example.employee_service.service;

import com.example.employee_service.dto.EmployeeDTO;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

public interface EmployeeService {
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws Exception;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws Exception;

    public boolean deleteEmployee(Long id) throws Exception;

    public EmployeeDTO findEmployee(Long id) throws Exception;

    public List<EmployeeDTO> getAllEmployeesByDepartmentId(Long departmentId) throws Exception;

    public List<EmployeeDTO> getAllEmployees() throws Exception;
}
