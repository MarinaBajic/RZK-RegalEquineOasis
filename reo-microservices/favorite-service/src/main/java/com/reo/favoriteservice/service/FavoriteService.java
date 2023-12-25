package com.reo.favoriteservice.service;

import com.reo.exception.CustomResponseEntityExceptionHandler;
import com.reo.exception.EntityDoesNotExistException;
import com.reo.exception.UnableToAddNewEntityException;
import com.reo.favoriteservice.dto.FavoriteHorse;
import com.reo.favoriteservice.dto.FavoriteRequest;
import com.reo.favoriteservice.dto.FavoriteResponse;
import com.reo.favoriteservice.model.Favorite;
import com.reo.favoriteservice.model.Horse;
import com.reo.favoriteservice.model.Rider;
import com.reo.favoriteservice.repository.FavoriteRepository;
import com.reo.favoriteservice.repository.HorseRepository;
import com.reo.favoriteservice.repository.RiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Configuration
@Import({ CustomResponseEntityExceptionHandler.class })
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private RiderRepository riderRepository;

    public List<FavoriteResponse> getAllFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();
        return favorites.stream().map(this::mapToFavoriteResponse).toList();
    }

    private FavoriteResponse mapToFavoriteResponse(Favorite favorite) {
        return FavoriteResponse.builder()
                .idFavorite(favorite.getIdFavorite())
                .horseFullName(favorite.getHorse().getFullName())
                .riderName(favorite.getRider().getName() + " " + favorite.getRider().getSurname())
                .build();
    }

    public List<FavoriteHorse> getAllFavoriteHorsesForRider(int idRider) {
        Rider rider = riderRepository.findById(idRider)
                .orElseThrow(() -> new EntityDoesNotExistException("Rider with id: " + idRider + " does not exist in the DB.", idRider));

        List<Favorite> favorites = favoriteRepository.findAllByRider(rider);
        return favorites.stream().map(this::mapToFavoriteHorse).toList();
    }

    private FavoriteHorse mapToFavoriteHorse(Favorite favorite) {
        return FavoriteHorse.builder()
                .horseFullName(favorite.getHorse().getFullName())
                .horseNickname(favorite.getHorse().getNickname())
                .horseGender(favorite.getHorse().getGender())
                .horseDateOfBirth(favorite.getHorse().getDateOfBirth())
                .idRider(favorite.getRider().getIdRider())
                .build();
    }

    public void addNewFavorite(FavoriteRequest favoriteRequest) {
        Horse horse = horseRepository.findById(favoriteRequest.getIdHorse())
                .orElseThrow(() -> new EntityDoesNotExistException("Horse with id: " + favoriteRequest.getIdHorse() + " does not exist in the DB.", favoriteRequest.getIdHorse()));

        Rider rider = riderRepository.findById(favoriteRequest.getIdRider())
                .orElseThrow(() -> new EntityDoesNotExistException("Rider with id: " + favoriteRequest.getIdRider() + " does not exist in the DB.", favoriteRequest.getIdRider()));

        Optional<Favorite> alreadyIsFavorite = favoriteRepository.findByHorseAndRider(horse, rider);
        if (alreadyIsFavorite.isPresent())
            throw new UnableToAddNewEntityException("Horse with id: " + horse.getIdHorse() + " is already favorite for rider with id: " + rider.getIdRider());

        Favorite favorite = Favorite.builder()
                .horse(horse)
                .rider(rider)
                .build();

        favoriteRepository.save(favorite);
        log.info("Favorite horse with id: {} is saved.", favorite.getIdFavorite());
    }

    public void deleteFavorite(int idFavorite) {
        Favorite favorite = favoriteRepository.findById(idFavorite)
                .orElseThrow(() -> new EntityDoesNotExistException("Favorite with id: " + idFavorite + " does not exist in the DB.", idFavorite));

        favoriteRepository.delete(favorite);
        log.info("Favorite with id: {} successfully deleted.", idFavorite);
    }
}
