package com.ds.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.travel.dao.TravelDAO;
import com.ds.travel.model.*;
import com.ds.travel.service.TravelWrapperToObject;
import com.ds.travel.wrapper.TravelWrapper;

@RestController
public class TravelController {
	
	@Autowired
	private TravelWrapperToObject travelWrapperToObject;
	
	@Autowired 
	private TravelDAO travelDAO;
	
		//------------------- Retrieve All Travel --------------------------------------------------------
		 @RequestMapping(value = "/travel/", method = RequestMethod.GET)
		    public ResponseEntity<List<Travel>> listAllTravels() {
			 	List<Travel> travels = travelDAO.getAll();
		        if(travels.isEmpty()){
		            return new ResponseEntity<List<Travel>>(HttpStatus.NO_CONTENT);
		        }
		        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
		    }
	  
	    //-------------------Retrieve Single Travel--------------------------------------------------------
	    @RequestMapping(value = "/travel/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Travel> getTravel(@PathVariable("id") int id) {
	        System.out.println("Fetching Travel with id " + id);
	        Travel travel = travelDAO.getById(id);
	        if (travel == null) {
	            System.out.println("Travel with id " + id + " not found");
	            return new ResponseEntity<Travel>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Travel>(travel, HttpStatus.OK);
	    }	 
	    
	    //-------------------Create a Travel--------------------------------------------------------
	    @RequestMapping(value = "/travel/", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<Void> createTravel(@RequestBody TravelWrapper travelWrapper, UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Travel " + travelWrapper.getTitle());
	        Travel travel = travelWrapperToObject.toTravel(travelWrapper);
	        travelDAO.add(travel);
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
	    
	    //------------------- Update a Travel --------------------------------------------------------
	    @RequestMapping(value = "/travel/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Travel> updateTravel(@PathVariable("id") int id, @RequestBody TravelWrapper travelWrapper) {
	        System.out.println("Updating Travel " + id);
	        Travel currentTravel = travelDAO.getById(id);
	        if (currentTravel==null) {
	            System.out.println("Travel with id " + id + " not found");
	            return new ResponseEntity<Travel>(HttpStatus.NOT_FOUND);
	        }
	        Travel travel = travelWrapperToObject.toTravel(travelWrapper);
	        
	        currentTravel.setCategory(travel.getCategory());
	        currentTravel.setTarget(travel.getTarget());
	        currentTravel.setUser(travel.getUser());
	        currentTravel.setTitle(travel.getTitle());
	        currentTravel.setDescription(travel.getDescription());
	        currentTravel.setDuration(travel.getDuration());
	        currentTravel.setMaxNumberAttendees(travel.getMaxNumberAttendees());
	        
	        travelDAO.update(currentTravel);
	        return new ResponseEntity<Travel>(currentTravel, HttpStatus.OK);
	    }	    
	    
		//------------------- Retrieve Travels By Properties --------------------------------------------------------
		 @RequestMapping(value = "/travel/search", method = RequestMethod.GET)
		    public ResponseEntity<List<Travel>> listTravelsByProperties(
		    		@RequestParam(value="budget", required=false) String budget,
		    		@RequestParam(value="place", required=false) String place, 
		    		@RequestParam(value="category", required=false) String category) {
			 
			 	
			 	if(budget==null || budget.equals(null)){
			 		budget = "999999";
			 	}
			 	if(place==null || place.equals(null)){
			 		place = "";
			 	}
			 	if(category==null || category.equals(null)){
			 		category = "";
			 	}
			 	
			 	List<Travel> travels = travelDAO.getTravelsIDByProperties(budget, place, category);
		        if(travels.isEmpty()){
		            return new ResponseEntity<List<Travel>>(HttpStatus.NO_CONTENT);
		        }
		        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
		    }
		 
		 //------------------- Retrieve All Travel By User ID --------------------------------------------------------
		 @RequestMapping(value = "/travel/byuser/{id}", method = RequestMethod.GET)
		    public ResponseEntity<List<Travel>> listTravelsByUser(@PathVariable("id") int id) {
			 	List<Travel> travels = travelDAO.getTravelsByUser(id);
		        if(travels.isEmpty()){
		            return new ResponseEntity<List<Travel>>(HttpStatus.NO_CONTENT);
		        }
		        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
		    }
	    
}
