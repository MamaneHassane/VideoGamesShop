package com.videogamesshop.vgs_package;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan(value = "com/videogamesshop/vgs_package/model")
@SpringBootApplication
public class VideoGamesShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoGamesShopApplication.class, args);
    }

    @Bean
    CommandLineRunner start(){
        return args -> {

        };
    }

}
