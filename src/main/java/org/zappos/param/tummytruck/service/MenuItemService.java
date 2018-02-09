package org.zappos.param.tummytruck.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.zappos.param.tummytruck.dao.MenuItemDao;
import org.zappos.param.tummytruck.exception.DataNotFoundException;
import org.zappos.param.tummytruck.exception.BadRequestException;
import org.zappos.param.tummytruck.model.MenuItem;

@Service
public class MenuItemService {

	Logger logger = Logger.getLogger(MenuItemService.class.getName());
	
	@Autowired
	private MenuItemDao menuItemDao;
	
	public List<MenuItem> getMenuItems(int m_id){
		return menuItemDao.getMenuItems(m_id);
	}
	
	public MenuItem getMenuItem(int m_id, int id){
		MenuItem menuItem = null;
		
		menuItem = menuItemDao.getMenuItem(m_id, id);
		
		if(menuItem == null) {
			throw new DataNotFoundException("Menu item not found, your favourite food is over!");
		}
		
		return menuItem;
	}
	
	public String addMenuItem(MenuItem menuItem) {
		String returnMessage = null;
		try {
			returnMessage = menuItemDao.addMenuItem(menuItem);
		}
		catch (Exception e) {
			throw new BadRequestException("Exception occurred while finding your favourite menu item.");
		}
		return returnMessage;
	}
	
	public void deleteMenuItem(int m_id, int id) {
		MenuItem menuItem= null;
		
		try {
			menuItem = getMenuItem(m_id, id);
		}
		catch(Exception e) {
			throw new BadRequestException("Exception occurred while finding your favourite menu item.");
		}
		
		menuItemDao.deleteMenuItem(menuItem);
	}
	
	public List<MenuItem> findMenuItem(int r_id, int m_id, String name) {
		if(name.isEmpty() || name == null) {
			logger.error("MenuItem name shouldn't be empty!");
			throw new BadRequestException("MenuItem name shouldn't be empty!");
		}
		
		List<MenuItem> menuItems = null;
		
		try {
			menuItems = menuItemDao.findMenuItem(r_id, m_id, name);
		}
		catch (Exception e) {
			logger.error("Exception occurred while finding the menuItem!");
			logger.error(e.getMessage());
			throw new BadRequestException("Exception occurred while finding the menuItem!");
		}
		
		if( menuItems.isEmpty() || menuItems == null ) {
			logger.error("Couldn't find the MenuItem with the name" + name);
			throw new DataNotFoundException("Couldn't find the MenuItem with the name" + name);
		}
		
		return menuItems;
	}
	
}
