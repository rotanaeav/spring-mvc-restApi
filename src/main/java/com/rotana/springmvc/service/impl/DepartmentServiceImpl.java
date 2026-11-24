package com.rotana.springmvc.service.impl;

import com.rotana.springmvc.domain.Department;
import com.rotana.springmvc.dto.department.CreateDepartmentRequest;
import com.rotana.springmvc.dto.department.DepartmentResponse;
import com.rotana.springmvc.dto.department.DepartmentStatisticsResponse;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.exception.NotFoundException;
import com.rotana.springmvc.mapper.DepartmentMapper;
import com.rotana.springmvc.mapper.EmployeeMapper;
import com.rotana.springmvc.repository.DepartmentRepository;
import com.rotana.springmvc.repository.EmployeeRepository;
import com.rotana.springmvc.service.DepartmentService;
import com.rotana.springmvc.utils.PageResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepo;
    private final DepartmentMapper departmentMapper;
    private final EmployeeRepository empRepo;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepo.findAll().stream().map(departmentMapper::toDepartmentResponse).toList();
//        return departmentRepo.findAll(PageRequest.of(0, 10))

    }

    @Override
    @Transactional
    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {
        Department department = departmentMapper.toDepartment(request);
        departmentRepo.save(department);
        return departmentMapper.toDepartmentResponse(department);
    }

    @Override
    @Transactional
    public void updateDepartmentStatus(UUID id, Boolean isActive) {
        Department department = findDepartmentById(id);
        department.setIsActive(isActive);
        department.getEmployees()
                .forEach(emp->emp.setIsActive(isActive));
        departmentRepo.save(department);
    }


    @Override
    public PageResponse<EmployeeResponse> getEmployeesByDepartment(
            UUID departmentId,
            Pageable pageable
    ) {
        if (!departmentRepo.existsById(departmentId)) {
            throw new NotFoundException("Department not found");
        }



        return PageResponse.from(
                empRepo.findByDepartmentId(departmentId, pageable)
                        .map(employeeMapper::toResponse)
        );
    }

    @Override
    public DepartmentStatisticsResponse getDepartmentStatistics(UUID departmentId) {
        Department foundDepartment = findDepartmentById(departmentId);
        Long count = empRepo.countEmployeeByDepartmentId(departmentId);
        Long active = empRepo.countAllByIsActiveAndDepartment(true, foundDepartment);
        BigDecimal avg = empRepo.getEmployeesAvgSalaryByDepartment(departmentId);

        return DepartmentStatisticsResponse.builder()
                .departmentName(foundDepartment.getName())
                .totalEmployees(count)
                .totalActiveEmployees(active)
                .averageSalary(avg.setScale(8, RoundingMode.HALF_EVEN))
                .build();
    }

    private Department findDepartmentById(UUID departmentId) {
        return departmentRepo.findById(departmentId)
                .orElseThrow(
                () -> new NotFoundException("Department not found")
        );
    }
}
