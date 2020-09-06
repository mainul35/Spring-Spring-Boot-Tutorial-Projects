package com.mainul35.service;

import com.mainul35.model.Reservation;
import com.mainul35.repositories.ReservationRepository;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservationService implements IReservationService {

    private final DatabaseClient databaseClient;
    private final ReservationRepository reservationRepository;

    public ReservationService(DatabaseClient databaseClient, ReservationRepository reservationRepository) {
        this.databaseClient = databaseClient;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Mono<Reservation> getReservation(Long id) {
        return this.reservationRepository.getReservationByRoomId(id);
    }

    @Override
    public Mono<Reservation> createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Mono<Void> deleteReservation(Reservation reservation) {
        return reservationRepository.delete(reservation);
    }

    @Override
    public Flux<Reservation> listAllReservation() {
        return reservationRepository.findAll();
    }
}
