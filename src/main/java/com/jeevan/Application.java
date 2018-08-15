package com.jeevan;

import com.jeevan.factories.DbFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
		DbFactory.initialize("src/main/resources/hikari.properties");
        SpringApplication.run(Application.class, args);
    }
}