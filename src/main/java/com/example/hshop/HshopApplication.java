package com.example.hshop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HshopApplication.class, args);
    }

    @Bean
    Hibernate5Module hibernate5Module(){
        return new Hibernate5Module();
    }

}
