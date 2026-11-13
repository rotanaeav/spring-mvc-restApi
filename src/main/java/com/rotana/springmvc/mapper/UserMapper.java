package com.rotana.springmvc.mapper;

import com.rotana.springmvc.domain.User;
import com.rotana.springmvc.dto.user.CreateUserRequest;
import com.rotana.springmvc.dto.user.UpdateUserRequest;
import com.rotana.springmvc.dto.user.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")

public interface UserMapper {


    UserResponse toResponse(User user);

    User toEntity(CreateUserRequest createUserRequest);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    void updateToEntity(
            UpdateUserRequest request,
            @MappingTarget User user
    );
}
