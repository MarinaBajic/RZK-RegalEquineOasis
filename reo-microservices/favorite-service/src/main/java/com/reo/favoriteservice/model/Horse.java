package com.reo.favoriteservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horse")
@NamedQuery(name="Horse.findAll", query="SELECT h FROM Horse h")
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHorse", nullable = false)
    private int idHorse;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Size(min=1, message="Horses full name is required!")
    private String fullName;

    @Size(min=1, max=1, message="Horses gender must be one letter!")
    private String gender;

    private String nickname;

    @OneToMany(mappedBy="horse")
    private List<Favorite> favorites;

    @ManyToOne
    @JoinColumn(name="idBreed")
    private Breed breed;

    @OneToMany(mappedBy="horse")
    private List<Session> sessions;
}