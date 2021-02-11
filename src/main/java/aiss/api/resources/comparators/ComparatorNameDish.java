package aiss.api.resources.comparators;

import java.util.Comparator;
import aiss.model.Dish;

public class ComparatorNameDish implements Comparator<Dish> {

	@Override
	public int compare(Dish s1, Dish s2) {
		return s1.getName().compareTo(s2.getName());
	}

}
