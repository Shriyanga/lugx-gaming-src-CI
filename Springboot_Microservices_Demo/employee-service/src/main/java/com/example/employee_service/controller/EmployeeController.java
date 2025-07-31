package com.example.employee_service.controller;

import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.service.EmployeeService;
import com.example.employee_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@RestController
@RequestMapping("/api/v1/employee")
//@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<StandardResponse> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            boolean b = employeeService.saveEmployee(employeeDTO);
            return new ResponseEntity<>(new StandardResponse(201, "Success", null, null, b), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new StandardResponse(500, "Error", null, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllEmployees(){
        try {
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
            return new ResponseEntity<>(new StandardResponse(200, "Success", null,employeeDTOList, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new StandardResponse(500, "Error", null, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byDepartmentId/{departmentId}")
    public ResponseEntity<StandardResponse> getAllEmployeesByDepartmentId(@PathVariable("departmentId") Long departmentId){
        try {
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployeesByDepartmentId(departmentId);
            return new ResponseEntity<>(new StandardResponse(200, "Success", null, employeeDTOList, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new StandardResponse(500, "Error", null, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findEmployee(@PathVariable("id") Long id){
        try{
            EmployeeDTO employeeDTO = employeeService.findEmployee(id);
            return new ResponseEntity<>(new StandardResponse(200, "Success", employeeDTO, null, null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new StandardResponse(500, "Error", null, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
