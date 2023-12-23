package com.reo.horseservice.controller;

import com.reo.horseservice.dto.HorseRequest;
import com.reo.horseservice.dto.HorseResponse;
import com.reo.horseservice.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horse")
public class HorseController {

    @Autowired
    private HorseService horseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HorseResponse> getAllHorses() {
        return horseService.getAllHorses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewHorse(@RequestBody HorseRequest horseRequest) {
        horseService.addNewHorse(horseRequest);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HorseResponse> findAllHorsesByBreed(@RequestParam int idBreed) {
        return horseService.findAllHorsesByBreed(idBreed);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHorse(@RequestParam int idHorse) {
        horseService.deleteHorse(idHorse);
    }
}
