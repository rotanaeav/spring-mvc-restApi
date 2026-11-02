package com.rotana.springmvc.service.impl;

import com.rotana.springmvc.domain.Employee;
import com.rotana.springmvc.dto.CreateEmployeeRequest;
import com.rotana.springmvc.dto.EmployeeResponse;
import com.rotana.springmvc.dto.UpdateEmployeeRequest;
import com.rotana.springmvc.exception.CustomExceptionHandling;
import com.rotana.springmvc.mapper.EmployeeMapper;
import com.rotana.springmvc.repository.EmployeeRepository;
import com.rotana.springmvc.service.EmployeeService;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    // inject repo :
    private final EmployeeRepository empRepo;
    // inject mapper :
    private final EmployeeMapper empMapper;

    // auto id :
    private static int nextId = 4;

    @Override
    public List<EmployeeResponse> getAll() {
//        return empRepo.getEmployees().stream()
//                .map(();
        return empRepo.getEmployees()
                .stream()
                .map(empMapper::toResponse) // we can : emp-> mapper.toResponse(emp)
                .toList();
    }

    @Override
    public EmployeeResponse getById(int id) {
        return findById(id) // replace code cher with helper method
                .map(empMapper::toResponse) // opt to empResponse
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee with id : %d not found!".formatted(id))
                );
    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        // create obj with condiction :
        if(checkIfEmailExists(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists!");
        }

        Employee employee = empMapper.fromCreateEmployeeRequest(request); // map request to obj employee
        employee.setId(nextId++);
        employee.setIsActive(true);

        empRepo.getEmployees().add(employee); // add to repo

        return empMapper.toResponse(employee); // map to response
    }

    @Override
    public EmployeeResponse updateEmployee(int id, UpdateEmployeeRequest request) {
        // get found emp
        Employee foundEmp = findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee with id : %d not found!".formatted(id))
        );
        // check email but ignore the current email ;
        if(checkIfEmailExists(request.email()) && !foundEmp.getEmail().equals(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists!");
        }
        // map found with request.fields
        empMapper.fromUpdateEmployee(request,foundEmp);
        // response when success
        return empMapper.toResponse(foundEmp);
    }

    @Override
    public void deleteById(int id) {
        Employee foundEmp = findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee with id : %d not found!".formatted(id))
        );
        // remove
        empRepo.getEmployees()
                .remove(foundEmp);
    }

    private Boolean checkIfEmailExists(String email) {
        return empRepo.getEmployees()
                .stream()
                .anyMatch(emp ->emp.getEmail().equals(email));
    }
    private Optional<Employee> findById(int id) {
        return empRepo.getEmployees()
                .stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();
    }
}
