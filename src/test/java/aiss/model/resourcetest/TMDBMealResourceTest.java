package aiss.model.resourcetest;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.TMDBMeal.MealSearch;
import aiss.model.resource.TMDBMealResource;

public class TMDBMealResourceTest {

	@Test
	public void test() throws UnsupportedEncodingException {
		String meal = "Arrabiata";
		TMDBMealResource tmdb2 = new TMDBMealResource();
		MealSearch mealResults = tmdb2.getMealInfo(meal);

		assertNotNull("The search returned null", mealResults);
		assertNotNull("The search returned null", mealResults.getMeals());
		assertFalse("The number of meals with " + meal + " is zero", mealResults.getMeals().size() == 0);

		System.out.println("The search for " + meal + " returned " + mealResults.getMeals().size() + " meals.");
	}

}
