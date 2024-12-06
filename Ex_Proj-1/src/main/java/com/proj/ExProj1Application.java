package com.proj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.proj.demo.mapper")
public class ExProj1Application {

	public static void main(String[] args) {
		SpringApplication.run(ExProj1Application.class, args);
	}

}
