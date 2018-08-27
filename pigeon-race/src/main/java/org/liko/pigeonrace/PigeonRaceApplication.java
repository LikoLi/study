package org.liko.pigeonrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PigeonRaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PigeonRaceApplication.class, args);
	}
}
