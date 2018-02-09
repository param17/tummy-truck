package org.zappos.param.tummytruck.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zappos.param.tummytruck.model.Restaurant;

@Repository("restaurantDao")
public class RestaurantDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	    
    @Transactional
    public List<Restaurant> getRestaurants() {
    	List<Restaurant> restaurants = new ArrayList<Restaurant>();
    	Query<Restaurant> query = sessionFactory.getCurrentSession().createQuery("from Restaurant");
    	restaurants.addAll(query.list());
    	return restaurants;
    }
    
    @Transactional
    public Restaurant getRestaurant(int id) {
    	Query<Restaurant> query = sessionFactory.getCurrentSession().createQuery("from Restaurant where id=:id");
    	query.setParameter("id", id);
    	return (Restaurant) query.uniqueResult();
    }
    
    @Transactional
    public List<Restaurant> findRestaurant(String name) {
    	List<Restaurant> restaurants = new ArrayList<Restaurant>();
    	Query<Restaurant> query = sessionFactory.getCurrentSession().createQuery("from Restaurant where name like :name");
    	query.setString("name", "%"+name+"%");
    	restaurants.addAll(query.list());
    	return restaurants;
    }
    
    @Transactional
    public String addRestaurant(Restaurant restaurant) {
    	int restaurantId = (Integer) sessionFactory.getCurrentSession().save(restaurant);
        return "Restaurant information saved successfully with id " + restaurantId;
    }
    
    @Transactional
    public void deleteRestaurant(Restaurant restaurant) {
    	sessionFactory.getCurrentSession().delete(restaurant);
    }
    
}
