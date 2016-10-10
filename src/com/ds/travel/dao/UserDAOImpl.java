package com.ds.travel.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ds.travel.*;
import com.ds.travel.model.Role;
import com.ds.travel.model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(){super();}
	
	@Override
	public int add(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		user.setCreatedAt(new Timestamp(date.getTime()));
		return (int) session.save(user);
	}

	@Override
	public void delete(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(user);
		
	}

	@Override
	public void update(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		user.setUpdatedAt(new Timestamp(date.getTime()));
		session.update(user);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User");
		return (List<User>) query.list();
	}
	
	@Override
    public User getByUsername(final String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE username='"+username+"'");
        User user = (User) query.uniqueResult();
        if(user!=null){
	        Role r = new Role();
	        r.setName("ROLE_APP");
	        List<Role> roles = new ArrayList<Role>();
	        roles.add(r);
	        user.setAuthorities(roles);
	        return user;
        }else{
        	return null;
        }
    }

	@Override
	public User getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = null;
		Query query = session.createQuery("FROM User WHERE id='"+id+"'");
		user = (User)query.uniqueResult();
		return user;
	}

	@Override
	public List<User> getByAttributes(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE profile.name LIKE '"+keyword+"%' OR username LIKE '"+keyword+"%'");
		System.out.println(query.toString());
		return (List<User>)query.list();
	}

	@Override
	public boolean isUserExist(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE username='"+user.getUsername()+"'");
        User result = (User) query.uniqueResult();
        if(result!=null){
	        return true;
        }
		return false;
	}
	
}

