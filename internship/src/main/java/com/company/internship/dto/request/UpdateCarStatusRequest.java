package com.company.internship.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarStatusRequest {

    @NotBlank
    private String status;
}