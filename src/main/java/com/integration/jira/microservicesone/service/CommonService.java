package com.integration.jira.microservicesone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommonService {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Value("${jiraConfig.url}")
	private String jiraBaseURL;
	
	@Value("${jiraConfig.api}")
	private String getIssueAPI;
	
	
	
	public String doGet(String issue) {		
		

		// Jira Get Issue API = http://20.119.62.192:8080/rest/api/2/issue/IM-3
		String URL  = "https://bluepearl10.atlassian.net/rest/api/2/issue/" + issue;
		System.out.println(URL);
		
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-type", "application/json");
			headers.set("Accept", "application/json");
			headers.set("Authorization", "Basic bmF2ZWVuLnRwOTk3QGdtYWlsLmNvbTpLSTJsRmZzV0tnZVBGM3RWNnpFZUJDQkE=");
			
			
			
			
			
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
			
			if(response.getStatusCode() != null) {
				return response.getBody();
			}
			

			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
				
		return URL;
	}
	
	public String doPost(String requestBody) {
		
		// Jira Get Issue API = http://20.102.71.88:8080/rest/api/2/issue/IM-1
		String URL  = jiraBaseURL + getIssueAPI ;
		System.out.println(URL);
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-type", "application/json");
			//headers.set("Accept", "application/json");
			headers.set("Authorization", "Basic bmt0MDU6cGFzc3dvcmRAMTIz");
			
			HttpEntity<String> entity = new HttpEntity<String>(requestBody,headers);
			
			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
			System.out.println(" Res Body" + response.getBody() );
			
			//if(response.hasBody()) {
				return response.getBody();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage() +" here is the erroe ");
		}
		
		
		
		
		return "Error  @ post Request";
	}

}
