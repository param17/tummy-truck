package org.zappos.param.tummytruck.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.zappos.param.tummytruck.model.Menu;
import org.zappos.param.tummytruck.model.Restaurant;
import org.zappos.param.tummytruck.service.MenuService;

@Component
@Path("/menus/")
public class MenuApi {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MenuItemApi menuItemApi;
		
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menu> getMenus(@PathParam("restaurantId") int r_id) {
		return menuService.getMenus(r_id);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Menu getMenu(@PathParam("restaurantId") int r_id, @PathParam("id") int id) {
		return menuService.getMenu(r_id, id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addMenu(Menu menu) {
		return menuService.addMenu(menu);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMenu(@PathParam("restaurantId") int r_id, @PathParam("id") int id) {
		menuService.deleteMenu(r_id, id);
		return Response.noContent().build();
	}
	
	@GET
	@Path("/find/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menu> findMenu(@PathParam("restaurantId") int r_id, @PathParam("name") String name) {
		
		return menuService.findMenu(r_id, name);		
	}
	
	@Path("/{menuId}/items")
	public MenuItemApi getMenuItemApi() {
		return menuItemApi;
	}	
}
