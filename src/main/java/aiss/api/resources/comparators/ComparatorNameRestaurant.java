package aiss.api.resources.comparators;

import java.util.Comparator;

import aiss.model.Restaurant;

public class ComparatorNameRestaurant implements Comparator<Restaurant> {

	@Override
	public int compare(Restaurant p1, Restaurant p2) {
		return p1.getName().compareTo(p2.getName());
	}

}
