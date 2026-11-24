package com.rotana.springmvc.service.impl;

import com.rotana.springmvc.domain.Department;
import com.rotana.springmvc.domain.Employee;
import com.rotana.springmvc.dto.employee.CreateEmployeeRequest;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.dto.employee.TransferEmployeeRequest;
import com.rotana.springmvc.dto.employee.UpdateEmployeeRequest;
import com.rotana.springmvc.exception.BadRequestException;
import com.rotana.springmvc.exception.ConflictException;
import com.rotana.springmvc.exception.NotFoundException;
import com.rotana.springmvc.mapper.EmployeeMapper;
import com.rotana.springmvc.repository.DepartmentRepository;
import com.rotana.springmvc.repository.EmployeeRepository;
import com.rotana.springmvc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    // inject repo :
    private final EmployeeRepository empRepo;
    // inject mapper :
    private final EmployeeMapper empMapper;

    private final DepartmentRepository departmentRepo;

    // auto id :
//    private static int nextId = 4;

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
    public EmployeeResponse getById(UUID id) {
        return empMapper.toResponse(findEmpById(id));
    }

    @Override
    @Transactional
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        // create obj with condiction :
        if (checkIfEmailExists(request.email())) {
            throw new ConflictException("Email already exists!");
        }
        // validate Departmentid
        Department existDepartment = departmentRepo.findById(request.departmentId())
                .orElseThrow(() -> new NotFoundException("Department not found!"));

        Employee employee = empMapper.fromCreateEmployeeRequest(request); // map request to obj employee
        employee.setIsActive(true);
        employee.setDepartment(existDepartment);
        empRepo.save(employee); // add to repo

        return empMapper.toResponse(employee); // map to response
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(UUID id, UpdateEmployeeRequest request) {
        // get found emp
        Employee foundEmp = findEmpById(id);
        // check email but ignore the current email ;
        if (checkIfEmailExists(request.email()) && !foundEmp.getEmail().equals(request.email())) {
            throw new ConflictException("Email already exists!");
        }
        Department department = request.departmentId() == null ? null : departmentRepo.findById(request.departmentId())
                .orElseThrow(() -> new NotFoundException("Department not found!"));
        // map found with request.fields
        empMapper.fromUpdateEmployee(request, foundEmp);
        if (department != null) {
            foundEmp.setDepartment(department);
        }
        empRepo.save(foundEmp);// add updated to repo
        // response when success
        return empMapper.toResponse(foundEmp);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {

//        Employee foundEmp = findEmpById(id).orElseThrow(
//                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee with id : %d not found!".formatted(id))
//        );
        // remove
//        empRepo.remove(foundEmp);
        findEmpById(id);
        empRepo.deleteById(id);
    }

    @Override
    public List<EmployeeResponse> searchByNameOrEmail(String keyword) {
//        return empRepo.findAll()
//                .stream()
//                .filter((emp->emp.getName().toLowerCase().contains(keyword.toLowerCase())))
//                .map(empMapper::toResponse)
//                .toList();
        return empRepo.findAllByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword).stream()
                .map(empMapper::toResponse).toList();
    }

    @Override
    public List<EmployeeResponse> findByName(String name) {
        return empRepo.findByName(name).stream()
                .map(empMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public List<EmployeeResponse> bulkCreateEmployees(List<CreateEmployeeRequest> bulkRequest) {
//        return bulkRequest.stream()
//                .map(empMapper::fromCreateEmployeeRequest)
//                .map(emp->emp.setIsActive(true))
//                .map(empRepo::save)
//                .map(empMapper::toResponse)
//                .toList();
        return bulkRequest.stream()
                .map(this::createEmployee)
                .toList();
    }

    @Override
    @Transactional
    public EmployeeResponse transferEmployee(UUID employeeId, TransferEmployeeRequest request) {
        Employee emp = findEmpById(employeeId);
        Department dep = findDepartmentById(request.newDepartmentId());

        if (dep.getIsActive()!=Boolean.TRUE){
            throw new BadRequestException("Department is not active");
        }

        if (dep.getId().equals(emp.getDepartment().getId())){
            throw new BadRequestException("Employee is already in this department");
        }

        emp.setDepartment(dep);
        empRepo.save(emp);
        return empMapper.toResponse(emp);
    }


    private Boolean checkIfEmailExists(String email) {
//        return empRepo.existsByEmail(email);
        return empRepo.findAll()
                .stream()
                .anyMatch(emp -> emp.getEmail().equals(email));
    }

    private Employee findEmpById(UUID id) {
        return empRepo.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }
    private Department findDepartmentById(UUID id) {
        return departmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Department not found"));
    }
}
