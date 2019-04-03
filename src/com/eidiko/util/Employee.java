package com.eidiko.util;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("employeeId")
	private int employeeId;
	
	@JsonProperty("categoryId")
	private long categoryId;
	
	@JsonProperty("subCategoryId")
	private long subCategoryId;
	
	@JsonProperty("skillId")
	private long skillId;
	
	@JsonProperty("startDate")
	private Date  startDate;
	
	@JsonProperty("endDate")
	private Date endDate ;
	
	@JsonProperty("remarks")
	private String remarks ;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public long getSkillId() {
		return skillId;
	}
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeId=" + employeeId + ", categoryId=" + categoryId + ", subCategoryId="
				+ subCategoryId + ", skillId=" + skillId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", remarks=" + remarks + "]";
	}
	
}
