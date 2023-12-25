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
public class SessionRequest {
    private Date date;
    private String description;
    private String time;
    private int idHorse;
    private int idRider;
}
