package com.family.refresh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RefreshApplicationTests {
	
	public static final Logger logger = LoggerFactory.getLogger(RefreshApplicationTests.class);

	@Test
	void contextLoads() {
		logger.info("test cases");
		assertEquals(true,true);
	}

}
