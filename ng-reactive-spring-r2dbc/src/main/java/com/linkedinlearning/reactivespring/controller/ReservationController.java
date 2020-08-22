package com.linkedinlearning.reactivespring.controller;

import com.linkedinlearning.reactivespring.model.Reservation;
import com.linkedinlearning.reactivespring.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping(ReservationController.BASE_PATH)
@CrossOrigin
public class ReservationController {
    public static final String BASE_PATH = "/room/reservation";

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Reservation> getReservationById() {
        // fetch and return reservation by room ID
        return reservationRepository.findAll();
//        return Mono.just(reservation);
    }
}
