package com.mainul35;

import com.mainul35.model.Reservation;
import com.mainul35.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.core.DatabaseClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class ReactivespringApplication implements CommandLineRunner {

    @Autowired
    private DatabaseClient databaseClient;
    @Autowired
    private ReservationRepository reservationRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactivespringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.asList("DROP TABLE IF EXISTS reservation;",
                "CREATE TABLE reservation (room_id serial primary key, name varchar, check_in timestamp, check_out timestamp, price bigint, room_number integer);")
                .forEach(s ->
                        databaseClient.execute(s)
                                .fetch()
                                .rowsUpdated()
                                .block()
                );
        Reservation reservation = new Reservation();
        reservation.setCheckIn(LocalDate.now());
        reservation.setCheckOut(LocalDate.now().plusDays(2));
        reservation.setPrice(new BigDecimal(1000));
        reservation.setRoomNumber(114);
        reservationRepository.save(reservation).log().subscribe();
        reservationRepository.findAll().log().subscribe(System.out::println);
    }
}
