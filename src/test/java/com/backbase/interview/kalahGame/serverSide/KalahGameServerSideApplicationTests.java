package com.backbase.interview.kalahGame.serverSide;

import com.backbase.interview.kalahgame.serverside.service.KalahResourse;
import com.backbase.interview.kalahgame.serverside.service.api.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KalahGameServerSideApplicationTests {

	@Autowired
	private KalahResourse kalahResourse;
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void contextLoads() {
	
		assertThat(kalahResourse).isNotNull();
		//assertThat(this.restTemplate.postForLocation("http://localhost:" + port + "/games","",String.class)).isNotNull();
	}
	
	
	
	@Test
	void testCreateGame() throws Exception {
		
		final String baseUrl = "http://localhost:"+port+"/games";
		URI uri = new URI(baseUrl);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> request = new HttpEntity<>("", headers);
		
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		
		//Verify request succeed
		assertThat ( result.getStatusCodeValue()).isEqualTo(201);
		assertThat(result.getBody()).contains("id");
		assertThat(result.getBody()).contains("uri");
		
	}
	
	@Test
	void testMakeMove() throws Exception {
		
		final String baseUrl = "http://localhost:"+port+"/games";
		URI uri = new URI(baseUrl);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> createGameRequest = new HttpEntity<>("", headers);
		
		ResponseEntity<String> gameCreationresult = this.restTemplate.postForEntity(uri, createGameRequest, String.class);
		
		//Verify createGameRequest succeed
		assertThat ( gameCreationresult.getStatusCodeValue()).isEqualTo(201);
		Response createGameresponse = objectMapper.readValue(gameCreationresult.getBody().toString(), Response.class);
		assertThat(createGameresponse.getId()).isNotNull();
		assertThat(createGameresponse.getId()).isNotEmpty();
		assertThat(createGameresponse.getUri()).isNotEmpty();
		assertThat(createGameresponse.getUri()).isNotNull();
		
		String moveUri = baseUrl+"/"+createGameresponse.getId()+"/pits/4";
		HttpEntity<Object> makeMoveRequest = new HttpEntity<>("", headers);
		
		ResponseEntity<String> makeMoveresult = this.restTemplate.exchange(moveUri, HttpMethod.PUT, makeMoveRequest, String.class);
		assertThat ( makeMoveresult.getStatusCodeValue()).isEqualTo(200);
		Response makeMoveResponse = objectMapper.readValue(makeMoveresult.getBody().toString(), Response.class);
		assertThat(makeMoveResponse.getId()).isNotNull();
		assertThat(makeMoveResponse.getId()).isNotEmpty();
		assertThat(makeMoveResponse.getUri()).isNotEmpty();
		assertThat(makeMoveResponse.getUri()).isNotNull();
		assertThat(makeMoveResponse.getStatus()).isNotEmpty();
		assertThat(makeMoveResponse.getStatus()).isNotNull();
		
	}

}
