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

import com.ds.travel.dao.CategoryDAO;
import com.ds.travel.model.Category;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	 //------------------- Retrieve All Category --------------------------------------------------------
	 @RequestMapping(value = "/category/", method = RequestMethod.GET)
	    public ResponseEntity<List<Category>> listAllCategories() {
	        List<Category> categories = categoryDAO.getAll();
	        if(categories.isEmpty()){
	            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	    }

	    //-------------------Retrieve Single Category--------------------------------------------------------
	    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Category> getCategory(@PathVariable("id") int id) {
	        System.out.println("Fetching User with id " + id);
	        Category category = categoryDAO.getById(id);
	        if (category == null) {
	            System.out.println("Category with id " + id + " not found");
	            return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Category>(category, HttpStatus.OK);
	    }
	    
	    //-------------------Retrieve Single Category By Slug--------------------------------------------------------
	    @RequestMapping(value = "/category/byslug/{slug}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<Category> getCategoryBySlug(@PathVariable("slug") String slug) {
	        System.out.println("Fetching Category with slug " + slug);
	        Category category = categoryDAO.getBySlug(slug);
	        if (category == null) {
	            System.out.println("Category with slug " + slug + " not found");
	            return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<Category>(category, HttpStatus.OK);
	    }
}
