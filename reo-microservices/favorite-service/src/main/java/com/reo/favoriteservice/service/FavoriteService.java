package com.reo.favoriteservice.service;

import com.reo.exception.CustomResponseEntityExceptionHandler;
import com.reo.exception.EntityDoesNotExistException;
import com.reo.exception.UnableToAddNewEntityException;
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

    public void addNewFavorite(FavoriteRequest favoriteRequest) {
        Optional<Horse> horseExists = horseRepository.findById(favoriteRequest.getIdHorse());
        if (horseExists.isEmpty())
            throw new EntityDoesNotExistException("Horse with id: " + favoriteRequest.getIdHorse() + " does not exist in the DB.", favoriteRequest.getIdHorse());
        Horse horse = horseExists.get();

        Optional<Rider> riderExists = riderRepository.findById(favoriteRequest.getIdRider());
        if (riderExists.isEmpty())
            throw new EntityDoesNotExistException("Rider with id: " + favoriteRequest.getIdRider() + " does not exist in the DB.", favoriteRequest.getIdRider());
        Rider rider = riderExists.get();

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
        Optional<Favorite> favoriteOptional = favoriteRepository.findById(idFavorite);
        if (favoriteOptional.isEmpty())
            throw new EntityDoesNotExistException("Favorite with id: " + idFavorite + " does not exist in the DB.", idFavorite);

        Favorite favorite = favoriteOptional.get();
        favoriteRepository.delete(favorite);
        log.info("Favorite with id: {} successfully deleted.", idFavorite);
    }
}
