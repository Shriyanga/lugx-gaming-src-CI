package com.example.department_service.client;

import com.example.department_service.util.EmployeeStandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@HttpExchange("http://employee-service/api/v1/employee")
public interface EmployeeClient {

    @GetExchange("/byDepartmentId/{departmentId}")
    public ResponseEntity<EmployeeStandardResponse> getAllEmployeesByDepartmentId(@PathVariable("departmentId") Long departmentId);
}
