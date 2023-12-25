package com.reo.appservice.service;

import com.reo.appservice.dto.FavoriteHorse;
import com.reo.appservice.dto.FavoriteRequest;
import com.reo.appservice.dto.HorseResponse;
import com.reo.appservice.feign.FavoriteProxy;
import com.reo.appservice.feign.HorseProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private HorseProxy horseProxy;

    @Autowired
    private FavoriteProxy favoriteProxy;

    public List<HorseResponse> showHorses() {
        return horseProxy.getAllHorses();
    }

    public List<HorseResponse> searchHorses(int idBreed) {
        return horseProxy.findAllHorsesByBreed(idBreed);
    }

    public List<FavoriteHorse> showFavoriteHorses(int idRider) {
        return favoriteProxy.getAllFavoriteHorsesForRider(idRider);
    }
    public void addToFavorites(FavoriteRequest favoriteRequest) {
        favoriteProxy.addNewFavorite(favoriteRequest);
    }

    public void removeFromFavorites(int idRider, int idHorse) {
        favoriteProxy.deleteFavorite(idRider, idHorse);
    }
}
