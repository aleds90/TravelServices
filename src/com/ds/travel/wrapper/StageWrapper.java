package com.ds.travel.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.ds.travel.model.City;
import com.ds.travel.model.Travel;

public class StageWrapper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int cityID;
	private int travelID;
	private Date startDate;
	private Date endDate;
	
	public StageWrapper(){}

	public StageWrapper(Integer id, int cityID, int travelID, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.cityID = cityID;
		this.travelID = travelID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCityID() {
		return cityID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	public int getTravelID() {
		return travelID;
	}

	public void setTravelID(int travelID) {
		this.travelID = travelID;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
