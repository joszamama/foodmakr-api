package aiss.api.resources.comparators;

import java.util.Comparator;
import aiss.model.Dish;

public class ComparatorTypeDishReversed implements Comparator<Dish> {

	@Override
	public int compare(Dish s1, Dish s2) {
		return s2.getType().compareTo(s1.getType());
	}

}
