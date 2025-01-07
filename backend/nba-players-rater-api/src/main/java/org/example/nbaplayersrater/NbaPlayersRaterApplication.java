package org.example.nbaplayersrater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.SecureRandom;

@SpringBootApplication
public class NbaPlayersRaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbaPlayersRaterApplication.class, args);


    }
    @Bean
    SecureRandom createRandomInstance() {
        return new SecureRandom();
    }

}
