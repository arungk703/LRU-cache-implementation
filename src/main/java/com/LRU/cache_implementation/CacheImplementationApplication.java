package com.LRU.cache_implementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "APIs,cache")
public class CacheImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheImplementationApplication.class, args);
	}

}
