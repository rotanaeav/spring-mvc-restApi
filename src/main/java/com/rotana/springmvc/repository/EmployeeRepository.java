package com.rotana.springmvc.repository;

import com.rotana.springmvc.domain.Employee;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class EmployeeRepository {
    private final List<Employee> employees;
    public EmployeeRepository(){
        employees = new ArrayList<>();
        Employee em1 = new Employee();
        em1.setId(1);
        em1.setName("Jame");
        em1.setEmail("jame@gmail.com");
        em1.setSalary(BigDecimal.valueOf(800));
        em1.setHireDate(LocalDate.of(2026,1, 1));
        em1.setIsActive(true);
        Employee em2 = new Employee();
        em2.setId(2);
        em2.setName("John");
        em2.setEmail("john@gmail.com");
        em2.setSalary(BigDecimal.valueOf(1200));
        em2.setHireDate(LocalDate.of(2025,12, 1));
        em2.setIsActive(true);
        Employee em3 = new Employee();
        em3.setId(3);
        em3.setName("kaka");
        em3.setEmail("kaka@gmail.com");
        em3.setSalary(BigDecimal.valueOf(1500));
        em3.setHireDate(LocalDate.of(2025,10, 1));
        em3.setIsActive(true);
        employees.addAll(List.of(em1, em2, em3));
    }

}
