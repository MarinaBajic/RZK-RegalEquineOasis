package com.reo.horseservice.service;

import com.reo.horseservice.dto.HorseRequest;
import com.reo.horseservice.dto.HorseResponse;
import com.reo.horseservice.exception.BreedDoesntExistException;
import com.reo.horseservice.exception.HorseAlreadyExistsException;
import com.reo.horseservice.exception.HorseDoesNotExistException;
import com.reo.horseservice.model.Breed;
import com.reo.horseservice.model.Favorite;
import com.reo.horseservice.model.Horse;
import com.reo.horseservice.model.Session;
import com.reo.horseservice.repository.BreedRepository;
import com.reo.horseservice.repository.FavoriteRepository;
import com.reo.horseservice.repository.HorseRepository;
import com.reo.horseservice.repository.SessionRepository;
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

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private SessionRepository sessionRepository;

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


    public List<HorseResponse> findAllHorsesByBreed(int idBreed) {
        Optional<Breed> breedOptional = breedRepository.findById(idBreed);
        if (breedOptional.isEmpty())
            throw new BreedDoesntExistException("Breed with id: '" + idBreed + "' does not exist.", idBreed);

        Breed breed = breedOptional.get();
        List<Horse> horses = horseRepository.findAllByBreed(breed);
        return horses.stream().map(this::mapToHorseResponse).toList();
    }

    public void deleteHorse(int idHorse) {
        Optional<Horse> horseOptional = horseRepository.findById(idHorse);
        if (horseOptional.isEmpty())
            throw new HorseDoesNotExistException("Horse with id: '" + idHorse + "' does not wxist in the DB.", idHorse);

        Horse horse = horseOptional.get();
        List<Favorite> favorites = favoriteRepository.findAllByHorse(horse);
        for (Favorite f: favorites) {
            favoriteRepository.delete(f);
        }
        List<Session> sessions = sessionRepository.findAllByHorse(horse);
        for (Session s: sessions) {
            sessionRepository.delete(s);
        }
        horseRepository.delete(horse);
        log.info("Horse with id: {} successfully deleted.", idHorse);
    }
}
