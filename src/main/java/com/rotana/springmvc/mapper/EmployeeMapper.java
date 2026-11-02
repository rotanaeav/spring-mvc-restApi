package com.rotana.springmvc.mapper;

import com.rotana.springmvc.domain.Employee;
import com.rotana.springmvc.dto.CreateEmployeeRequest;
import com.rotana.springmvc.dto.EmployeeResponse;
import com.rotana.springmvc.dto.UpdateEmployeeRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    // Map from Domain to DTO : meaning accept employee and return as Response
    EmployeeResponse toResponse(Employee employee);

    // Map from DTO to Domain
    Employee fromCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest);

    // Map from DTO to Domain (update) (use MappingTarget and allow null for Patch)
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    void fromUpdateEmployee(
            UpdateEmployeeRequest updateEmployeeRequest,
            @MappingTarget Employee employee
    );
}

