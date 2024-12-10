package com.atapia.apirfds2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Apirfds2Application {

	public static void main(String[] args) {
		SpringApplication.run(Apirfds2Application.class, args);
	}

}
