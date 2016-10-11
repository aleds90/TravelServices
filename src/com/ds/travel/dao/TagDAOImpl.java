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
public class TagDAOImpl implements TagDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public TagDAOImpl(){super();}


	@Override
	public int add(Tag tag) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(tag);
		return 0;
	}

	@Override
	public void delete(Tag tag) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(tag);
	}

	@Override
	public void update(Tag tag) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tag);
	}

	@Override
	public List<Tag> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Tag");
		return (List<Tag>) query.list();
	}

	@Override
	public Tag getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Tag WHERE id='"+id+"'");
		return (Tag) query.uniqueResult();
	}


	@Override
	public Tag getByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Tag WHERE name='"+name+"'");
		return  (Tag) query.list().get(0);
	}


	@Override
	public List<Tag> getTagsByTravel(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select distinct tag from Tag as tag inner join tag.travelTags as travelTag WHERE travelTag.travel.id ='"+id+"'");
		return (List<Tag>) query.list();
	}


	@Override
	public boolean checkIfExits(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Tag WHERE name='"+name+"'");
		if(query.list().size() > 0){
			return true;
		}
		return false;
	}

}
