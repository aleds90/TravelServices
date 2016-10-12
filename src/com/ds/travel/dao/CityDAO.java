package com.ds.travel.dao;

import java.util.List;

import com.ds.travel.model.*;

public interface CityDAO {
	 public int add( City city);
	 public void delete( City city);
	 public void update( City city);
	 public List< City> getAll();
	 public  City getById(int id);
	 public List<City> getByCountry(String code);
	 
}
