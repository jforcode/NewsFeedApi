package com.jeevan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
		DbFactory.initialize(Application.class, "hikari.properties");
        SpringApplication.run(Application.class, args);
    }
}