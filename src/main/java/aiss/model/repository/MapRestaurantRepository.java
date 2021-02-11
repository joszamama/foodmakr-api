package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import aiss.model.Restaurant;
import aiss.model.Dish;

public class MapRestaurantRepository implements RestaurantRepository {

	Map<String, Restaurant> RestaurantMap;
	Map<String, Dish> DishMap;
	private static MapRestaurantRepository instance = null;
	private int index = 0; // Index to create Restaurants and Dishs' identifiers.

	public static MapRestaurantRepository getInstance() {

		if (instance == null) {
			instance = new MapRestaurantRepository();
			instance.init();
		}

		return instance;
	}

	public void init() {

		RestaurantMap = new HashMap<String, Restaurant>();
		DishMap = new HashMap<String, Dish>();

		// Create Dishs
		Dish d1 = new Dish();
		d1.setName("Dish 1");
		d1.setType("Tapa");
		addDish(d1);

		Dish d2 = new Dish();
		d2.setName("Dish 2");
		d2.setType("Halph-plate");
		addDish(d2);

		Dish d3 = new Dish();
		d3.setName("Dish 3");
		d3.setType("Plate");
		addDish(d3);

		Dish d4 = new Dish();
		d4.setName("Dish 4");
		d4.setType("Tapa");
		addDish(d4);

		Dish d5 = new Dish();
		d5.setName("Dish 5");
		d5.setType("Plate");
		addDish(d5);

		// Create Restaurants
		Restaurant jaRestaurant = new Restaurant();
		jaRestaurant.setName("AISSRestaurant");
		jaRestaurant.setDescription("AISS Restaurant");
		addRestaurant(jaRestaurant);

		Restaurant RestaurantNoDishs = new Restaurant();
		RestaurantNoDishs.setName("NoDishs");
		RestaurantNoDishs.setDescription("A Restaurant without Dishs");
		addRestaurant(RestaurantNoDishs);

		Restaurant Restaurant = new Restaurant();
		Restaurant.setName("Favourites");
		Restaurant.setDescription("A sample Restaurant");
		addRestaurant(Restaurant);

		// Add Dishs to Restaurants
		addDish(jaRestaurant.getId(), d1.getId());
		addDish(jaRestaurant.getId(), d2.getId());
		addDish(jaRestaurant.getId(), d4.getId());
		addDish(jaRestaurant.getId(), d3.getId());

		addDish(Restaurant.getId(), d3.getId());
		addDish(Restaurant.getId(), d5.getId());
	}

	// Restaurant related operations
	@Override
	public void addRestaurant(Restaurant p) {
		String id = "R" + index++;
		p.setId(id);
		RestaurantMap.put(id, p);
	}

	@Override
	public Collection<Restaurant> getAllRestaurant() {
		return RestaurantMap.values();
	}

	@Override
	public Restaurant getRestaurant(String id) {
		return RestaurantMap.get(id);
	}

	@Override
	public void updateRestaurant(Restaurant p) {
		RestaurantMap.put(p.getId(), p);
	}

	@Override
	public void deleteRestaurant(String id) {
		RestaurantMap.remove(id);
	}

	@Override
	public void addDish(String RestaurantId, String DishId) {
		Restaurant Restaurant = getRestaurant(RestaurantId);
		Restaurant.addDish(DishMap.get(DishId));
	}

	@Override
	public Collection<Dish> getAll(String RestaurantId) {
		return getRestaurant(RestaurantId).getDish();
	}

	@Override
	public void removeDish(String RestaurantId, String DishId) {
		getRestaurant(RestaurantId).deleteDish(DishId);
	}

	// Dish related operations

	@Override
	public void addDish(Dish s) {
		String id = "d" + index++;
		s.setId(id);
		DishMap.put(id, s);
	}

	@Override
	public Collection<Dish> getAllDishs() {
		return DishMap.values();
	}

	@Override
	public Dish getDish(String DishId) {
		return DishMap.get(DishId);
	}

	@Override
	public void updateDish(Dish s) {
		Dish Dish = DishMap.get(s.getId());
		Dish.setName(s.getName());
		Dish.setType(s.getType());
	}

	@Override
	public void deleteDish(String DishId) {
		DishMap.remove(DishId);
	}

}
