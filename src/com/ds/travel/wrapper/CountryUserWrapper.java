package com.ds.travel.wrapper;

public class CountryUserWrapper {
	
	private int id;
	private int userID;
	private String countryCode;
	
	public CountryUserWrapper(){}
	
	public CountryUserWrapper(int id, int userID, String countryCode) {
		super();
		this.id = id;
		this.userID = userID;
		this.countryCode = countryCode;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	
}
