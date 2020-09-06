package com.mainul35.service;

import com.mainul35.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IReservationService {

    Mono<Reservation> getReservation(Long id);

    Mono<Reservation> createReservation(Reservation reservation);

    Mono<Void> deleteReservation(Reservation reservation);

    Flux<Reservation> listAllReservation();
}
