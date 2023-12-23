package com.reo.riderservice.dto;

import com.reo.riderservice.model.Coach;
import com.reo.riderservice.model.Favorite;
import com.reo.riderservice.model.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
