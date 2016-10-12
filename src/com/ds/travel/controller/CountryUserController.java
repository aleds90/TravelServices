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

import com.ds.travel.dao.CountryUserDAO;
import com.ds.travel.model.Category;
import com.ds.travel.model.CountryUser;
import com.ds.travel.model.Stage;
import com.ds.travel.model.User;
import com.ds.travel.service.CountryUserWrapperToObject;
import com.ds.travel.wrapper.CountryUserWrapper;
import com.ds.travel.wrapper.StageWrapper;

@RestController
public class CountryUserController {
	
	@Autowired
	private CountryUserDAO countryUserDAO;
	
	@Autowired
	private CountryUserWrapperToObject countryUserWrapperToObject;
	
    //-------------------Create a CountryUser--------------------------------------------------------
    @RequestMapping(value = "/countryUser/", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createCountryUser(@RequestBody CountryUserWrapper countryUserWrapper,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating CountryUser ");
        if (countryUserDAO.isCountryUserExist(countryUserWrapper.getUserID(), countryUserWrapper.getCountryCode())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        CountryUser countryUser = countryUserWrapperToObject.toCountryUser(countryUserWrapper);
        countryUserDAO.add(countryUser);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    //------------------- Update a CountryUser --------------------------------------------------------
    @RequestMapping(value = "/countryUser/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CountryUser> updateCountryUser(@PathVariable("id") int id, @RequestBody CountryUserWrapper countryUserWrapper) {
        System.out.println("Updating CountryUser " + id);
        CountryUser currentCountryUser = countryUserDAO.getById(id);
        if (currentCountryUser==null) {
            System.out.println("CountryUser with id " + id + " not found");
            return new ResponseEntity<CountryUser>(HttpStatus.NOT_FOUND);
        }
        CountryUser countryUser = countryUserWrapperToObject.toCountryUser(countryUserWrapper);
        currentCountryUser.setCountry(countryUser.getCountry());
        currentCountryUser.setUser(countryUser.getUser());

        countryUserDAO.update(currentCountryUser);
        return new ResponseEntity<CountryUser>(currentCountryUser, HttpStatus.OK);
    }	
      
    //------------------- Update a CountryUser --------------------------------------------------------
    @RequestMapping(value = "/countryUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        System.out.println("Deleting CountryUser " + id);
        CountryUser countryUser = countryUserDAO.getById(id);
        if (countryUser==null) {
            System.out.println("CountryUser with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        countryUserDAO.delete(countryUser);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }	
    
    
	
}
