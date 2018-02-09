package org.zappos.param.tummytruck.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.zappos.param.tummytruck.dao.RestaurantDao;
import org.zappos.param.tummytruck.exception.DataNotFoundException;
import org.zappos.param.tummytruck.exception.BadRequestException;
import org.zappos.param.tummytruck.model.Restaurant;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class RestaurantServiceTest {
	
	@InjectMocks
	RestaurantService restaurantService;
	
	@Mock
	RestaurantDao restaurantDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(restaurantDao);
	}
	
	@Test
	public void getRestaurantsTest() {
		
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(new Restaurant(1,"restaurant_1","123 St, Boulder", "7458569852", "4.5"));
		restaurants.add(new Restaurant(2,"restaurant_2","12 Walnut, Denver", "7411169852", "2.5"));
		restaurants.add(new Restaurant(3,"restaurant_3","400 Stewart Ave, Las Vegas", "8748569852", "4.0"));
		
		when(restaurantDao.getRestaurants()).thenReturn(restaurants);
		
		List<Restaurant> restaurantsReturned = restaurantService.getRestaurants();
		Assert.assertEquals(restaurantsReturned, restaurants);
	}
	
	@Test
	public void getRestaurantByIdTest_valid() {
		
		Restaurant restaurant = new Restaurant(101,"restaurant_101","400 Stewart Ave, Las Vegas", "8748569852", "4.0");
		int id = 101;
		when(restaurantDao.getRestaurant(101)).thenReturn(restaurant);
		
		Restaurant restaurantReturned = restaurantService.getRestaurant(id);
		Assert.assertEquals(restaurantReturned, restaurant);
	}
	
	@Test(expected = DataNotFoundException.class)
	public void getRestaurantByIdTest_invalid() {
		
		Restaurant restaurant = null;
		int id = 101;
		when(restaurantDao.getRestaurant(101)).thenReturn(restaurant);
		
		Restaurant restaurantReturned = restaurantService.getRestaurant(id);
		Assert.assertEquals(restaurantReturned, restaurant);
	}
	
	@Test
	public void addRestaurant_valid() {
		Restaurant restaurant = new Restaurant(101,"restaurant_101","400 Stewart Ave, Las Vegas", "8748569852", "4.0");
		String msg = "Restaurant information saved successfully with id 101";
		when(restaurantDao.addRestaurant(restaurant)).thenReturn(msg);
		
		String returnMsg = restaurantService.addRestaurant(restaurant);
		Assert.assertEquals(returnMsg, msg);
	}

	@Test(expected = BadRequestException.class)
	public void addRestaurant_invalid() {
		Restaurant restaurant = null;
		String msg = "Restaurant information saved successfully with id 101";
		when(restaurantDao.addRestaurant(restaurant)).thenThrow(BadRequestException.class);
		
		String returnMsg = restaurantService.addRestaurant(restaurant);
		Assert.assertEquals(returnMsg, msg);
	}
	
	@Test
	public void deleteRestaurant_valid() {
		Restaurant restaurant = new Restaurant(101,"restaurant_101","400 Stewart Ave, Las Vegas", "8748569852", "4.0");
		int id = 101;
		when(restaurantDao.getRestaurant(101)).thenReturn(restaurant);
		Mockito.doNothing().when(restaurantDao).deleteRestaurant(restaurant);
		
		restaurantService.deleteRestaurant(id);
	}
	
	@Test(expected = BadRequestException.class)
	public void deleteRestaurant_invalid_1() {
		int id = 101;
		when(restaurantDao.getRestaurant(101)).thenThrow(BadRequestException.class);
		
		restaurantService.deleteRestaurant(id);
	}
	
	@Test(expected = BadRequestException.class)
	public void deleteRestaurant_invalid_2() {
		int id = 101;
		
		when(restaurantDao.getRestaurant(id)).thenReturn(null);
		
		restaurantService.deleteRestaurant(id);
	}
}
