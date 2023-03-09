package com.pawin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class PawinApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(PawinApplication.class, args);
	}

}
