package com.reo.riderservice.service;

import com.reo.riderservice.dto.RiderRequest;
import com.reo.riderservice.dto.RiderResponse;
import com.reo.riderservice.model.Coach;
import com.reo.riderservice.model.Rider;
import com.reo.riderservice.repository.CoachRepository;
import com.reo.riderservice.repository.RiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private CoachRepository coachRepository;

    public List<RiderResponse> getAllRiders() {
        List<Rider> riders = riderRepository.findAll();
        return riders.stream().map(this::mapToRiderResponse).toList();
    }

    private RiderResponse mapToRiderResponse(Rider rider) {
        return RiderResponse.builder()
                .idRider(rider.getIdRider())
                .address(rider.getAddress())
                .dateOfBirth(rider.getDateOfBirth())
                .dateOfEnrollment(rider.getDateOfEnrollment())
                .name(rider.getName())
                .surname(rider.getSurname())
                .favorites(rider.getFavorites())
                .build();
    }

    public void addNewRider(RiderRequest riderRequest) {
        Optional<Coach> defaultCoachOptional = coachRepository.findByNameAndSurname("None", "None");
        Coach defaultCoach;
        if (defaultCoachOptional.isEmpty()) {
            defaultCoach = Coach.builder().name("None").surname("None").build();
            coachRepository.save(defaultCoach);
        }
        else
            defaultCoach = defaultCoachOptional.get();

        Rider rider = Rider.builder()
                .address(riderRequest.getAddress())
                .dateOfBirth(riderRequest.getDateOfBirth())
                .dateOfEnrollment(riderRequest.getDateOfEnrollment())
                .name(riderRequest.getName())
                .surname(riderRequest.getSurname())
                .coach(coachRepository.findById(riderRequest.getIdCoach()).orElse(defaultCoach))
                .build();

        riderRepository.save(rider);
        log.info("Rider with id: {} is saved", rider.getIdRider());
    }
}
