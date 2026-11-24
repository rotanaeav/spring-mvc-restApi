package com.rotana.springmvc.controller;

import com.rotana.springmvc.dto.employee.CreateEmployeeRequest;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.dto.employee.TransferEmployeeRequest;
import com.rotana.springmvc.dto.employee.UpdateEmployeeRequest;
import com.rotana.springmvc.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    // inject service
    private final EmployeeService service;

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return service.getAll();
    }
    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployee(@PathVariable UUID employeeId) {
        return service.getById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@Valid @RequestBody CreateEmployeeRequest request){
        return service.createEmployee(request);
    }

    @PatchMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable UUID id,
                                           @Valid @RequestBody UpdateEmployeeRequest request){
        return service.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID id) {
        service.deleteById(id);
    }

    @GetMapping("/search")
    public List<EmployeeResponse> searchEmployeesByNameOrEmail(
            @RequestParam String keyword
    ){
        return service.searchByNameOrEmail(keyword);
    }
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<EmployeeResponse> bulkCreateEmployees(@Valid @RequestBody List<CreateEmployeeRequest> bulkRequest) {
        return service.bulkCreateEmployees(bulkRequest);
    }

    @PutMapping("/{employeeId}/transfer")
    public EmployeeResponse transferEmployee(@PathVariable UUID employeeId,
                                             @Valid @RequestBody TransferEmployeeRequest request){
        return service.transferEmployee(employeeId, request);
    }

}
