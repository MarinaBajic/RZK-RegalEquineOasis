package com.reo.favoriteservice.controller;

import com.reo.favoriteservice.dto.FavoriteHorse;
import com.reo.favoriteservice.dto.FavoriteRequest;
import com.reo.favoriteservice.dto.FavoriteResponse;
import com.reo.favoriteservice.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteResponse> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/rider")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteHorse> getAllFavoriteHorsesForRider(@RequestParam("id-rider") int idRider) {
        return favoriteService.getAllFavoriteHorsesForRider(idRider);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewFavorite(@RequestBody FavoriteRequest favoriteRequest) {
        favoriteService.addNewFavorite(favoriteRequest);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorite(@RequestParam("id-rider") int idRider, @RequestParam("id-horse") int idHorse) {
        favoriteService.deleteFavorite(idRider, idHorse);
    }
}
