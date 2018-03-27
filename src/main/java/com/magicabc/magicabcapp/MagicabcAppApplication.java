package com.magicabc.magicabcapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(value = {"com.magicabc.magicabcapp.dao"})
@SpringBootApplication
public class MagicabcAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagicabcAppApplication.class, args);
	}
}
