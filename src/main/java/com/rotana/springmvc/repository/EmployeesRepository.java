package com.rotana.springmvc.repository;

import com.rotana.springmvc.domain.Employee;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

    List<EmployeeResponse> findAllByNameContainingIgnoreCase(String name);
}
