package com.reo.riderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @Size(min=1, message = "Name of rider is required!")
    private String name;

    @Size(min=1, message = "Surname of rider is required!")
    private String surname;

    @OneToMany(mappedBy="rider")
    private List<Favorite> favorites;

    @ManyToOne
    @JoinColumn(name="idCoach")
    private Coach coach;

    @OneToMany(mappedBy="rider")
    private List<Session> sessions;
}