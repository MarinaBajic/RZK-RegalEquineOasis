package com.reo.appservice.service;

import com.reo.appservice.dto.*;
import com.reo.appservice.feign.FavoriteProxy;
import com.reo.appservice.feign.HorseProxy;
import com.reo.appservice.feign.SessionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private HorseProxy horseProxy;

    @Autowired
    private FavoriteProxy favoriteProxy;

    @Autowired
    private SessionProxy sessionProxy;

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

    public List<SessionResponse> newSession(SessionRequest sessionRequest) {
        if (sessionRequest.getIdHorse() == -1) {
            List<FavoriteHorse> favoriteHorses = favoriteProxy.getAllFavoriteHorsesForRider(sessionRequest.getIdRider());
            if (favoriteHorses.isEmpty()) {
                List<HorseResponse> allHorses = horseProxy.getAllHorses();
                sessionRequest.setIdHorse(allHorses.get(0).getIdHorse());
            } else {
                sessionRequest.setIdHorse(favoriteHorses.get(0).getIdHorse());
            }
        }
        sessionProxy.addNewSession(sessionRequest);
        return sessionProxy.getAllSessionsForRider(sessionRequest.getIdRider());
    }

    public List<SessionResponse> showSessions(int idRider) {
        return sessionProxy.getAllSessionsForRider(idRider);
    }

    public String debug() {
        return horseProxy.debug();
    }
}
