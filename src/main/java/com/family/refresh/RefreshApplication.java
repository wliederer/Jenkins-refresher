package com.family.refresh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RefreshApplication implements CommandLineRunner {
	
	public static final Logger logger = LoggerFactory.getLogger(RefreshApplication.class);

	public static void main(String[] args) {
		logger.info("its a continous integration job");
		SpringApplication.run(RefreshApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("its my second logger");
		
	}

}
