package org.zappos.param.tummytruck.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.zappos.param.tummytruck.dao.MenuDao;
import org.zappos.param.tummytruck.exception.DataNotFoundException;
import org.zappos.param.tummytruck.exception.BadRequestException;
import org.zappos.param.tummytruck.model.Menu;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class MenuServiceTest {
	
	@InjectMocks
	MenuService menuService;
	
	@Mock
	MenuDao menuDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(menuDao);
	}
	
	@Test
	public void getMenusTest() {
		
		List<Menu> menus = new ArrayList<>();
		menus.add(new Menu(1,"menu_1","Morning menu"));
		menus.add(new Menu(2,"menu_2","Lunch menu"));
		menus.add(new Menu(3,"menu_3","Anytime hungry menu"));
		int r_id = 101;
		when(menuDao.getMenus(r_id)).thenReturn(menus);
		
		List<Menu> menusReturned = menuService.getMenus(r_id);
		Assert.assertEquals(menusReturned, menus);
	}
	
	@Test
	public void getMenuByIdTest_valid() {
		
		Menu menu = new Menu(1,"menu_1","Anytime hungry menu");
		int id = 1;
		int r_id = 101;
		when(menuDao.getMenu(r_id, 1)).thenReturn(menu);
		
		Menu menuReturned = menuService.getMenu(r_id, id);
		Assert.assertEquals(menuReturned, menu);
	}
	
	@Test(expected = DataNotFoundException.class)
	public void getMenuByIdTest_invalid() {
		
		Menu menu = null;
		int id = 1;
		int r_id = 101;
		when(menuDao.getMenu(1, r_id)).thenReturn(menu);
		
		Menu menuReturned = menuService.getMenu(r_id, id);
		Assert.assertEquals(menuReturned, menu);
	}
	
	@Test
	public void addMenu_valid() {
		Menu menu = new Menu(1,"menu_1","Anytime hungry menu");
		String msg = "Menu information saved successfully with id 1";
		when(menuDao.addMenu(menu)).thenReturn(msg);
		
		String returnMsg = menuService.addMenu(menu);
		Assert.assertEquals(returnMsg, msg);
	}

	@Test(expected = BadRequestException.class)
	public void addMenu_invalid() {
		Menu menu = null;
		String msg = "Menu information saved successfully with id 101";
		when(menuDao.addMenu(menu)).thenThrow(BadRequestException.class);
		
		String returnMsg = menuService.addMenu(menu);
		Assert.assertEquals(returnMsg, msg);
	}
	
	@Test
	public void deleteMenu_valid() {
		Menu menu = new Menu(1,"menu_1","Anytime hungry menu");
		int id = 1;
		int r_id = 101;
		when(menuDao.getMenu(r_id, id)).thenReturn(menu);
		Mockito.doNothing().when(menuDao).deleteMenu(menu);
		
		menuService.deleteMenu(r_id, id);
	}
	
	@Test(expected = BadRequestException.class)
	public void deleteMenu_invalid_1() {
		int id = 1;
		int r_id = 101;
		when(menuDao.getMenu(1, r_id)).thenThrow(BadRequestException.class);
		
		menuService.deleteMenu(r_id, id);
	}
	
	@Test(expected = BadRequestException.class)
	public void deleteMenu_invalid_2() {
		int id = 1;
		int r_id = 101;
		when(menuDao.getMenu(r_id, id)).thenReturn(null);
		
		menuService.deleteMenu(r_id, id);
	}
}
