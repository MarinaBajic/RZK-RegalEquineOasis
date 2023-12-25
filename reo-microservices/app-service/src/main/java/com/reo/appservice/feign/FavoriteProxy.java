package com.reo.appservice.feign;

import com.reo.appservice.dto.FavoriteHorse;
import com.reo.appservice.dto.FavoriteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="favorite-service", url="localhost:8083")
public interface FavoriteProxy {

    @GetMapping("/api/favorite/rider")
    List<FavoriteHorse> getAllFavoriteHorsesForRider(@RequestParam int idRider);

    @PostMapping("/api/favorite")
    void addNewFavorite(@RequestBody FavoriteRequest favoriteRequest);

    @DeleteMapping("/api/favorite/delete")
    void deleteFavorite(@RequestParam int idRider, @RequestParam int idHorse);
}
