package com.gui.webappgui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.pmservice.basePackage.repos")
@EntityScan("com.pmservice.basePackage.models")
public class WebAppGuiApplication{

	public static void main(String[] args) {
		SpringApplication.run(WebAppGuiApplication.class, args);
	}
}
