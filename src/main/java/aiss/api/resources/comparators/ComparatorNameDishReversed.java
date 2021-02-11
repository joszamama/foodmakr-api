package aiss.api.resources.comparators;

import java.util.Comparator;
import aiss.model.Dish;

public class ComparatorNameDishReversed implements Comparator<Dish> {

	@Override
	public int compare(Dish s1, Dish s2) {
		return s2.getName().compareTo(s1.getName());
	}

}
