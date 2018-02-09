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

import org.zappos.param.tummytruck.model.Menu;
import org.zappos.param.tummytruck.service.MenuService;

import junit.framework.Assert;

public class MenuApiTest {

	@InjectMocks
	MenuApi menuApi;
	
	@Mock
	MenuService menuService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(menuService);
	}

	@Test
	public void getMenusTest() {
		
		List<Menu> menus = new ArrayList<>();
		menus.add(new Menu(1,"menu_1","Morning menu"));
		menus.add(new Menu(2,"menu_2","Lunch menu"));
		menus.add(new Menu(3,"menu_3","Anytime hungry menu"));
		int r_id = 101;
		when(menuService.getMenus(r_id)).thenReturn(menus);
		
		List<Menu> returnedMenus = menuApi.getMenus(r_id);
		
		Assert.assertEquals(returnedMenus, menus);
	}
	
	@Test
	public void getMenuByIdTest() {
		Menu menu = new Menu(1,"menu_1","Anytime hungry menu");
		int id = 1;
		int r_id = 101;
		when(menuService.getMenu(r_id, id)).thenReturn(menu);
		
		Menu returnedMenu = menuApi.getMenu(r_id, id);
		
		Assert.assertEquals(returnedMenu, menu);
	}
	
	@Test
	public void addMenuTest() {
		String msg = "Menu created with id 101";
		Menu menu = new Menu();
		
		when(menuService.addMenu(menu)).thenReturn(msg);
		
		String returnMsg = menuApi.addMenu(menu);
		
		Assert.assertEquals(returnMsg, msg);
	}
	
	@Test
	public void deleteMenuTest() {
		int id = 1;	
		int r_id = 101;
		Mockito.doNothing().when(menuService).deleteMenu(r_id, id);
		
		Response response = menuApi.deleteMenu(r_id, id);
		
		Assert.assertEquals(response.getStatus(), 204);
	}
	
}
