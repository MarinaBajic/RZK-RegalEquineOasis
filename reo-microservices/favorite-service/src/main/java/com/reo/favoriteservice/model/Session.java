package com.reo.favoriteservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "session")
@NamedQuery(name="Session.findAll", query="SELECT s FROM Session s")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSession", nullable = false)
    private int idSession;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

    private String time;

    @ManyToOne
    @JoinColumn(name="idHorse")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name="idRider")
    private Rider rider;
}