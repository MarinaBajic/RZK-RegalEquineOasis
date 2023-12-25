package com.reo.appservice.controller;

import com.reo.appservice.dto.HorseResponse;
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
}
