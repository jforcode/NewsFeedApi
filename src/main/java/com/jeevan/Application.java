package com.jeevan;

import com.jeevan.factories.DaoFactory;
import com.jeevan.factories.DbFactory;
import com.jeevan.factories.ServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
		ServiceFactory.init();
		DaoFactory.init();
		DbFactory.initialize("src/main/resources/hikari.properties");
        SpringApplication.run(Application.class, args);
    }
}