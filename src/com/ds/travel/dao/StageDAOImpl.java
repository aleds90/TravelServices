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
public class StageDAOImpl implements StageDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public StageDAOImpl(){super();}


	@Override
	public int add(Stage stage) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(stage);
		return 0;
	}

	@Override
	public void delete(Stage stage) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(stage);
		
	}

	@Override
	public void update(Stage stage) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(stage);
		
	}

	@Override
	public List<Stage> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Stage");
		return (List<Stage>) query.list();	
	}

	@Override
	public Stage getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Stage WHERE id='"+id+"'");
		return (Stage) query.uniqueResult();
	}


	@Override
	public List<Stage> getByTravel(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Stage as stage WHERE stage.travel.id='"+id+"'");
		return (List<Stage>) query.list();	
		}

}
