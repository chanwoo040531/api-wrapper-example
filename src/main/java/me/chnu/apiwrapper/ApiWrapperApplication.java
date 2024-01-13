package me.chnu.apiwrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiWrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiWrapperApplication.class, args);
    }
}