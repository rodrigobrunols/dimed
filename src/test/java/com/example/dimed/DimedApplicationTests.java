package com.example.dimed;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class DimedApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	@Test
	void contextLoads() {
	}

}
