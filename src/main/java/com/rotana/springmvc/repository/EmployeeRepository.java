package com.rotana.springmvc.repository;

import com.rotana.springmvc.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findAllByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name,String email);

    List<Employee> findByName(String name);
    Page<Employee> findByDepartmentId(UUID departmentId, Pageable pageable);
}
