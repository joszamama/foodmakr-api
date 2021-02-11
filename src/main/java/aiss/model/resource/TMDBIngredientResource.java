package aiss.model.resource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.TMDBIngredient.IngredientSearch;

public class TMDBIngredientResource {

	private static final Logger Log = Logger.getLogger(TMDBIngredientResource.class.getName());

	public IngredientSearch getMealsWithIngredient(String ingredient) throws UnsupportedEncodingException {

		String uri = "https://www.themealdb.com/api/json/v1/1/filter.php?i=" + URLEncoder.encode(ingredient, "UTF-8");

		Log.log(Level.FINE, "TMDB getMealsWithIngredient() URI: " + uri);

		ClientResource cr1 = new ClientResource(uri);
		IngredientSearch ingSearch = cr1.get(IngredientSearch.class);

		return ingSearch;
	}
}
