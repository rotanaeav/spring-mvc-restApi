package com.rotana.springmvc.repository;

import com.rotana.springmvc.domain.Department;
import com.rotana.springmvc.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findAllByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name,String email);

    List<Employee> findByName(String name);
    Page<Employee> findByDepartmentId(UUID departmentId, Pageable pageable);

    Long countEmployeeByDepartmentId(UUID departmentId);

    Long countAllByIsActiveAndDepartment(Boolean isActive, Department department);

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id =:departmentId")
    BigDecimal getEmployeesAvgSalaryByDepartment(@Param("departmentId") UUID departmentId);
}
