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

import com.ds.travel.dao.StageDAO;
import com.ds.travel.model.Stage;
import com.ds.travel.model.Travel;
import com.ds.travel.service.StageWrapperToObject;
import com.ds.travel.wrapper.StageWrapper;
import com.ds.travel.wrapper.TravelWrapper;

@RestController
public class StageController {

	@Autowired
	private StageDAO stageDAO;
	
	@Autowired
	private StageWrapperToObject stageWrapperToObject;
	
	 //------------------- Retrieve All User --------------------------------------------------------
	 @RequestMapping(value = "/stage/", method = RequestMethod.GET)
	    public ResponseEntity<List<Stage>> listAllStages() {
	        List<Stage> stages = stageDAO.getAll();
	        if(stages.isEmpty()){
	            return new ResponseEntity<List<Stage>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Stage>>(stages, HttpStatus.OK);
	    }
	 
	    //-------------------Retrieve Single Travel--------------------------------------------------------
	    @RequestMapping(value = "/stage/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Stage> getStage(@PathVariable("id") int id) {
	        System.out.println("Fetching Stage with id " + id);
	        Stage stage = stageDAO.getById(id);
	        if (stage == null) {
	            System.out.println("Stage with id " + id + " not found");
	            return new ResponseEntity<Stage>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Stage>(stage, HttpStatus.OK);
	    }	 
	    
	    //-------------------Retrieve Stages By Travel--------------------------------------------------------
	    @RequestMapping(value = "/stage/bytravel/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<List<Stage>> getStageByTravel(@PathVariable("id") int id) {
	        System.out.println("Fetching Stage with id " + id);
	        List<Stage> stages = stageDAO.getByTravel(id);
	        if(stages.isEmpty()){
	            return new ResponseEntity<List<Stage>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Stage>>(stages, HttpStatus.OK);
	    }	 
	    
	    //-------------------Create a Stage--------------------------------------------------------
	    @RequestMapping(value = "/stage/", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<Void> createStage(@RequestBody StageWrapper stageWrapper,    UriComponentsBuilder ucBuilder) {
	    	Stage stage = stageWrapperToObject.toStage(stageWrapper);
	        stageDAO.add(stage);
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
	    
	    //------------------- Update a Stage --------------------------------------------------------
	    @RequestMapping(value = "/stage/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Stage> updateStage(@PathVariable("id") int id, @RequestBody StageWrapper stageWrapper) {
	        System.out.println("Updating Stage " + id);
	        Stage currentStage = stageDAO.getById(id);
	        if (currentStage==null) {
	            System.out.println("Travel with id " + id + " not found");
	            return new ResponseEntity<Stage>(HttpStatus.NOT_FOUND);
	        }
	        Stage stage = stageWrapperToObject.toStage(stageWrapper);
	        
	        currentStage.setCity(stage.getCity());
	        currentStage.setTravel(stage.getTravel());
	        currentStage.setStartDate(stage.getStartDate());
	        currentStage.setEndDate(stage.getEndDate());
	 
	        stageDAO.update(currentStage);
	        return new ResponseEntity<Stage>(currentStage, HttpStatus.OK);
	    }	
}
