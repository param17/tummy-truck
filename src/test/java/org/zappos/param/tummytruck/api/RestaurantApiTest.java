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

import org.zappos.param.tummytruck.model.Restaurant;
import org.zappos.param.tummytruck.service.RestaurantService;

import junit.framework.Assert;

public class RestaurantApiTest {

	@InjectMocks
	RestaurantApi restaurantApi;
	
	@Mock
	RestaurantService restaurantService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(restaurantService);
	}

	@Test
	public void getRestaurantsTest() {
		
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(new Restaurant(1,"restaurant_1","123 St, Boulder", "7458569852", "4.5"));
		restaurants.add(new Restaurant(2,"restaurant_2","12 Walnut, Denver", "7411169852", "2.5"));
		restaurants.add(new Restaurant(3,"restaurant_3","400 Stewart Ave, Las Vegas", "8748569852", "4.0"));
		
		when(restaurantService.getRestaurants()).thenReturn(restaurants);
		
		List<Restaurant> returnedRestaurants = restaurantApi.getRestaurants();
		
		Assert.assertEquals(returnedRestaurants, restaurants);
	}
	
	@Test
	public void getRestaurantByIdTest() {
		Restaurant restaurant = new Restaurant(101,"restaurant_101","400 Stewart Ave, Las Vegas", "8748569852", "4.0");
		int id = 101;
		
		when(restaurantService.getRestaurant(id)).thenReturn(restaurant);
		
		Restaurant returnedRestaurant = restaurantApi.getRestaurant(id);
		
		Assert.assertEquals(returnedRestaurant, restaurant);
	}
	
	@Test
	public void addRestaurantTest() {
		String msg = "Restaurant created with id 101";
		Restaurant restaurant = new Restaurant();
		
		when(restaurantService.addRestaurant(restaurant)).thenReturn(msg);
		
		String returnMsg = restaurantApi.addRestaurant(restaurant);
		
		Assert.assertEquals(returnMsg, msg);
	}
	
	@Test
	public void deleteRestaurantTest() {
		int id = 101;	
		Mockito.doNothing().when(restaurantService).deleteRestaurant(id);
		
		Response response = restaurantApi.deleteRestaurant(id);
		
		Assert.assertEquals(response.getStatus(), 204);
	}
	
}
