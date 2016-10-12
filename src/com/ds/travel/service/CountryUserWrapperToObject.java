package com.ds.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.travel.dao.*;
import com.ds.travel.model.Category;
import com.ds.travel.model.Country;
import com.ds.travel.model.CountryUser;
import com.ds.travel.model.Target;
import com.ds.travel.model.Travel;
import com.ds.travel.model.User;
import com.ds.travel.wrapper.CountryUserWrapper;
import com.ds.travel.wrapper.TravelWrapper;

@Service
public class CountryUserWrapperToObject {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CountryDAO countryDAO;
	
	public CountryUser toCountryUser(CountryUserWrapper countryUserWrapper){
		User user = new User();
		Country country = new Country();
		CountryUser countryUser = new CountryUser();
		country = countryDAO.getByCode(countryUserWrapper.getCountryCode());
		user = userDAO.getById(countryUserWrapper.getUserID());
		
		countryUser.setCountry(country);
		countryUser.setUser(user);
		
		return countryUser;
	}
	
}
