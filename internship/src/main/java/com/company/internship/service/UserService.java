package com.company.internship.service;

import com.company.internship.dto.request.CreateUserRequest;
import com.company.internship.dto.request.UpdateUserRequest;
import com.company.internship.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getById(Long id);

    UserResponse update(Long id, UpdateUserRequest request);

    List<UserResponse> getAll();

    void delete(Long id);
}