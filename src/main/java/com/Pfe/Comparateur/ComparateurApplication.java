package com.Pfe.Comparateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class ComparateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComparateurApplication.class, args);
//		SpringApplication springApplication=new SpringApplication(ComparateurApplication.class);
//		System.out.println("Spring Core Version:- " + SpringVersion.getVersion());
//		springApplication.run(args);

	}

}
