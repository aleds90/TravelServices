package com.ds.travel.dao;

import java.util.List;

import com.ds.travel.model.*;

public interface TagDAO {
	
	 public int add(Tag tag);
	 public void delete(Tag tag);
	 public void update(Tag tag);
	 public List<Tag> getAll();
	 public Tag getById(int id);
	 public Tag getByName(String name);
	 public List<Tag> getTagsByTravel(int id);
	 public boolean checkIfExits(String name);

}
