package com.ds.travel.dao;

import com.ds.travel.model.*;

public interface CountryUserDAO {
	
	 public CountryUser getById(int id);
	 public int add(CountryUser countryUser);
	 public void delete(CountryUser countryUser);
	 public void update(CountryUser countryUser);
	 public boolean isCountryUserExist(int userID, String countryCode);
}
