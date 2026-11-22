package com.rotana.springmvc.controller;

import com.rotana.springmvc.dto.department.CreateDepartmentRequest;
import com.rotana.springmvc.dto.department.DepartmentResponse;
import com.rotana.springmvc.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    List<DepartmentResponse> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @PutMapping("/{id}/status")
    void updateDepartmentStatus(@PathVariable UUID id, @RequestParam Boolean isActive){
        departmentService.updateDepartmentStatus(id,isActive);
    }

    @PostMapping
    DepartmentResponse createDepartment(@Valid @RequestBody CreateDepartmentRequest request){
        return departmentService.createDepartment(request);
    }

}
