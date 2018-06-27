package com.izuche.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class SpringbootModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootModelApplication.class, args);
	}
}
