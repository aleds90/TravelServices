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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.travel.dao.TravelDAO;
import com.ds.travel.model.*;

@RestController
public class TravelController {
	
	@Autowired 
	private TravelDAO travelDAO;
	
		//------------------- Retrieve All Travel --------------------------------------------------------
		 @RequestMapping(value = "/travel/", method = RequestMethod.GET)
		    public ResponseEntity<List<Travel>> listAllTravels() {
			 	List<Travel> travels = travelDAO.getAll();
		        if(travels.isEmpty()){
		            return new ResponseEntity<List<Travel>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
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
	    
	    //-------------------Create a User--------------------------------------------------------
	    @RequestMapping(value = "/travel/", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<Void> createTravel(@RequestBody Travel travel, UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Travel " + travel.getTitle());
	        travelDAO.add(travel);
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
	  

}
