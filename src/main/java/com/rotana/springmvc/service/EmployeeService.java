package com.rotana.springmvc.service;

import com.rotana.springmvc.dto.employee.CreateEmployeeRequest;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.dto.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAll();
    EmployeeResponse getById(int id);
    EmployeeResponse createEmployee(CreateEmployeeRequest request);
    EmployeeResponse updateEmployee(int id, UpdateEmployeeRequest request);

    void deleteById(int id);

    List<EmployeeResponse> searchByName(String name);
}
