package com.reo.riderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
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

    public Rider addRider(Rider rider) {
        getRiders().add(rider);
        rider.setCoach(this);

        return rider;
    }

    public Rider removeRider(Rider rider) {
        getRiders().remove(rider);
        rider.setCoach(null);

        return rider;
    }
}