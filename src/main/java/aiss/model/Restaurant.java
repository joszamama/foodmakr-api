package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

	private String id;
	private String name;
	private String description;
	private List<Dish> Dish;

	public Restaurant() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Dish> getDish() {
		return Dish;
	}

	public void setDish(List<Dish> Dish) {
		this.Dish = Dish;
	}

	public Dish getDish(String id) {
		if (Dish == null)
			return null;

		Dish song = null;
		for (Dish s : Dish)
			if (s.getId().equals(id)) {
				song = s;
				break;
			}

		return song;
	}

	public void addDish(Dish s) {
		if (Dish == null)
			Dish = new ArrayList<Dish>();
		Dish.add(s);
	}

	public void deleteDish(Dish s) {
		Dish.remove(s);
	}

	public void deleteDish(String id) {
		Dish s = getDish(id);
		if (s != null)
			Dish.remove(s);
	}

}
