package com.integration.jira.microservicesone.service;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.jira.microservicesone.entity.FieldObject;
import com.integration.jira.microservicesone.entity.Fields;
import com.integration.jira.microservicesone.entity.Issuetype;
import com.integration.jira.microservicesone.entity.Priority;
import com.integration.jira.microservicesone.entity.Project;

@Service
public class IntegrationService {

	@Autowired
	private CommonService commonService;

	public HashMap<String, String> getIssueDetails(String issue) { // customfield_10058 customfield_10059

		HashMap<String, String> objectMapper = new HashMap<String, String>();
		String response = null;
		try {
			response = commonService.doGet(issue);

			if (!response.isEmpty()) {

				JSONObject responseObject = new JSONObject(response);
				JSONObject fields = responseObject.getJSONObject("fields");

				objectMapper.put("description", fields.getString("description") ); //

				objectMapper.put("summary", fields.getString("customfield_10059") + " - " + 
				fields.getString("customfield_10058") + " - " + fields.getString("description"));

				objectMapper.put("TASK", fields.getString("customfield_10058"));
				objectMapper.put("RITM", fields.getString("customfield_10059"));

				System.out.println(objectMapper.toString());

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return objectMapper;
	}
	
	public String createIssue(HashMap<String, String> objectMapper) throws JsonProcessingException {
		
		ObjectMapper maper = new ObjectMapper();
		
		Project project = new Project("IM");
		Issuetype issuetype = new Issuetype("Story");
		Priority priority = new Priority("Medium");
		String description = objectMapper.get("description");
		String summary = objectMapper.get("summary");
		
		Fields fields = new Fields(project,issuetype,priority,summary,description);
		
		FieldObject fieldObject = new FieldObject(fields);
		String jsonBody = maper.writeValueAsString(fieldObject);
		
		System.out.println(jsonBody);
		String resp = commonService.doPost(jsonBody);
		
		
		return resp;
		
	}

}
