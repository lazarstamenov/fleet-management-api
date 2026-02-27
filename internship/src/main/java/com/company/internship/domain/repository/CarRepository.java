package com.company.internship.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.internship.domain.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByVin(String vin);

    boolean existsByLicensePlate(String licensePlate);

    List<Car> findByOwnerId(Long ownerId);
}