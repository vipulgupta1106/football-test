package com.sapient.footballmatchtest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest(properties = {"server.port=0", "management.server.port=0"})
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext
class FootballMatchTestApplicationTests {

	@Test
	void contextLoads() {
	}

}