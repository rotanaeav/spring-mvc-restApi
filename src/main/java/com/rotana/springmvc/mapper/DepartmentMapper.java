package com.rotana.springmvc.mapper;

import com.rotana.springmvc.domain.Department;
import com.rotana.springmvc.dto.department.CreateDepartmentRequest;
import com.rotana.springmvc.dto.department.DepartmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse toDepartmentResponse(Department department);

    Department toDepartment(CreateDepartmentRequest createDepartmentRequest);
}
