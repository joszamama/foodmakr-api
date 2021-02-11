package aiss.model.resourcetest;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.TMDBIngredient.IngredientSearch;
import aiss.model.resource.TMDBIngredientResource;

public class TMDBIngredientResourceTest {

	@Test
	public void getMealsWithIngredients() throws UnsupportedEncodingException {
		String ingredient = "Quinoa";
		TMDBIngredientResource tmdb1 = new TMDBIngredientResource();
		IngredientSearch ingredientResults = tmdb1.getMealsWithIngredient(ingredient);

		assertNotNull("The search returned null", ingredientResults);
		assertNotNull("The search returned null", ingredientResults.getMeals());
		assertFalse("The number of meals with " + ingredient + " is zero", ingredientResults.getMeals().size() == 0);

		System.out.println(
				"The search for " + ingredient + " returned " + ingredientResults.getMeals().size() + " meals.");

	}

}
