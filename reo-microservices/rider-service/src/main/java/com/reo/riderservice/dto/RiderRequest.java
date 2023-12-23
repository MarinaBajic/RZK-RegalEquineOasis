package com.reo.riderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class RiderRequest {
    private String address;
    private Date dateOfBirth;
    private Date dateOfEnrollment;
    private String name;
    private String surname;
    private int idCoach;
}
