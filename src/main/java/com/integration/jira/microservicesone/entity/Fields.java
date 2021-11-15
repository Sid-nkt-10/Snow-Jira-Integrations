package com.integration.jira.microservicesone.entity;

public class Fields {
	
	private Project project;
	private Issuetype issuetype;
	private Priority priority;
	private String summary;
	private String description;
	
	
	
	public Fields() {
		
	}
	
	public Fields(Project project, Issuetype issuetype, Priority priority, String summary, String description) {
		
		this.project = project;
		this.issuetype = issuetype;
		this.priority = priority;
		this.summary = summary;
		this.description = description;
	}
	
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Issuetype getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(Issuetype issuetype) {
		this.issuetype = issuetype;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	

}
