package com.example.employee_service.repo;

import com.example.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findAllByDepartmentId(Long departmentId);
}
