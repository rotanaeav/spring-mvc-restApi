package com.rotana.springmvc.service;

import com.rotana.springmvc.dto.department.CreateDepartmentRequest;
import com.rotana.springmvc.dto.department.DepartmentResponse;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse createDepartment(CreateDepartmentRequest request);

    void updateDepartmentStatus(UUID id, Boolean isActive);
}
