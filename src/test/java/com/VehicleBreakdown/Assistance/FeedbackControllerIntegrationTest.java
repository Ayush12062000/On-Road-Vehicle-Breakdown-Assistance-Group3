package com.VehicleBreakdown.Assistance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes=OnRoadVehicleBreakdownAssistanceApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeedbackControllerIntegrationTest {
	
	@Autowired	
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:"+port;
	}
	
	@Test
	public void testGetAllFeedback()
	{
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+"/feedback/all",HttpMethod.GET,entity,String.class);
		assertNotNull(response.getBody());
	}
}
