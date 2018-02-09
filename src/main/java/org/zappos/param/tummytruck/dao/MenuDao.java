package org.zappos.param.tummytruck.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.zappos.param.tummytruck.model.Menu;
import org.zappos.param.tummytruck.model.Restaurant;

@Repository("menuDao")
public class MenuDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Menu> getMenus(int r_id) {
		List<Menu> menus = new ArrayList<Menu>();
		Query<Menu> query = sessionFactory.getCurrentSession().createQuery("from Menu entry where entry.restaurant.id=:r_id");
		query.setParameter("r_id", r_id);
		menus.addAll(query.list());
		return menus;
	}
	
	@Transactional
	public Menu getMenu(int r_id, int id) {
		Query<Menu> query = sessionFactory.getCurrentSession().createQuery("from Menu entry where entry.restaurant.id=:r_id and id=:id");
		query.setParameter("r_id", r_id);
		query.setParameter("id", id);
		return (Menu)query.uniqueResult();
	}
	
	@Transactional
	public String addMenu(Menu menu) {
		int menu_id = (Integer) sessionFactory.getCurrentSession().save(menu);
		return "Menu created successfully with id " + menu_id;
	}
	
	@Transactional
	public void deleteMenu(Menu menu) {
		sessionFactory.getCurrentSession().delete(menu);
	}
	
    @Transactional
    public List<Menu> findMenu(int r_id, String name) {
    	List<Menu> menus = new ArrayList<Menu>();
    	Query<Menu> query = sessionFactory.getCurrentSession().createQuery("from Menu entry where name like :name and entry.restaurant.id=:r_id");
    	query.setString("name", "%"+name+"%");
    	query.setParameter("r_id", r_id);
    	menus.addAll(query.list());
    	return menus;
    }
	
}
