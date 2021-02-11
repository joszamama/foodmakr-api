package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.TMDBIngredient.IngredientSearch;

import aiss.model.resource.TMDBIngredientResource;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SearchController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String query = request.getParameter("searchQuery");
		RequestDispatcher rd = null;

		// Search for meals in TMDB
		log.log(Level.FINE, "Searching meals with the ingredient: " + query);
		TMDBIngredientResource tmdb1 = new TMDBIngredientResource();
		IngredientSearch ingredientResults = tmdb1.getMealsWithIngredient(query);

		if (ingredientResults != null) {
			rd = request.getRequestDispatcher("/successIngredient.jsp");
			request.setAttribute("ingredients", ingredientResults.getMeals());
		} else {
			log.log(Level.SEVERE, "TMDB Ingredient object: " + ingredientResults);
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
