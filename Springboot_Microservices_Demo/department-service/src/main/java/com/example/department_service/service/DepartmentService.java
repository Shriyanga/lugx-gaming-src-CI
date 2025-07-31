package com.example.department_service.service;

import com.example.department_service.dto.DepartmentDTO;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

public interface DepartmentService {
    public boolean saveDepartment(DepartmentDTO departmentDTO) throws Exception;

    public boolean updateDepartment(DepartmentDTO departmentDTO) throws Exception;

    public boolean deleteDepartment(Long id) throws Exception;

    public DepartmentDTO findDepartment(Long id) throws Exception;

    public List<DepartmentDTO> getAllDepartments() throws Exception;
}
