package com.reo.appservice.feign;

import com.reo.appservice.dto.FavoriteHorse;
import com.reo.appservice.dto.FavoriteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="favorite-service")
public interface FavoriteProxy {

    @GetMapping("/api/favorite/rider")
    List<FavoriteHorse> getAllFavoriteHorsesForRider(@RequestParam("id-rider") int idRider);

    @PostMapping("/api/favorite")
    void addNewFavorite(@RequestBody FavoriteRequest favoriteRequest);

    @DeleteMapping("/api/favorite/delete")
    void deleteFavorite(@RequestParam("id-rider") int idRider, @RequestParam("id-horse") int idHorse);
}
