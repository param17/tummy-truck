package org.zappos.param.tummytruck.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zappos.param.tummytruck.dao.RestaurantDao;
import org.zappos.param.tummytruck.exception.BadRequestException;
import org.zappos.param.tummytruck.exception.DataNotFoundException;
import org.zappos.param.tummytruck.model.Restaurant;

@Service
public class RestaurantService {

	@Autowired
	RestaurantDao restaurantDao;
	
	Logger logger = Logger.getLogger(RestaurantService.class.getName());
	
	public List<Restaurant> getRestaurants(){
		return restaurantDao.getRestaurants();
	}

	public Restaurant getRestaurant(int id) {
		Restaurant restaurant = null;
		restaurant = restaurantDao.getRestaurant(id);
		
		if(restaurant == null) {
			logger.error("Restaurant you are looking for not found :(");
			throw new DataNotFoundException("Restaurant you are looking for not found :(");
		}
		
		return restaurant;
	}
	
	public String addRestaurant(Restaurant restaurant) {
		String returnMessage = null;
		try {
			returnMessage = restaurantDao.addRestaurant(restaurant);
		}
		catch(Exception e) {
			logger.error("Exception occurred while adding the restaurant!");
			logger.error(e.getMessage());
			throw new BadRequestException("Exception occurred while adding the restaurant!");
		}
		
		return returnMessage;
	}
	
	public void deleteRestaurant(int id) {
		Restaurant restaurant = null;
		try {
			restaurant = getRestaurant(id);
		}
		catch(Exception e) {
			logger.error("Exception occurred while finding the restaurant!");
			logger.error(e.getMessage());
			throw new BadRequestException("Exception occurred while finding the restaurant!");
		}
		
		restaurantDao.deleteRestaurant(restaurant);
	}
	
	public List<Restaurant> findRestaurant(String name) {
		if(name.isEmpty() || name == null) {
			logger.error("Restaurant name shouldn't be empty!");
			throw new BadRequestException("Restaurant name shouldn't be empty!");
		}
		
		List<Restaurant> restaurants = null;
		
		try {
			restaurants = restaurantDao.findRestaurant(name);
		}
		catch (Exception e) {
			logger.error("Exception occurred while finding the restaurant!");
			logger.error(e.getMessage());
			throw new BadRequestException("Exception occurred while finding the restaurant!");
		}
		
		if( restaurants.isEmpty() || restaurants == null ) {
			logger.error("Couldn't find the Restaurant with the name" + name);
			throw new DataNotFoundException("Couldn't find the Restaurant with the name" + name);
		}
		
		return restaurants;
		
	}
	
}
