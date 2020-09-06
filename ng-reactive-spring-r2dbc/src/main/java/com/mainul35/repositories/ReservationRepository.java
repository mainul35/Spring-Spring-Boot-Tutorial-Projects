package com.mainul35.repositories;

import com.mainul35.model.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReservationRepository  extends ReactiveCrudRepository<Reservation, Long> {
    Mono<Reservation> getReservationByRoomId(Long roomId);
}
