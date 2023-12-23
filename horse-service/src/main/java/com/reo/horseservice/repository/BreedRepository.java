package com.reo.horseservice.repository;

import com.reo.horseservice.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreedRepository extends JpaRepository<Breed, Integer> {

    public Optional<Breed> findByName(String name);
}