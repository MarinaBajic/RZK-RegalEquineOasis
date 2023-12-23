package com.reo.horseservice.dto;

import com.reo.horseservice.model.Breed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HorseResponse {
    private int idHorse;
    private Date dateOfBirth;
    private String fullName;
    private String gender;
    private String nickname;
    private Breed breed;
}