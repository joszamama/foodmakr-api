package aiss.api.resources.comparators;

import java.util.Comparator;
import aiss.model.Dish;

public class ComparatorTypeDish implements Comparator<Dish> {

	@Override
	public int compare(Dish s1, Dish s2) {
		return s1.getType().compareTo(s2.getType());
	}

}
