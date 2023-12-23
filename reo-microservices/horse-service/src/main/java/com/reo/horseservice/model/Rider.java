package com.reo.horseservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rider")
@NamedQuery(name="Rider.findAll", query="SELECT r FROM Rider r")
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRider", nullable = false)
    private int idRider;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    private Date dateOfEnrollment;

    private String name;

    private String surname;

    @OneToMany(mappedBy="rider")
    private List<Favorite> favorites;

    @ManyToOne
    @JoinColumn(name="idCoach")
    private Coach coach;

    @OneToMany(mappedBy="rider")
    private List<Session> sessions;
}