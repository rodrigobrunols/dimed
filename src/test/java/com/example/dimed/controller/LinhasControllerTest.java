package com.example.dimed.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import com.example.dimed.DimedApplicationTest;

public class LinhasControllerTest extends DimedApplicationTest {

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@LocalServerPort
	private int port;

	@Before
	public void setUp() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void listLinhaTest() throws Exception {

		mockServer.expect(ExpectedCount.once(), requestTo(new URI("https://localhost:"+port+"/v1/linhas")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.OK)
						.body("[\n" + "  {\n" + "    \"id\": 5475,\n" + "    \"codigo\": \"T11-1\",\n"
								+ "    \"nome\": \"3ª PERIMETRAL\"\n" + "  },\n" + "  {\n" + "    \"id\": 5517,\n"
								+ "    \"codigo\": \"250-1\",\n" + "    \"nome\": \"1 DE MAIO\"\n" + "  },\n" + "  {\n"
								+ "    \"id\": 5518,\n" + "    \"codigo\": \"250-2\",\n"
								+ "    \"nome\": \"1 DE MAIO\"\n" + "  }\n" + "]")
						.contentType(MediaType.APPLICATION_JSON));

		getMockMvc().perform(get("/v1/linhas").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json("[\n" + "  {\n" + "    \"id\": 5475,\n" + "    \"codigo\": \"T11-1\",\n"
								+ "    \"nome\": \"3ª PERIMETRAL\"\n" + "  },\n" + "  {\n" + "    \"id\": 5517,\n"
								+ "    \"codigo\": \"250-1\",\n" + "    \"nome\": \"1 DE MAIO\"\n" + "  },\n" + "  {\n"
								+ "    \"id\": 5518,\n" + "    \"codigo\": \"250-2\",\n"
								+ "    \"nome\": \"1 DE MAIO\"\n" + "  }\n" + "]"));
	}


	@Test
	public void linhaNotFoundTest() throws Exception {

		mockServer.expect(ExpectedCount.once(), requestTo(new URI(
				"http://localhost:8080/v1/linhas/1")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON));

		getMockMvc()
				.perform(get("/v1/linhas/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.status", is(404)))
				.andExpect(jsonPath("$.error", is("Not Found")));

	}

}