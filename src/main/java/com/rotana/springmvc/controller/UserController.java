package com.rotana.springmvc.controller;

import com.rotana.springmvc.dto.user.CreateUserRequest;
import com.rotana.springmvc.dto.user.UpdateStatusUserRequest;
import com.rotana.springmvc.dto.user.UpdateUserRequest;
import com.rotana.springmvc.dto.user.UserResponse;
import com.rotana.springmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}")
    public UserResponse updateUserStatus(
            @PathVariable Integer id, @RequestBody UpdateStatusUserRequest request
    ) {
        return userService.updateUserStatus(request.active(),id);
    }

    @GetMapping("/search")
    public List<UserResponse> searchWithConcat(@RequestParam String keyword){
        return userService.searchUsersWithConcat(keyword);
    }

    @GetMapping("/searchName")
    public List<UserResponse> searchName(@RequestParam String keyword){
        return userService.searchUsersWithoutConcat(keyword);
    }

    @GetMapping("/searchContains")
    public List<UserResponse> searchContains(@RequestParam String keyword){
        return userService.searchUsersNameContains(keyword);
    }
    @GetMapping("/searchUnderScore")
    public List<UserResponse> searchUnderScore(@RequestParam String keyword){
        return userService.searchUnderScore(keyword);
    }

    @GetMapping("/searchIgnore")
    public List<UserResponse> searchIgnoreCase(@RequestParam String keyword){
        return userService.searchNameIgnoreCase(keyword);
    }
    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUsers(createUserRequest);
    }

    @PatchMapping("/{id}")
    public UserResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable Integer id){
        return userService.updateUser(id, updateUserRequest);
    }
}
