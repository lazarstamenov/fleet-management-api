package com.company.internship.service;

import com.company.internship.dto.request.CreateCarRequest;
import com.company.internship.dto.response.CarResponse;
import com.company.internship.dto.request.UpdateCarRequest;

import java.util.List;

public interface CarService {

    CarResponse create(CreateCarRequest request);

    CarResponse getById(Long id);

    List<CarResponse> getByOwnerId(Long ownerId);

    List<CarResponse> getAll();

    CarResponse update(Long id, UpdateCarRequest request);

    CarResponse updateStatus(Long id, String status);

    void delete(Long id);
}