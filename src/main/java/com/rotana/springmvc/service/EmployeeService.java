package com.rotana.springmvc.service;

import com.rotana.springmvc.dto.CreateEmployeeRequest;
import com.rotana.springmvc.dto.EmployeeResponse;
import com.rotana.springmvc.dto.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAll();
    EmployeeResponse getById(int id);
    EmployeeResponse createEmployee(CreateEmployeeRequest request);
    EmployeeResponse updateEmployee(int id, UpdateEmployeeRequest request);

    void deleteById(int id);

    List<EmployeeResponse> searchByName(String name);
}
