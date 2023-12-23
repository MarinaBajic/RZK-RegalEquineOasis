package com.reo.horseservice.repository;

import com.reo.horseservice.model.Breed;
import com.reo.horseservice.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HorseRepository extends JpaRepository<Horse, Integer> {

    Optional<Horse> findByFullName(String fullName);

    List<Horse> findAllByBreed(Breed breed);
}