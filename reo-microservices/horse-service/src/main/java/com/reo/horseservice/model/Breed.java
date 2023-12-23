package com.reo.horseservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "breed")
@NamedQuery(name="Breed.findAll", query="SELECT b FROM Breed b")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBreed", nullable = false)
    private int idBreed;

    private String coatColor;

    private String name;

    @OneToMany(mappedBy="breed")
    @JsonIgnore
    private List<Horse> horses;
}