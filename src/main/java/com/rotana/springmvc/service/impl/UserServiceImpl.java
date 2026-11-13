package com.rotana.springmvc.service.impl;

import com.rotana.springmvc.domain.User;
import com.rotana.springmvc.dto.user.CreateUserRequest;
import com.rotana.springmvc.dto.user.UpdateUserRequest;
import com.rotana.springmvc.dto.user.UserResponse;
import com.rotana.springmvc.mapper.UserMapper;
import com.rotana.springmvc.repository.UserRepository;
import com.rotana.springmvc.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponse updateUserStatus(Boolean status, Integer id) {
        User beforeUser = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        IO.println(beforeUser.getActive());
        int affected = userRepository.updateUserStatus(status, id);
        if (affected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> searchUsersWithConcat(String keyword) {
        return userRepository.searchByNameConcat(keyword)
                .stream().map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> searchUsersWithoutConcat(String keyword) {
        String fKeyword = "%" + keyword + "%";
        return userRepository.searchByName(fKeyword)
                .stream().map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> searchUsersNameContains(String keyword) {
        return userRepository.searchByNameContains(keyword)
                .stream().map(userMapper::toResponse)
                .toList();
    }

    //    @Override
//    public List<UserResponse> searchUnderScore(String keyword) {
//        if (keyword.contains("/"))
//            keyword = keyword.replace("/", "//");
//        if (keyword.contains("_"))
//            keyword = keyword.replace("_", "/_");
//
//        return userRepository.searchByNameUnderscore(keyword)
//                .stream().map(userMapper::toResponse)
//                .toList();
//    }
    @Override
    public List<UserResponse> searchUnderScore(String keyword) {
        return userRepository.searchByNameUnderscore(keyword)
                .stream().map(userMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> searchNameIgnoreCase(String keyword) {
        return userRepository.searchByNameIgnoreCase(keyword);
    }

    @Override
    public UserResponse createUsers(CreateUserRequest createUserRequest) {
        User userEntity = userMapper.toEntity(createUserRequest);
        User user = userRepository.save(userEntity);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(
            Integer id,
            UpdateUserRequest updateUserRequest
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
                );

        userMapper.updateToEntity(updateUserRequest, user);
//        userRepository.save(user);
        return userMapper.toResponse(user);
    }


}
