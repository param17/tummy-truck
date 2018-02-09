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
import org.zappos.param.tummytruck.model.Restaurant;
import org.zappos.param.tummytruck.service.RestaurantService;

@Component
@Path("/restaurants")
public class RestaurantApi {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private MenuApi menuApi;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant getRestaurant(@PathParam("id") int id) {
		return restaurantService.getRestaurant(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addRestaurant(Restaurant restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRestaurant(@PathParam("id") int id) {
		
		restaurantService.deleteRestaurant(id);
        return Response.noContent().build();
	}
	
	@GET
	@Path("/find/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Restaurant> findRestaurant(@PathParam("name") String name) {
		
		return restaurantService.findRestaurant(name);		
	}
	
	@Path("/{restaurantId}/menus")
	public MenuApi getMenuApi() {
		return menuApi;
	}
		
}
