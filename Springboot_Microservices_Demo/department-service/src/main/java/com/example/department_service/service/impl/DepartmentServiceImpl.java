package com.example.department_service.service.impl;

import com.example.department_service.client.EmployeeClient;
import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.dto.EmployeeDTO;
import com.example.department_service.entity.Department;
import com.example.department_service.repo.DepartmentRepo;
import com.example.department_service.service.DepartmentService;
import com.example.department_service.util.EmployeeStandardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeClient employeeClient;

    @Override
    public boolean saveDepartment(DepartmentDTO departmentDTO) throws Exception {
        if (Objects.nonNull(departmentDTO)) {
            Department department = modelMapper.map(departmentDTO, Department.class);
            Department savedDepartment = departmentRepo.save(department);
            return savedDepartment.getId() != null && savedDepartment.getId() > 0;
        }
        return false;
    }

    @Override
    public boolean updateDepartment(DepartmentDTO departmentDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteDepartment(Long id) throws Exception {
        return false;
    }

    @Override
    public DepartmentDTO findDepartment(Long id) throws Exception {
        return null;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() throws Exception {
        List<Department> all = departmentRepo.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (Department department : all) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            ResponseEntity<EmployeeStandardResponse> employeeResponse = employeeClient.getAllEmployeesByDepartmentId(department.getId());
            if (employeeResponse != null) {
                EmployeeStandardResponse body = employeeResponse.getBody();
                if (body.code() == 200) {
                    List<EmployeeDTO> employeeDTOS1 = body.employeeDTOList();
                    departmentDTO.setEmployeeDTOS(employeeDTOS1);
                } else {
                    departmentDTO.setEmployeeDTOS(new ArrayList<>());
                }
            } else {
                departmentDTO.setEmployeeDTOS(new ArrayList<>());
            }
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }

}
