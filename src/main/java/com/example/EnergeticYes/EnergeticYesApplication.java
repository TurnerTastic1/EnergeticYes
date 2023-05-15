package com.example.EnergeticYes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class EnergeticYesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergeticYesApplication.class, args);
	}

	@GetMapping("/")
	public List<String> hello() {
		return List.of("Hello World! API is currently v1.");
	}
}
