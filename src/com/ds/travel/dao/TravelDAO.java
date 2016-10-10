package com.ds.travel.dao;

import java.util.List;

import com.ds.travel.model.*;

public interface TravelDAO {
	
	 public int add(Travel travel);
	 public void delete(Travel travel);
	 public void update(Travel travel);
	 public List<Travel> getAll();
	 public Travel getById(int id);
	 public List<Integer> getTravelsIDByProperties(String place, String budget, String category);
}
