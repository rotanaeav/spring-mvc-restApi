package com.rotana.springmvc.service;

import com.rotana.springmvc.dto.user.CreateUserRequest;
import com.rotana.springmvc.dto.user.UpdateUserRequest;
import com.rotana.springmvc.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse updateUserStatus(Boolean status, Integer id);
    List<UserResponse> searchUsersWithConcat(String keyword);
    List<UserResponse> searchUsersWithoutConcat(String keyword);
    List<UserResponse> searchUsersNameContains(String keyword);
    List<UserResponse> searchUnderScore(String keyword);
    List<UserResponse> searchNameIgnoreCase(String keyword);
    UserResponse createUsers(CreateUserRequest createUserRequest);
    UserResponse updateUser(Integer id,UpdateUserRequest updateUserRequest);
}
