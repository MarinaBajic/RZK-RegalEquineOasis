package com.reo.favoriteservice.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "favorite")
@NamedQuery(name="Favorite.findAll", query="SELECT f FROM Favorite f")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFavorite", nullable = false)
    private int idFavorite;

    @ManyToOne
    @JoinColumn(name="idHorse")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name="idRider")
    private Rider rider;
}