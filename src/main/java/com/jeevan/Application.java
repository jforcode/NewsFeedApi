package com.jeevan;

import com.jeevan.factories.DaoFactory;
import com.jeevan.factories.ServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
		ServiceFactory.init();

		DbFactory.initialize(Application.class, "hikari.properties");

        SpringApplication.run(Application.class, args);
    }
}