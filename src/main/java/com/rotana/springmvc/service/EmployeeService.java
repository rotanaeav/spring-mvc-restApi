package com.rotana.springmvc.service;

import com.rotana.springmvc.dto.employee.CreateEmployeeRequest;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.dto.employee.UpdateEmployeeRequest;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeResponse> getAll();
    EmployeeResponse getById(UUID id);
    EmployeeResponse createEmployee(CreateEmployeeRequest request);
    EmployeeResponse updateEmployee(UUID id, UpdateEmployeeRequest request);

    void deleteById(UUID id);

    List<EmployeeResponse> searchByNameOrEmail(String keyword);
    List<EmployeeResponse> findByName(String name);

    List<EmployeeResponse> bulkCreateEmployees(List<CreateEmployeeRequest> bulkRequest);
}
