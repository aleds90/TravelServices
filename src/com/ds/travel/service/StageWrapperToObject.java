package com.ds.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.travel.dao.*;
import com.ds.travel.dao.TargetDAO;
import com.ds.travel.dao.TravelDAO;
import com.ds.travel.dao.UserDAO;
import com.ds.travel.model.Category;
import com.ds.travel.model.Target;
import com.ds.travel.model.Travel;
import com.ds.travel.model.*;
import com.ds.travel.wrapper.StageWrapper;
import com.ds.travel.wrapper.TravelWrapper;

@Service
public class StageWrapperToObject {
	
	@Autowired
	private TravelDAO travelDAO;
	
	@Autowired
	private CityDAO cityDAO;

	public Stage toStage(StageWrapper stageWrapper){
		Stage stage = new Stage();
		Travel travel = new Travel();
		City city = new City();
		
		travel = travelDAO.getById(stageWrapper.getTravelID());
		city = cityDAO.getById(stageWrapper.getCityID());
		
		stage.setCity(city);
		stage.setTravel(travel);
		stage.setStartDate(stageWrapper.getStartDate());
		stage.setEndDate(stageWrapper.getEndDate());
		
		return stage;
	}
}
