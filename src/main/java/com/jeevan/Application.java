package com.jeevan;

import com.jeevan.factories.DaoFactory;
import com.jeevan.factories.DbFactory;
import com.jeevan.factories.ServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
		ServiceFactory.init();
		DaoFactory.init();

		DbFactory.initialize(Application.class, "hikari.properties");

        SpringApplication.run(Application.class, args);
    }
}