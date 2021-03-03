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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import com.example.dimed.DimedApplicationTests;

public class OnibusControllerTest extends DimedApplicationTests {

  @Autowired
  private RestTemplate restTemplate;

  private MockRestServiceServer mockServer;

  @Before
  public void setUp() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void getLinhasTest() throws Exception {

    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:8080/v1/onibus/linhas")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.OK)
            .body("[{     " +" {\n" + 
            		"    \"id\": \"5517\",\n" + 
            		"    \"codigo\": \"250-1\",\n" + 
            		"    \"nome\": \"1 DE MAIO\"\n" + 
            		"  },\n" + 
            		"  {\n" + 
            		"    \"id\": \"5518\",\n" + 
            		"    \"codigo\": \"250-2\",\n" + 
            		"    \"nome\": \"1 DE MAIO\"\n" + 
            		"  },\n" + 
            		"  {\n" + 
            		"    \"id\": \"5475\",\n" + 
            		"    \"codigo\": \"T11-1\",\n" + 
            		"    \"nome\": \"3ª PERIMETRAL\"\n" + 
            		"  }]")
            .contentType(MediaType.APPLICATION_JSON));

    getMockMvc().perform(get("/v1/onibus/linhas")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()) 
        .andExpect(MockMvcResultMatchers.content().json(
        		"[{\"id\":5517,\"codigo\":\"250-1\",\"nome\":\"1 DE MAIO\"},"
        		+ "{\"id\":5518,\"codigo\":\"250-2\",\"nome\":\"1 DE MAIO\"}]"
        		+ "{\"id\":5475,\"codigo\":\"T11-1\",\"nome\":\"3ª PERIMETRAL\"}]"
        		));
  }

  
  @Test
  public void getItinerariosTest() throws Exception {

    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://localhost:8080/v1/onibus/itinerarios")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.OK)
            .body("[{     " +" {\n" + 
            		"    \"0\": {\n" + 
            		"    \"lat\": \"-30.03075963324200000\",\n" + 
            		"    \"lng\": \"-51.22776531648400000\"\n" + 
            		"  },"
            		+ "\"1\": {\n" + 
            		"    \"lat\": \"-30.03078463324200000\",\n" + 
            		"    \"lng\": \"-51.22762931648400000\"\n" + 
            		"  }," +
            		"\"idlinha\": \"5526\",\n" + 
            		"  \"nome\": \"EMBRATEL (CASCATINHA)\",\n" + 
            		"  \"codigo\": \"254-2\"" +
            		"  }]")
            .contentType(MediaType.APPLICATION_JSON));

    getMockMvc().perform(get("/v1/onibus/linhas")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()) 
        .andExpect(jsonPath("$.idlinha", is("5526")))
        .andExpect(jsonPath("$.nome", is("EMBRATEL (CASCATINHA)")))
        .andExpect(jsonPath("$.codigo", is("254-2")));
  }


}