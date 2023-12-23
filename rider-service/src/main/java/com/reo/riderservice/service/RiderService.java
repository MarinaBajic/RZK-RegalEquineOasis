package com.reo.riderservice.service;

import com.reo.riderservice.dto.RiderResponse;
import com.reo.riderservice.model.Rider;
import com.reo.riderservice.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

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
}
