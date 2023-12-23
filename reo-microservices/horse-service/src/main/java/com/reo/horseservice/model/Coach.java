package com.reo.horseservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coach")
@NamedQuery(name="Coach.findAll", query="SELECT c FROM Coach c")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCoach", nullable = false)
    private int idCoach;

    private String name;

    private String surname;

    private int yearsOfExperience;

    @OneToMany(mappedBy="coach")
    private List<Rider> riders;
}