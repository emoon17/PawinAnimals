package com.pawin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PawinApplicationTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Test
	void contextLoads() {
	}
	
	@Test
	void 더하기 () {
		
		logger.info("############더하기 테스트 #####");
		int a = 10;
		int b = 20;
		assertEquals(30, sum(a,b));
	}

	int sum (int x, int y) {
		return x + y;
	}
}
