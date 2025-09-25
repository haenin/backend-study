package com.haenin.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 붙이지 않아도 돌아는 감
public class Chap0102EurekaClientLectureSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap0102EurekaClientLectureSourceApplication.class, args);
    }

}

