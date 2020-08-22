package com.linkedinlearning.reactivespring.repositories;

import com.linkedinlearning.reactivespring.model.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository  extends ReactiveCrudRepository<Reservation, Integer> {
}
