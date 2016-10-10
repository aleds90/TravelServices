package com.ds.travel.dao;

import java.sql.Timestamp;
import java.util.Date;
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
public class TravelDAOImpl implements TravelDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public TravelDAOImpl(){super();}


	@Override
	public int add(Travel travel) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		travel.setCreatedAt(new Timestamp(date.getTime()));
		session.save(travel);
		return 0;
	}

	@Override
	public void delete(Travel travel) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(travel);
		
	}

	@Override
	public void update(Travel travel) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		travel.setUpdatedAt(new Timestamp(date.getTime()));
		session.update(travel);
		
	}

	@Override
	public List<Travel> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Travel");
		return (List<Travel>) query.list();
	}

	@Override
	public Travel getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Travel WHERE id='"+id+"'");
		return (Travel) query.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Travel> getTravelsIDByProperties(String budget, String place, String category) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select distinct travel from Travel as travel inner join travel.stages as stage "
				+ "where (stage.city.name like '"+place+"%' or stage.city.country.name like '"+place+"%' "
						+ "or stage.city.country.region like '%"+place+"%')"
							+ " and (travel.target.maxiumBudget < "+budget+") "
								+ "and (travel.category.slug like '%"+category+"%')";
		Query query = session.createQuery(hql);
		return (List<Travel>) query.list();
	}


	@Override
	public List<Travel> getTravelsByUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Travel as travel WHERE travel.user.id ='"+id+"'");
		return (List<Travel>) query.list();
	}

}

//if(!category.equals("All")){
//	queryString = "SELECT * FROM travel WHERE travel.id IN (SELECT distinct travel.id FROM travel left join stage on travel.id = stage.travel left join city on stage.city = city.id"
//			+ " left join country on city.CountryCode = country.Code left join target on target.id = travel.target left join category on category.id = travel.category "
//			+ "where (city.name like '"+place+"%' or country.Name like '"+place+"%' ) AND (target.maxium_budget < "+budget+" ) AND (category.slug='"+category+"'))";
//	}else{
//	queryString = "SELECT * FROM travel WHERE travel.id IN (SELECT distinct travel.id FROM travel left join stage on travel.id = stage.travel left join city on stage.city = city.id"
//			+ " left join country on city.CountryCode = country.Code left join target on target.id = travel.target left join category on category.id = travel.category "
//			+ "where (city.name like '"+place+"%' or country.Name like '"+place+"%' ) AND (target.maxium_budget < "+budget+" ))";
//	}
