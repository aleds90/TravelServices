package com.ds.travel.dao;

import java.util.List;

import com.ds.travel.model.*;

public interface StageDAO {
	
	 public int add(Stage stage);
	 public void delete(Stage stage);
	 public void update(Stage stage);
	 public List<Stage> getAll();
	 public Stage getById(int id);
	 public List<Stage> getByTravel(int id);
}
