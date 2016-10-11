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
public class TargetDAOImpl implements TargetDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public TargetDAOImpl(){super();}

	@Override
	public int add(Target target) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(target);
		return 0;
	}

	@Override
	public void delete(Target target) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(target);
		
	}

	@Override
	public void update(Target target) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(target);
		
	}

	@Override
	public List<Target> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Target");
		return (List<Target>) query.list();
	}

	@Override
	public Target getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Target WHERE id='"+id+"'");
		return (Target) query.uniqueResult();
	}

	@Override
	public Target getByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Target WHERE name='"+name+"'");
		return (Target) query.uniqueResult();
	}

}
