package com.reo.appservice.controller;

import com.reo.appservice.dto.*;
import com.reo.appservice.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HorseResponse> showHorses() {
        return appService.showHorses();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HorseResponse> searchHorses(@RequestParam int idBreed) {
        return appService.searchHorses(idBreed);
    }

    @GetMapping("/favorite/rider")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteHorse> showFavoriteHorses(@RequestParam int idRider) {
        return appService.showFavoriteHorses(idRider);
    }

    @PostMapping("/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public void addToFavorites(@RequestBody FavoriteRequest favoriteRequest) {
        appService.addToFavorites(favoriteRequest);
    }

    @DeleteMapping("/favorite/delete")
    @ResponseStatus(HttpStatus.OK)
    public void removeFromFavorites(@RequestParam int idRider, @RequestParam int idHorse) {
        appService.removeFromFavorites(idRider, idHorse);
    }

    @PostMapping("/session/new")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SessionResponse> newSession(@RequestBody SessionRequest sessionRequest) {
        return appService.newSession(sessionRequest);
    }

    @GetMapping("/session")
    @ResponseStatus(HttpStatus.OK)
    public List<SessionResponse> showSessions(@RequestParam("id-rider") int idRider) {
        return appService.showSessions(idRider);
    }

    // TODO rename all request params
}
