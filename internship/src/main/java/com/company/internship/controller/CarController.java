package com.company.internship.controller;

import com.company.internship.dto.request.CreateCarRequest;
import com.company.internship.dto.response.CarResponse;
import com.company.internship.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.company.internship.dto.request.UpdateCarRequest;
import com.company.internship.dto.request.UpdateCarStatusRequest;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> create(
            @Valid @RequestBody CreateCarRequest request) {

        CarResponse response = carService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCarRequest request) {

        return ResponseEntity.ok(carService.update(id, request));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<CarResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCarStatusRequest request) {

        return ResponseEntity.ok(
                carService.updateStatus(id, request.getStatus())
        );
    }
}
