package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Restaurant;

public class ComparatorNameRestaurantReversed implements Comparator<Restaurant> {

	@Override
	public int compare(Restaurant p1, Restaurant p2) {
		return p2.getName().compareTo(p1.getName());
	}

}
