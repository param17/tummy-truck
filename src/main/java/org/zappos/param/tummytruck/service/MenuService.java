package org.zappos.param.tummytruck.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zappos.param.tummytruck.dao.MenuDao;
import org.zappos.param.tummytruck.exception.BadRequestException;
import org.zappos.param.tummytruck.exception.DataNotFoundException;
import org.zappos.param.tummytruck.model.Menu;

@Service
public class MenuService {
	
	Logger logger = Logger.getLogger(MenuService.class.getName());

	@Autowired
	private MenuDao menuDao;
	
	public List<Menu> getMenus(int r_id){
		return menuDao.getMenus(r_id);
	}
	
	public Menu getMenu(int r_id, int id) {
		Menu menu = null;
		
		menu = menuDao.getMenu(r_id, id);
		
		if(menu == null) {
			throw new DataNotFoundException("Menu not found, may be our dog ate it :)");
		}
		
		return menu;
	}
	
	public String addMenu(Menu menu) {
		String returnMessage = null;
		try {
			returnMessage = menuDao.addMenu(menu);
		}
		catch (Exception e) {
			throw new BadRequestException("Exception occurred while finding respective menu!");
		}
		return menuDao.addMenu(menu);
	}
	
	public void deleteMenu(int r_id, int id) {
		Menu menu = null;
		try {
			menu = getMenu(r_id, id);
		}
		catch(Exception e) {
			throw new BadRequestException("Exception occurred while finding respective menu!");
		}
		
		menuDao.deleteMenu(menu);
		
	}
	
	public List<Menu> findMenu(int r_id, String name) {
		if(name.isEmpty() || name == null) {
			logger.error("Menu name shouldn't be empty!");
			throw new BadRequestException("Menu name shouldn't be empty!");
		}
		
		List<Menu> menus = null;
		
		try {
			menus = menuDao.findMenu(r_id, name);
		}
		catch (Exception e) {
			logger.error("Exception occurred while finding the menu!");
			logger.error(e.getMessage());
			throw new BadRequestException("Exception occurred while finding the menu!");
		}
		
		if( menus.isEmpty() || menus == null ) {
			logger.error("Couldn't find the Menu with the name" + name);
			throw new DataNotFoundException("Couldn't find the Menu with the name" + name);
		}
		
		return menus;
	}	
	
}
