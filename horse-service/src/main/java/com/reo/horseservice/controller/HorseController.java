package com.reo.horseservice.controller;

import com.reo.horseservice.model.Horse;
import com.reo.horseservice.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/horse")
public class HorseController {

    @Autowired
    private HorseService horseService;

    @GetMapping("/all")
    public List<Horse> getAllHorses() {
        return horseService.getAllHorses();
    }
}
