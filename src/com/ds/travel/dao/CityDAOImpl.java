package com.ds.travel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ds.travel.model.*;

@Repository
@Transactional
public class CityDAOImpl implements CityDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public CityDAOImpl(){super();}
	
	@Override
	public int add(City city) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<City> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM City");
		return (List<City>) query.list();
	}

	@Override
	public City getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM City WHERE id='"+id+"'");
		return (City) query.uniqueResult();
	}

}
