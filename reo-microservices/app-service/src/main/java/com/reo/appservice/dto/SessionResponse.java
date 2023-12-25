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
public class SessionResponse {
    private int idSession;
    private Date date;
    private String description;
    private String time;
    private String horseFullName;
    private String riderName;
}
