package com.reo.appservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteHorse {
    private int idHorse;
    private String horseFullName;
    private String horseNickname;
    private String horseGender;
    private Date horseDateOfBirth;
    private int idRider;
}
