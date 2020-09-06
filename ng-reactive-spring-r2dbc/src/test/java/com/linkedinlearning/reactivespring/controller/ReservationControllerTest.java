package com.linkedinlearning.reactivespring.controller;

import com.mainul35.model.Reservation;
import com.mainul35.controller.ReservationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    private WebTestClient webTestClient;
    private Reservation reservation;
    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
                .bindToApplicationContext(applicationContext)
                .build();
        reservation = new Reservation(
                1l,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                BigDecimal.valueOf(1000),
                123);
    }

    @Test
    void createReservation() {
        webTestClient.post()
                .uri(ReservationController.BASE_PATH)
                .body(Mono.just(reservation), Reservation.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(Reservation.class);
    }

    @Test
    void listAllReservation() {
        webTestClient.get()
                .uri(ReservationController.BASE_PATH + "/all")
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reservation.class);
    }
}
