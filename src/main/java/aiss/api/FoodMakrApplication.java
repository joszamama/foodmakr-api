package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import aiss.api.resources.RestaurantResource;
import aiss.api.resources.DishResource;


public class FoodMakrApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	// Loads all resources that are implemented in the application
	// so that they can be found by RESTEasy.
	public FoodMakrApplication() {

		singletons.add(RestaurantResource.getInstance());
		singletons.add(DishResource.getInstance());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
