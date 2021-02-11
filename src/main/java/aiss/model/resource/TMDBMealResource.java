package aiss.model.resource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.TMDBMeal.MealSearch;

public class TMDBMealResource {

	private static final Logger Log = Logger.getLogger(TMDBMealResource.class.getName());

	public MealSearch getMealInfo(String meal) throws UnsupportedEncodingException {

		String uri = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + URLEncoder.encode(meal, "UTF-8");

		Log.log(Level.FINE, "TMDB getMealInfo() URI: " + uri);

		ClientResource cr2 = new ClientResource(uri);
		MealSearch mealSearch = cr2.get(MealSearch.class);

		return mealSearch;
	}
}
