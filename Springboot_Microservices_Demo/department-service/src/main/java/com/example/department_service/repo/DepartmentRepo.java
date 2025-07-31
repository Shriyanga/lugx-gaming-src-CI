package com.example.department_service.repo;

import com.example.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
