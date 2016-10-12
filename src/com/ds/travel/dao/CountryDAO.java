package com.ds.travel.dao;

import java.util.List;

import com.ds.travel.model.*;

public interface CountryDAO {
	
	 public int add(Country country);
	 public void delete(Country country);
	 public void update(Country country);
	 public List<Country> getAll();
	 public Country getById(int id);
	 public Country getByCode(String code);
	 public List<Country> getByUser(int id);
}
