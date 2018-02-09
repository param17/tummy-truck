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

import org.zappos.param.tummytruck.model.MenuItem;
import org.zappos.param.tummytruck.model.Restaurant;
import org.zappos.param.tummytruck.service.MenuItemService;

@Component
@Path("/items/")
public class MenuItemApi {

	@Autowired
	private MenuItemService menuItemService;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MenuItem> getMenuItems(@PathParam("menuId") int m_id) {
		return menuItemService.getMenuItems(m_id);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuItem getMenuItem(@PathParam("menuId") int m_id, @PathParam("id") int id) {
		return menuItemService.getMenuItem(m_id, id);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addMenuItem(MenuItem menuItem) {
		return menuItemService.addMenuItem(menuItem);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMenuItem(@PathParam("menuId") int m_id, @PathParam("id") int id) {
		menuItemService.deleteMenuItem(m_id, id);
		return Response.noContent().build();
	}
	
	@GET
	@Path("/find/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MenuItem> findMenuItem(@PathParam("restaurantId") int r_id, @PathParam("menuId") int m_id, @PathParam("name") String name) {
		
		return menuItemService.findMenuItem(r_id, m_id, name);		
	}
}
