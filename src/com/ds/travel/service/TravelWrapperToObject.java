package com.ds.travel.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ds.travel.dao.*;
import com.ds.travel.model.*;
import com.ds.travel.wrapper.TravelWrapper;

@Service
public class TravelWrapperToObject {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired 
	private TargetDAO targetDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public Travel toTravel(TravelWrapper travelWrapper){
		Travel travel = new Travel();
		Category category = new Category();
		Target target = new Target();
		User user = new User();
		
		category = categoryDAO.getBySlug(travelWrapper.getCategorySlug());
		target = targetDAO.getByName(travelWrapper.getTargetBudget());
		user = userDAO.getById(travelWrapper.getUserID());
		
		travel.setCategory(category);
		travel.setTarget(target);
		travel.setUser(user);
		travel.setTitle(travelWrapper.getTitle());
		travel.setDescription(travelWrapper.getDescription());
		travel.setDuration(travelWrapper.getDuration());
		travel.setMaxNumberAttendees(travelWrapper.getDuration());
		
		return travel;
	}
	
}
