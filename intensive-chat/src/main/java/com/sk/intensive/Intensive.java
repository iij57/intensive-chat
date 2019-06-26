package com.sk.intensive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Intensive {

    public static void main(String[] args) {
        log.info("start intensive-chat");
        SpringApplication.run(Intensive.class, args);
    }
}