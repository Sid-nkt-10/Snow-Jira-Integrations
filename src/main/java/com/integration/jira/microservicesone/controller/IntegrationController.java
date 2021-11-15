package com.integration.jira.microservicesone.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.integration.jira.microservicesone.service.IntegrationService;

@RestController
@RequestMapping("/rest/api/v1/jira-integrations")
public class IntegrationController {
	
	@Autowired
	private IntegrationService integrationService;
	
	@Value("${jiraConfig.url}")
	private String jiraURL;
	
	
	@GetMapping("/Status")
	public String getPath() {
		
		return jiraURL + " is up and running......";
	}
	
	@PostMapping("/create/{issue}")
	public String jiraIntegration(@PathVariable String issue) throws JsonProcessingException {
		
		HashMap<String, String> issueFields =  integrationService.getIssueDetails(issue);
		
	
			return integrationService.createIssue(issueFields);
		
		
		//return "Error here";
		
	}
	
	

}
