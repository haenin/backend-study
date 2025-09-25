package com.haenin.firstservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Chap0201FirstServiceLectureSoruceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap0201FirstServiceLectureSoruceApplication.class, args);
    }

}
