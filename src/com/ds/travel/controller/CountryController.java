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

import com.ds.travel.dao.CountryDAO;
import com.ds.travel.model.Category;
import com.ds.travel.model.Country;

@RestController
public class CountryController {

	@Autowired
	private CountryDAO countryDAO;
	
	 //------------------- Retrieve All Country --------------------------------------------------------
	 @RequestMapping(value = "/country/", method = RequestMethod.GET)
	    public ResponseEntity<List<Country>> listAll() {
	        List<Country> countries = countryDAO.getAll();
	        if(countries.isEmpty()){
	            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	    }
	 
	    //-------------------Retrieve Single Country--------------------------------------------------------
	    @RequestMapping(value = "/country/{code}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Country> getCountry(@PathVariable("code") String code) {
	        System.out.println(code);
	        Country country = countryDAO.getByCode(code);
	        if (country == null) {
	            System.out.println("Country with code " + code + " not found");
	            return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Country>(country, HttpStatus.OK);
	    }
	    
		 //------------------- Retrieve All Country By User --------------------------------------------------------
		 @RequestMapping(value = "/country/byuser/{id}", method = RequestMethod.GET)
		    public ResponseEntity<List<Country>> getCountriesByUser(@PathVariable("id") int id) {
		        List<Country> countries = countryDAO.getByUser(id);
		        if(countries.isEmpty()){
		            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
		        }
		        return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
		    }
	    
}
