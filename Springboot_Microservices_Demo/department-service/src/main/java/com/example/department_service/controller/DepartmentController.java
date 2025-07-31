package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.service.DepartmentService;
import com.example.department_service.util.StandardResponse;
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
@RequestMapping("/api/v1/department")
//@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<StandardResponse> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        try {
            boolean b = departmentService.saveDepartment(departmentDTO);
            return new ResponseEntity<>(new StandardResponse(201, "Success", null, null,b), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new StandardResponse(500, "Error", null, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllDepartmentsWithEmployee(){
        try {
            List<DepartmentDTO> departmentDTOS = departmentService.getAllDepartments();
            return new ResponseEntity<>(new StandardResponse(200, "Success", null, departmentDTOS ,null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new StandardResponse(500, "Error", null, null, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
