package com.VehicleBreakdown.Assistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(classes=OnRoadVehicleBreakdownAssistanceApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
		
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:"+port+"/admin";
	}
	
	@Test
	public void testRegisterAdmin() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("username", "admin2");
        req_payload.put("password", "root1234");

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/register", request, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testLoginAdmin() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("username", "admin2");
        req_payload.put("password", "root1234");

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/login", request, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testLogoutAdmin() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("username", "admin");
        req_payload.put("password", "root");

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/logout", request, String.class);
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetAllUsers() {
		String username = "admin2";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/login/showusers/"+username, HttpMethod.GET, entity, String.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
	}
	
	@Test
	public void testGetAllMechanics() {
		String username = "admin2";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/login/showmechanics/"+username, HttpMethod.GET, entity, String.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
	}
	
	@Test
	public void testViewFeedback() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> getResponse = restTemplate.exchange(getRootUrl()+"/viewFeedback", HttpMethod.GET, entity, String.class);
		System.out.println(getResponse);
		assertEquals(200, getResponse.getStatusCodeValue());
	}
	
	@Test
	public void testAllowOrBlock() {
		long id = 59;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("username", "admin2");
        req_payload.put("password", "root1234");

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(getRootUrl()+"/alloworblockmechanic/"+id, request, String.class);
        assertNotNull(response.getBody());
	}
}
