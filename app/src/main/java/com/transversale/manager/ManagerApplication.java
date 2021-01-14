package com.transversale.manager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication
@EnableJms
@ComponentScan(basePackages = {"config", "services", "jms", "manager"})
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner JmsDemo() {
		return (args) -> {
			while (true) {
				try {
					Thread.sleep(3*1000);
					System.out.println("running");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

}
