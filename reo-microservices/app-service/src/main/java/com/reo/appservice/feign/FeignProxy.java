package com.reo.appservice.feign;

import com.reo.appservice.dto.HorseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name="horse-service", url="localhost:8080")
public interface FeignProxy {

    @GetMapping("/api/horse")
    List<HorseResponse> getAllHorses();

    @GetMapping("api/horse/search")
    @ResponseStatus(HttpStatus.OK)
    List<HorseResponse> findAllHorsesByBreed(@RequestParam int idBreed);
}
