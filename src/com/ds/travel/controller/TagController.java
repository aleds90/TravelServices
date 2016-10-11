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

import com.ds.travel.dao.TagDAO;
import com.ds.travel.model.Category;
import com.ds.travel.model.Tag;
import com.ds.travel.model.User;

@RestController
public class TagController {
	
	@Autowired
	private TagDAO tagDAO;

	 //------------------- Retrieve All Tag --------------------------------------------------------
	 @RequestMapping(value = "/tag/", method = RequestMethod.GET)
	    public ResponseEntity<List<Tag>> listAllCategories() {
	        List<Tag> tags = tagDAO.getAll();
	        if(tags.isEmpty()){
	            return new ResponseEntity<List<Tag>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
	    }
	    //-------------------Retrieve Single Tag--------------------------------------------------------
	    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Tag> getTag(@PathVariable("id") int id) {
	        System.out.println("Fetching Tag with id " + id);
	        Tag tag = tagDAO.getById(id);
	        if (tag == null) {
	            System.out.println("Tag with id " + id + " not found");
	            return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
	    }
	    
	    //-------------------Retrieve Single Tag By Name--------------------------------------------------------
	    @RequestMapping(value = "/tag/byname/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Tag> getTagByName(@PathVariable("name") String name) {
	        System.out.println("Fetching Tag with name " + name);
	        Tag tag = tagDAO.getByName(name);
	        if (tag == null) {
	            System.out.println("Tag with name " + name + " not found");
	            return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
	    }
	    
	    //-------------------Retrieve Tags By Travel--------------------------------------------------------
	    @RequestMapping(value = "/tag/bytravel/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<List<Tag>> getTagsByTravel(@PathVariable("id") int id) {
	        System.out.println("Fetching Tag with id " + id);
	        List<Tag> tags = tagDAO.getTagsByTravel(id);
	        if(tags.isEmpty()){
	            return new ResponseEntity<List<Tag>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
	    }
	    
	    //-------------------Create a Tag--------------------------------------------------------
	    @RequestMapping(value = "/tag/", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<Void> createTag(@RequestBody Tag tag, UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating tag " + tag.getName());
	        if(tagDAO.checkIfExits(tag.getName())){
	        	 return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	        tagDAO.add(tag);
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
}
