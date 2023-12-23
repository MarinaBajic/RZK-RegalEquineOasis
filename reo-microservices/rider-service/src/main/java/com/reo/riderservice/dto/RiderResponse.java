package com.reo.riderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiderResponse {
    private int idRider;
    private String address;
    private Date dateOfBirth;
    private Date dateOfEnrollment;
    private String name;
    private String surname;
    private String coachName;
}
