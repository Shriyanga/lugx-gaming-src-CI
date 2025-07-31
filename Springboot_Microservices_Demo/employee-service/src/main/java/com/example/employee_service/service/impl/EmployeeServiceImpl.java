package com.example.employee_service.service.impl;

import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.service.EmployeeService;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws Exception {
        if(Objects.nonNull(employeeDTO)){
            Employee employee = modelMapper.map(employeeDTO, Employee.class);
            Employee save = employeeRepo.save(employee);
            return save.getId() != null && save.getId()>0;
        }
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteEmployee(Long id) throws Exception {
        return false;
    }

    @Override
    public EmployeeDTO findEmployee(Long id) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        return optionalEmployee.map(employee -> modelMapper.map(employee, EmployeeDTO.class)).orElse(null);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByDepartmentId(Long departmentId) throws Exception {
        List<Employee> allByDepartmentId = employeeRepo.findAllByDepartmentId(departmentId);
        return modelMapper.map(allByDepartmentId, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws Exception {
        List<Employee> all = employeeRepo.findAll();
        return modelMapper.map(all, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }
}
