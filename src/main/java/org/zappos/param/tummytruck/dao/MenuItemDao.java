package org.zappos.param.tummytruck.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zappos.param.tummytruck.model.Menu;
import org.zappos.param.tummytruck.model.MenuItem;

@Repository("menuItemDao")
public class MenuItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<MenuItem> getMenuItems(int m_id){
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		Query<MenuItem> query = sessionFactory.getCurrentSession().createQuery("from MenuItem entry where entry.menu.id=:m_id");
		query.setParameter("m_id", m_id);
		menuItems.addAll(query.list());
		return menuItems;
	}
	
	@Transactional
	public MenuItem getMenuItem(int m_id, int id){
		Query<MenuItem> query = sessionFactory.getCurrentSession().createQuery("from MenuItem entry where entry.menu.id=:m_id and id=:id");
		query.setParameter("m_id", m_id);
		query.setParameter("id", id);
		return (MenuItem) query.uniqueResult();
	}
	
	@Transactional
	public String addMenuItem(MenuItem menuItem) {
		int menu_item_id = (Integer) sessionFactory.getCurrentSession().save(menuItem);
		return "Menu Item successfully created with id " + menu_item_id;
	}
	
	@Transactional
	public void deleteMenuItem(MenuItem menuItem) {
		sessionFactory.getCurrentSession().delete(menuItem);
	}
	
    @Transactional
    public List<MenuItem> findMenuItem(int r_id, int m_id, String name) {
    	List<MenuItem> menuItems = new ArrayList<MenuItem>();
    	Query<MenuItem> query = sessionFactory.getCurrentSession().createQuery("from MenuItem entry where name like :name and entry.menu.id=:m_id and entry.menu.restaurant.id=:r_id");
    	query.setString("name", "%"+name+"%");
    	query.setParameter("r_id", r_id);
    	query.setParameter("m_id", m_id);
    	menuItems.addAll(query.list());
    	return menuItems;
    }
	
}
