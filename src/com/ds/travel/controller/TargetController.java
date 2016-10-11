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

import com.ds.travel.dao.TargetDAO;
import com.ds.travel.model.Category;
import com.ds.travel.model.Target;

@RestController
public class TargetController {
	
	@Autowired
	private TargetDAO targetDAO;

	 //------------------- Retrieve All Category --------------------------------------------------------
	 @RequestMapping(value = "/target/", method = RequestMethod.GET)
	    public ResponseEntity<List<Target>> listAllTarget() {
	        List<Target> targets = targetDAO.getAll();
	        if(targets.isEmpty()){
	            return new ResponseEntity<List<Target>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Target>>(targets, HttpStatus.OK);
	    }

	    //-------------------Retrieve Single Category--------------------------------------------------------
	    @RequestMapping(value = "/target/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Target> getTarget(@PathVariable("id") int id) {
	        System.out.println("Fetching Target with id " + id);
	        Target target = targetDAO.getById(id);
	        if (target == null) {
	            System.out.println("Target with id " + id + " not found");
	            return new ResponseEntity<Target>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Target>(target, HttpStatus.OK);
	    }
	    
	
}
