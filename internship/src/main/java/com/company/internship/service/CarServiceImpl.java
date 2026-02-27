package com.company.internship.service;

import com.company.internship.domain.entity.Car;
import com.company.internship.domain.entity.User;
import com.company.internship.domain.exception.BusinessException;
import com.company.internship.domain.exception.NotFoundException;
import com.company.internship.domain.repository.CarRepository;
import com.company.internship.domain.repository.UserRepository;
import com.company.internship.dto.request.CreateCarRequest;
import com.company.internship.dto.request.UpdateCarRequest;
import com.company.internship.dto.response.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;


    @Override
    public CarResponse create(CreateCarRequest request) {

        if (carRepository.existsByVin(request.getVin())) {
            throw new BusinessException("VIN already exists");
        }

        if (carRepository.existsByLicensePlate(request.getLicensePlate())) {
            throw new BusinessException("License plate already exists");
        }

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new NotFoundException("Owner not found"));

        Car car = new Car();
        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setVin(request.getVin());
        car.setLicensePlate(request.getLicensePlate());
        car.setOwner(owner);

        try {
            car.setStatus(Car.Status.valueOf(request.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid status value");
        }

        Car savedCar = carRepository.save(car);

        return mapToResponse(savedCar);
    }

    @Override
    public CarResponse getById(Long id) {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found"));

        return mapToResponse(car);
    }

    @Override
    public List<CarResponse> getAll() {
        return carRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        if (!carRepository.existsById(id)) {
            throw new NotFoundException("Car not found");
        }

        carRepository.deleteById(id);
    }

    @Override
    public CarResponse update(Long id, UpdateCarRequest request) {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found"));

        if (request.getMake() != null) {
            car.setMake(request.getMake());
        }

        if (request.getModel() != null) {
            car.setModel(request.getModel());
        }

        if (request.getYear() != null) {
            car.setYear(request.getYear());
        }

        if (request.getVin() != null) {
            if (carRepository.existsByVin(request.getVin())
                    && !car.getVin().equals(request.getVin())) {
                throw new BusinessException("VIN already exists");
            }
            car.setVin(request.getVin());
        }

        if (request.getLicensePlate() != null) {
            if (carRepository.existsByLicensePlate(request.getLicensePlate())
                    && !car.getLicensePlate().equals(request.getLicensePlate())) {
                throw new BusinessException("License plate already exists");
            }
            car.setLicensePlate(request.getLicensePlate());
        }

        if (request.getOwnerId() != null) {
            User newOwner = userRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new NotFoundException("Owner not found"));
            car.setOwner(newOwner);
        }

        if (request.getStatus() != null) {
            try {
                car.setStatus(Car.Status.valueOf(request.getStatus()));
            } catch (IllegalArgumentException e) {
                throw new BusinessException("Invalid status value");
            }
        }

        Car updatedCar = carRepository.save(car);

        return mapToResponse(updatedCar);
    }

    private CarResponse mapToResponse(Car car) {
        return new CarResponse(
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getStatus().name(),
                car.getOwner().getId()
        );
    }

    @Override
    public List<CarResponse> getByOwnerId(Long ownerId) {

        if (!userRepository.existsById(ownerId)) {
            throw new NotFoundException("User not found");
        }

        return carRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public CarResponse updateStatus(Long id, String status) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found"));

        try {
            car.setStatus(Car.Status.valueOf(status));
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid status value");
        }

        Car updatedCar = carRepository.save(car);
        return mapToResponse(updatedCar);
    }
}