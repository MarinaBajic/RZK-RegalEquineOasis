package com.reo.horseservice.service;

import com.reo.horseservice.dto.HorseRequest;
import com.reo.horseservice.dto.HorseResponse;
import com.reo.horseservice.exception.HorseAlreadyExistsException;
import com.reo.horseservice.model.Breed;
import com.reo.horseservice.model.Horse;
import com.reo.horseservice.repository.BreedRepository;
import com.reo.horseservice.repository.HorseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HorseService {

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private BreedRepository breedRepository;

    public List<HorseResponse> getAllHorses() {
        List<Horse> horses = horseRepository.findAll();
        return horses.stream().map(this::mapToHorseResponse).toList();
    }

    private HorseResponse mapToHorseResponse(Horse horse) {
        return HorseResponse.builder()
                .idHorse(horse.getIdHorse())
                .dateOfBirth(horse.getDateOfBirth())
                .fullName(horse.getFullName())
                .gender(horse.getGender())
                .nickname(horse.getNickname())
                .breed(horse.getBreed())
                .build();
    }

    public void addNewHorse(HorseRequest horseRequest) {
        Optional<Horse> horseExists = horseRepository.findByFullName(horseRequest.getFullName());
        if (horseExists.isPresent()) {
            throw new HorseAlreadyExistsException("Horse with full name: '" + horseRequest.getFullName() + "' already exists in the DB.", horseExists.get().getIdHorse());
        }

        Optional<Breed> defaultBreedOptional = breedRepository.findByName("Other");
        Breed defaultBreed;
        if (defaultBreedOptional.isEmpty()) {
            defaultBreed = Breed.builder().name("Other").coatColor("Other").build();
            breedRepository.save(defaultBreed);
        }
        else
            defaultBreed = defaultBreedOptional.get();

        Horse horse = Horse.builder()
                .dateOfBirth(horseRequest.getDateOfBirth())
                .fullName(horseRequest.getFullName())
                .gender(horseRequest.getGender())
                .nickname(horseRequest.getNickname())
                .breed(breedRepository.findById(horseRequest.getIdBreed()).orElse(defaultBreed))
                .build();

        horseRepository.save(horse);
        log.info("Horse with id: {} is saved.", horse.getIdHorse());
    }
}
