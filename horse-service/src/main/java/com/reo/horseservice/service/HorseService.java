package com.reo.horseservice.service;

import com.reo.horseservice.model.Horse;
import com.reo.horseservice.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseService {

    @Autowired
    private HorseRepository horseRepository;

    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }
}
