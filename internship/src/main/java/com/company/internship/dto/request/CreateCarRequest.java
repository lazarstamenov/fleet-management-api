package com.company.internship.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCarRequest {

    @NotNull
    @Min(value = 1950, message = "Godina mora da bude iznad 1950 ")
    @Max(value = 2027, message = "Godina ne sme da bude veca od 2027")
    private Integer year;

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @Size(min = 17, max = 17)
    private String vin;

    @NotBlank
    private String licensePlate;

    @NotNull
    private Long ownerId;

    @NotBlank
    private String status; // ACTIVE or INACTIVE
}