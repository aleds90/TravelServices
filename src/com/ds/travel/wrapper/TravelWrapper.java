package com.ds.travel.wrapper;

import java.io.Serializable;
import java.util.Date;

public class TravelWrapper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;
	private Integer duration;
	private Integer maxNumberAttendees;
	private Date createdAt;
	private Date updatedAt;
	private int userID;
	private String categorySlug;
	private String targetBudget;
	
	public TravelWrapper(){}
	
	public TravelWrapper(String title, String description, Integer duration, Integer maxNumberAttendees, Date createdAt,
			Date updatedAt, int userID, String categorySlug, String targetBudget) {
		super();
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.maxNumberAttendees = maxNumberAttendees;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userID = userID;
		this.categorySlug = categorySlug;
		this.targetBudget = targetBudget;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getMaxNumberAttendees() {
		return maxNumberAttendees;
	}
	public void setMaxNumberAttendees(Integer maxNumberAttendees) {
		this.maxNumberAttendees = maxNumberAttendees;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getCategorySlug() {
		return categorySlug;
	}
	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}
	public String getTargetBudget() {
		return targetBudget;
	}
	public void setTargetBudget(String targetBudget) {
		this.targetBudget = targetBudget;
	}
}
