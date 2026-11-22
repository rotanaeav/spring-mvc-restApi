package com.rotana.springmvc.mapper;

import com.rotana.springmvc.domain.Employee;
import com.rotana.springmvc.dto.employee.CreateEmployeeRequest;
import com.rotana.springmvc.dto.employee.EmployeeResponse;
import com.rotana.springmvc.dto.employee.UpdateEmployeeRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    // Map from Domain to DTO : meaning accept employee and return as Response
    EmployeeResponse toResponse(Employee employee);

    // Map from DTO to Domain

    @Mapping(target = "department", ignore = true)
    Employee fromCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest);

    // Map from DTO to Domain (update) (use MappingTarget and allow null for Patch)
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    void fromUpdateEmployee(
            UpdateEmployeeRequest updateEmployeeRequest,
            @MappingTarget Employee employee
    );
}

