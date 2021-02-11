package aiss.model.repository;

import java.util.Collection;

import aiss.model.Restaurant;
import aiss.model.Dish;

public interface RestaurantRepository {
	
	
	// Dishs
	public void addDish(Dish s);
	public Collection<Dish> getAllDishs();
	public Dish getDish(String DishId);
	public void updateDish(Dish s);
	public void deleteDish(String DishId);
	
	// Restaurant
	public void addRestaurant(Restaurant p);
	public Collection<Restaurant> getAllRestaurant();
	public Restaurant getRestaurant(String RestaurantId);
	public void updateRestaurant(Restaurant p);
	public void deleteRestaurant(String RestaurantId);
	public Collection<Dish> getAll(String RestaurantId);
	public void addDish(String RestaurantId, String DishId);

	public void removeDish(String RestaurantId, String DishId); 

	
	
	
	

}
