package org.zappos.param.tummytruck.api;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.zappos.param.tummytruck.model.MenuItem;
import org.zappos.param.tummytruck.service.MenuItemService;

import junit.framework.Assert;

public class MenuItemApiTest {

	@InjectMocks
	MenuItemApi menuItemApi;
	
	@Mock
	MenuItemService menuItemService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(menuItemService);
	}

	@Test
	public void getMenuItemsTest() {
		
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add(new MenuItem(1,"menuItem_1","very delicious item 1", "4.5"));
		menuItems.add(new MenuItem(2,"menuItem_2","very delicious item 2", "2.5"));
		menuItems.add(new MenuItem(3,"menuItem_3","very delicious item 3", "4.0"));
		
		int menu_id = 1;
		
		when(menuItemService.getMenuItems(menu_id)).thenReturn(menuItems);
		
		List<MenuItem> returnedMenuItems = menuItemApi.getMenuItems(menu_id);
		
		Assert.assertEquals(returnedMenuItems, menuItems);
	}
	
	@Test
	public void getMenuItemByIdTest() {
		MenuItem menuItem = new MenuItem(101,"menuItem_101","Medium spicy Indian curry", "4.0");
		int id = 101;
		int menu_id = 1;
		
		when(menuItemService.getMenuItem(menu_id, id)).thenReturn(menuItem);
		
		MenuItem returnedMenuItem = menuItemApi.getMenuItem(menu_id, id);
		
		Assert.assertEquals(returnedMenuItem, menuItem);
	}
	
	@Test
	public void addMenuItemTest() {
		String msg = "MenuItem created with id 101";
		MenuItem menuItem = new MenuItem();
		
		when(menuItemService.addMenuItem(menuItem)).thenReturn(msg);
		
		String returnMsg = menuItemApi.addMenuItem(menuItem);
		
		Assert.assertEquals(returnMsg, msg);
	}
	
	@Test
	public void deleteMenuItemTest() {
		int id = 1;
		int menu_id = 1;
		Mockito.doNothing().when(menuItemService).deleteMenuItem(menu_id, id);
		
		Response response = menuItemApi.deleteMenuItem(menu_id, id);
		
		Assert.assertEquals(response.getStatus(), 204);
	}
	
}
