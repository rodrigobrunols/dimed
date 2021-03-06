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

public class ItinerariosControllerTest extends DimedApplicationTest {

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
	public void listItinerariosTest() throws Exception {

		mockServer.expect(ExpectedCount.once(), requestTo(new URI("https://localhost:\"+port+\"/v1/itinerarios")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.OK).body("[\n" + "  {\n" + "    \"id\": 1,\n"
						+ "    \"nome\": \"LCA1-1\",\n" + "    \"coordenadas\": [\n" + "      {\n"
						+ "        \"lat\": -29.999734713571,\n" + "        \"lng\": -51.200366201289\n" + "      },\n"
						+ "      {\n" + "        \"lat\": -29.999846713571,\n" + "        \"lng\": -51.199827201289\n"
						+ "      },\n" + "      {\n" + "        \"lat\": -29.998822713571,\n"
						+ "        \"lng\": -51.198171201289\n" + "      },\n" + "      {\n"
						+ "        \"lat\": -29.998050713571,\n" + "        \"lng\": -51.197874201289\n" + "      }\n"
						+ "    ]\n" + "  }\n" + "]").contentType(MediaType.APPLICATION_JSON));

		getMockMvc().perform(get("/v1/itinerarios").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json("[\n" + 
								"  {\n" + 
								"    \"id\": 1,\n" + 
								"    \"nome\": \"LCA1-1\",\n" + 
								"    \"coordenadas\": [\n" + 
								"      {\n" + 
								"        \"lat\": -29.999734713571,\n" + 
								"        \"lng\": -51.200366201289\n" + 
								"      },\n" + 
								"      {\n" + 
								"        \"lat\": -29.999846713571,\n" + 
								"        \"lng\": -51.199827201289\n" + 
								"      },\n" + 
								"      {\n" + 
								"        \"lat\": -29.998822713571,\n" + 
								"        \"lng\": -51.198171201289\n" + 
								"      },\n" + 
								"      {\n" + 
								"        \"lat\": -29.998050713571,\n" + 
								"        \"lng\": -51.197874201289\n" + 
								"      }\n" + 
								"    ]\n" + 
								"  }\n" + 
								"]"));
	}

	@Test
	public void itinerarioNotFoundTest() throws Exception {

		mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8080/v1/itinerario/999")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON));

		getMockMvc().perform(get("/v1/itinerario/9999").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.status", is(404)))
				.andExpect(jsonPath("$.error", is("Not Found")));

	}

}