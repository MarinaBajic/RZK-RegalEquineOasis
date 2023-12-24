package com.reo.favoriteservice.repository;

import com.reo.favoriteservice.model.Favorite;
import com.reo.favoriteservice.model.Horse;
import com.reo.favoriteservice.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    Optional<Favorite> findByHorseAndRider(Horse horse, Rider rider);
    List<Favorite> findAllByRider(Rider rider);
}