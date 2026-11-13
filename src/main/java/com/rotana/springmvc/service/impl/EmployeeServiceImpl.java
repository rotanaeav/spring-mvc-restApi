package com.rotana.springmvc.service.impl;

import com.rotana.springmvc.domain.Employee;
import com.rotana.springmvc.dto.employee.CreateEmployeeRequest;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.dto.employee.UpdateEmployeeRequest;
import com.rotana.springmvc.mapper.EmployeeMapper;
import com.rotana.springmvc.repository.EmployeeRepository;
import com.rotana.springmvc.repository.EmployeesRepository;
import com.rotana.springmvc.service.EmployeeService;
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
    private final EmployeesRepository empRepo;
    // inject mapper :
    private final EmployeeMapper empMapper;

    // auto id :
    private static int nextId = 4;

    @Override
    public List<EmployeeResponse> getAll() {
//        return empRepo.getEmployees().stream()
//                .map(();
        return empRepo.findAll()
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

        empRepo.save(employee); // add to repo

        return empMapper.toResponse(employee); // map to response
    }

    @Override
    public EmployeeResponse updateEmployee(int id, UpdateEmployeeRequest request) {
        // get found emp
        Employee foundEmp = findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus
                        .NOT_FOUND,"Employee with id : %d not found!".formatted(id))
        );
        // check email but ignore the current email ;
        if(checkIfEmailExists(request.email()) && !foundEmp.getEmail().equals(request.email())){
            throw new ResponseStatusException(HttpStatus
                    .CONFLICT,"Email already exists!");
        }
        // map found with request.fields
        empMapper.fromUpdateEmployee(request,foundEmp);
        empRepo.save(foundEmp);// add updated to repo
        // response when success
        return empMapper.toResponse(foundEmp);
    }

    @Override
    public void deleteById(int id) {

//        Employee foundEmp = findById(id).orElseThrow(
//                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee with id : %d not found!".formatted(id))
//        );
        // remove
//        empRepo.remove(foundEmp);
        if (findById(id).isEmpty()){throw new
        ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found!");}
        else empRepo.deleteById(id);
    }

    @Override
    public List<EmployeeResponse> searchByName(String name) {
        return empRepo.findAll()
                .stream()
                .filter((emp->emp.getName().toLowerCase().contains(name.toLowerCase())))
                .map(empMapper::toResponse)
                .toList();
    }

    private Boolean checkIfEmailExists(String email) {
        return empRepo.findAll()
                .stream()
                .anyMatch(emp ->emp.getEmail().equals(email));
    }
    private Optional<Employee> findById(int id) {
        return empRepo.findById(id);
    }
}
