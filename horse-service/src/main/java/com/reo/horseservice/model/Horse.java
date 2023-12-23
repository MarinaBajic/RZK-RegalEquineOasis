package com.reo.horseservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    public Favorite addFavorite(Favorite favorite) {
        getFavorites().add(favorite);
        favorite.setHorse(this);

        return favorite;
    }

    public Favorite removeFavorite(Favorite favorite) {
        getFavorites().remove(favorite);
        favorite.setHorse(null);

        return favorite;
    }

    public Session addSession(Session session) {
        getSessions().add(session);
        session.setHorse(this);

        return session;
    }

    public Session removeSession(Session session) {
        getSessions().remove(session);
        session.setHorse(null);

        return session;
    }
}