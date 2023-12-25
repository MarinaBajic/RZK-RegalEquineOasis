package com.reo.appservice.feign;

import com.reo.appservice.dto.SessionRequest;
import com.reo.appservice.dto.SessionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "session-service", url = "localhost:8082")
public interface SessionProxy {

    @PostMapping("/api/session")
    void addNewSession(@RequestBody SessionRequest sessionRequest);

    @GetMapping("/api/session/rider")
    List<SessionResponse> getAllSessionsForRider(@RequestParam int idRider);
}
