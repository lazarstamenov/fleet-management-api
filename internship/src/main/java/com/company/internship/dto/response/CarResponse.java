package com.company.internship.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarResponse {

    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String vin;
    private String licensePlate;
    private String status;
    private Long ownerId;
}