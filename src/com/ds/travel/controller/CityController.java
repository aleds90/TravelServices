package com.ds.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ds.travel.dao.CityDAO;
import com.ds.travel.model.City;
import com.ds.travel.model.Country;

@RestController
public class CityController {
	
	@Autowired
	private CityDAO cityDAO;
	
	 //------------------- Retrieve All City --------------------------------------------------------
	 @RequestMapping(value = "/city/", method = RequestMethod.GET)
	    public ResponseEntity<List<City>> listAll() {
	        List<City> cities = cityDAO.getAll();
	        if(cities.isEmpty()){
	            return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	    }
	 
	    //-------------------Retrieve Single City--------------------------------------------------------
	    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<City> getCity(@PathVariable("id") int id) {
	  
	        City city = cityDAO.getById(id);
	        if (city == null) {
	            System.out.println("City with id " + id + " not found");
	            return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<City>(city, HttpStatus.OK);
	    }
	    
	    //-------------------Retrieve  City By Country--------------------------------------------------------
	    @RequestMapping(value = "/city/bycountry/{code}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<List<City>> getCityByCountry(@PathVariable("code") String code) {
	  
	    	List<City> cities = cityDAO.getByCountry(code);
	        if (cities == null) {
	            System.out.println("Country with code " + code + " do not have cities");
	            return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	    }
	    
	    
}
