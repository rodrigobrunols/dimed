package com.example.dimed.service.rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.example.dimed.DimedApplicationTests;


public class DataPoaRestServiceTest extends DimedApplicationTests {

  @Autowired
  private DataPoaRestService dataPoaRestService;

  @Autowired
  private RestTemplate restTemplate;

  private MockRestServiceServer mockServer;

  @Before
  public void setUp() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void getLinhas() throws Exception {

    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON).body("[\n" +
        "{\n" + 
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
                "  }]"));

    ResponseEntity<String> linhas = dataPoaRestService.getLinhas("%", "o");

    Assert.assertTrue(linhas.hasBody());
    Assert.assertTrue(linhas.getBody().contains("5517"));
    Assert.assertTrue(linhas.getBody().contains("5518"));
    Assert.assertTrue(linhas.getBody().contains("5475"));
    
  }

 
  @Test
  public void getItinerario() throws Exception {

    mockServer.expect(ExpectedCount.once(),
        requestTo(new URI("http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5526")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON).body(
        "{\n" + 
        "  \"0\": {\n" + 
        "    \"lat\": \"-30.05096263324200000\",\n" + 
        "    \"lng\": \"-51.22976531648400000\"\n" + 
        "  },\n" + 
        "  \"1\": {\n" + 
        "    \"lat\": \"-30.05109963324200000\",\n" + 
        "    \"lng\": \"-51.22818331648400000\"\n" + 
        "  },\n" + 
        "  \"2\": {\n" + 
        "    \"lat\": \"-30.05025763324200000\",\n" + 
        "    \"lng\": \"-51.22804131648400000\"\n" + 
        "  }"));

    ResponseEntity<String> linhas = dataPoaRestService.getItinerarios(5526);
    
    Assert.assertTrue(linhas.hasBody());
    Assert.assertTrue(linhas.getBody().contains("30.05109963324200000"));
    Assert.assertTrue(linhas.getBody().contains("30.05025763324200000"));
    Assert.assertTrue(linhas.getBody().contains("51.22976531648400000"));
    
  }
}
