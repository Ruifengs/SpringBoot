package com.example.springbootredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.springbootredis.dao")
@EnableCaching
public class SpringbootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisApplication.class, args);
	}

}
