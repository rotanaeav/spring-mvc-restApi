package com.rotana.springmvc.controller;

import com.rotana.springmvc.dto.department.CreateDepartmentRequest;
import com.rotana.springmvc.dto.department.DepartmentResponse;
import com.rotana.springmvc.dto.department.DepartmentStatisticsResponse;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.service.DepartmentService;
import com.rotana.springmvc.utils.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    List<DepartmentResponse> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentId}/employees")
    public PageResponse<EmployeeResponse> getEmployeesByDepartment(@PathVariable UUID departmentId,
                                                                   @ParameterObject //better easier by Spring
                                                                   @PageableDefault(
                                                                           page = 0,
                                                                           size = 8,
                                                                           sort = "name",
                                                                           direction = Sort.Direction.ASC
                                                                   ) Pageable pageable
    ){
        return departmentService.getEmployeesByDepartment(departmentId, pageable);
    }
    @PutMapping("/{id}/status")
    void updateDepartmentStatus(@PathVariable UUID id, @RequestParam Boolean isActive){
        departmentService.updateDepartmentStatus(id,isActive);
    }

    @PostMapping
    DepartmentResponse createDepartment(@Valid @RequestBody CreateDepartmentRequest request){
        return departmentService.createDepartment(request);
    }

    @GetMapping("/{departmentId}/statistics")
    DepartmentStatisticsResponse getDepartmentStatistics(@PathVariable UUID departmentId){
        return departmentService.getDepartmentStatistics(departmentId);
    }

}
