package com.reo.horseservice.repository;

import com.reo.horseservice.model.Favorite;
import com.reo.horseservice.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findAllByHorse(Horse horse);
}