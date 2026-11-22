package com.rotana.springmvc.service.impl;

import com.rotana.springmvc.domain.Department;
import com.rotana.springmvc.dto.department.CreateDepartmentRequest;
import com.rotana.springmvc.dto.department.DepartmentResponse;
import com.rotana.springmvc.exception.NotFoundException;
import com.rotana.springmvc.mapper.DepartmentMapper;
import com.rotana.springmvc.repository.DepartmentRepository;
import com.rotana.springmvc.service.DepartmentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepo;
    private final DepartmentMapper DepartmentMapper;

    @Override
    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepo.findAll().stream().map(DepartmentMapper::toDepartmentResponse).toList();
//        return departmentRepo.findAll(PageRequest.of(0, 10))

    }

    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {
        Department department = DepartmentMapper.toDepartment(request);
        departmentRepo.save(department);
        return DepartmentMapper.toDepartmentResponse(department);
    }

    @Override
    public void updateDepartmentStatus(UUID id, Boolean isActive) {
        Department department = departmentRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Department not found")
        );
        department.setIsActive(isActive);
        department.getEmployees()
                .forEach(emp->emp.setIsActive(isActive));
        departmentRepo.save(department);
    }
}
