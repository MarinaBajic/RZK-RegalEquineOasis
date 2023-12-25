package com.reo.appservice.service;

import com.reo.appservice.dto.HorseResponse;
import com.reo.appservice.feign.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private FeignProxy feignProxy;

    public List<HorseResponse> showHorses() {
        return feignProxy.getAllHorses();
    }

    public List<HorseResponse> searchHorses(int idBreed) {
        return feignProxy.findAllHorsesByBreed(idBreed);
    }
}
