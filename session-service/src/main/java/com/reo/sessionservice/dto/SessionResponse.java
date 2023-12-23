package com.reo.sessionservice.dto;

import com.reo.sessionservice.model.Horse;
import com.reo.sessionservice.model.Rider;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private Horse horse;
    private Rider rider;
}
