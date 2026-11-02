package com.rotana.springmvc.controller;

import com.rotana.springmvc.domain.Employee;
import com.rotana.springmvc.dto.CreateEmployeeRequest;
import com.rotana.springmvc.dto.EmployeeResponse;
import com.rotana.springmvc.dto.UpdateEmployeeRequest;
import com.rotana.springmvc.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(@Valid @RequestBody CreateEmployeeRequest request){
        return service.createEmployee(request);
    }

    @PatchMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable int id,
                                           @Valid @RequestBody UpdateEmployeeRequest request){
        return service.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping("/search")
    public List<EmployeeResponse> searchEmployeesByName(
            @RequestParam String name
    ){
        return service.searchByName(name);
    }



}
