package com.ds.travel.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ds.travel.model.CountryUser;

@Repository
@Transactional
public class CountryUserDAOImpl implements CountryUserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CountryUserDAOImpl(){super();}

	@Override
	public int add(CountryUser countryUser) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(countryUser);
		return 0;
	}

	@Override
	public void delete(CountryUser countryUser) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(countryUser);
	}

	@Override
	public void update(CountryUser countryUser) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(countryUser);
	}

	@Override
	public boolean isCountryUserExist(int userID, String countryCode) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM CountryUser WHERE country.code = '"+countryCode+"' and user.id = "+userID);
		if(query.list().size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public CountryUser getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM CountryUser WHERE id = "+id);
		return (CountryUser) query.uniqueResult();
	}

}
