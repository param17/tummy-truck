package org.zappos.param.tummytruck.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.zappos.param.tummytruck.dao.MenuItemDao;
import org.zappos.param.tummytruck.exception.DataNotFoundException;
import org.zappos.param.tummytruck.exception.BadRequestException;
import org.zappos.param.tummytruck.model.MenuItem;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class MenuItemServiceTest {
	
	@InjectMocks
	MenuItemService menuItemService;
	
	@Mock
	MenuItemDao menuItemDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(menuItemDao);
	}
	
	@Test
	public void getMenuItemsTest() {
		
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add(new MenuItem(1,"menuItem_1","very delicious item 1", "4.5"));
		menuItems.add(new MenuItem(2,"menuItem_2","very delicious item 2", "2.5"));
		menuItems.add(new MenuItem(3,"menuItem_3","very delicious item 3", "4.0"));
		
		int menu_id = 1;
		
		when(menuItemDao.getMenuItems(menu_id)).thenReturn(menuItems);
		
		List<MenuItem> menuItemsReturned = menuItemService.getMenuItems(menu_id);
		Assert.assertEquals(menuItemsReturned, menuItems);
	}
	
	@Test
	public void getMenuItemByIdTest_valid() {
		
		MenuItem menuItem = new MenuItem(1,"menuItem_1","Okish dish", "4.0");
		int id = 1;
		int menu_id = 1;
		when(menuItemDao.getMenuItem(menu_id, 1)).thenReturn(menuItem);
		
		MenuItem menuItemReturned = menuItemService.getMenuItem(id, menu_id);
		Assert.assertEquals(menuItemReturned, menuItem);
	}
	
	@Test(expected = DataNotFoundException.class)
	public void getMenuItemByIdTest_invalid() {
		
		MenuItem menuItem = null;
		int id = 101;
		int menu_id = 1;
		when(menuItemDao.getMenuItem(menu_id, 101)).thenReturn(menuItem);
		
		MenuItem menuItemReturned = menuItemService.getMenuItem(menu_id, id);
		Assert.assertEquals(menuItemReturned, menuItem);
	}
	
	@Test
	public void addMenuItem_valid() {
		MenuItem menuItem = new MenuItem(101,"menuItem_101","little extra spicy dish", "4.0");
		String msg = "MenuItem information saved successfully with id 101";
		when(menuItemDao.addMenuItem(menuItem)).thenReturn(msg);
		
		String returnMsg = menuItemService.addMenuItem(menuItem);
		Assert.assertEquals(returnMsg, msg);
	}

	@Test(expected = BadRequestException.class)
	public void addMenuItem_invalid() {
		MenuItem menuItem = null;
		String msg = "MenuItem information saved successfully with id 101";
		when(menuItemDao.addMenuItem(menuItem)).thenThrow(BadRequestException.class);
		
		String returnMsg = menuItemService.addMenuItem(menuItem);
		Assert.assertEquals(returnMsg, msg);
	}
	
	@Test
	public void deleteMenuItem_valid() {
		MenuItem menuItem = new MenuItem(101,"menuItem_101","Medium spicy Indian curry", "4.0");
		int id = 101;
		int menu_id = 1;
		when(menuItemDao.getMenuItem(menu_id, 101)).thenReturn(menuItem);
		Mockito.doNothing().when(menuItemDao).deleteMenuItem(menuItem);
		
		menuItemService.deleteMenuItem(menu_id, id);
	}
	
	@Test(expected = BadRequestException.class)
	public void deleteMenuItem_invalid_1() {
		int id = 101;
		int menu_id = 1;
		when(menuItemDao.getMenuItem(menu_id, 101)).thenThrow(BadRequestException.class);
		
		menuItemService.deleteMenuItem(menu_id, id);
	}
	
	@Test(expected = BadRequestException.class)
	public void deleteMenuItem_invalid_2() {
		int id = 101;
		int menu_id = 1;
		
		when(menuItemDao.getMenuItem(menu_id, id)).thenReturn(null);
		
		menuItemService.deleteMenuItem(menu_id, id);
	}
}
