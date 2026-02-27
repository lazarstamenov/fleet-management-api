package com.company.internship.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Getter
@Setter
public class UpdateCarRequest {

    private String make;
    private String model;
    @Min(value = 1950, message = "Godina mora da bude iznad 1950 ")
    @Max(value = 2027, message = "Godina ne sme da bude veca od 2027")
    private Integer year;

    @Size(min = 17, max = 17)
    private String vin;

    private String licensePlate;
    private Long ownerId;
    private String status;
}