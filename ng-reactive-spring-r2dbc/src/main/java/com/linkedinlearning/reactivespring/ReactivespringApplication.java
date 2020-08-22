package com.linkedinlearning.reactivespring;

import com.linkedinlearning.reactivespring.model.Reservation;
import com.linkedinlearning.reactivespring.repositories.ReservationRepository;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
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
    private PostgresqlConnectionFactory connectionFactory;
    @Autowired
    private ReservationRepository reservationRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactivespringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);
        Arrays.asList("DROP TABLE IF EXISTS reservation;",
                "CREATE TABLE reservation (id serial primary key, name varchar, check_in timestamp, check_out timestamp, price bigint);")
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
        reservationRepository.save(reservation).log().subscribe();
        reservationRepository.findAll().log().subscribe(System.out::println);
    }
}
