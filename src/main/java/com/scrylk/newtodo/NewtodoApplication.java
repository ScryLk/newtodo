package com.scrylk.newtodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableJpaAuditing
public class NewtodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewtodoApplication.class, args);
	}

}
