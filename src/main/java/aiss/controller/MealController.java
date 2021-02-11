package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.TMDBMeal.MealSearch;
import aiss.model.resource.TMDBMealResource;
import aiss.model.resource.YouTubeResource;
import aiss.model.youtube.SearchVideos;


/**
 * Servlet implementation class SearchController
 */
public class MealController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(MealController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MealController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String meal = request.getParameter("meal");
		RequestDispatcher rd = null;
		YouTubeResource youtube = new YouTubeResource();
		// Search for a meal in TMDB
		log.log(Level.FINE, "Searching meal: " + meal);
		TMDBMealResource tmdb2 = new TMDBMealResource();
		MealSearch mealResults = tmdb2.getMealInfo(meal);
		// Search for videos in Youtube
		log.log(Level.INFO, "Buscando para YouTube vídeos de " + meal);
		SearchVideos youtubeResults = youtube.getVideo(meal);

		if (mealResults != null || youtubeResults != null) {
			rd = request.getRequestDispatcher("/successMeal.jsp");
			request.setAttribute("meal", mealResults.getMeals());
			request.setAttribute("videos", youtubeResults);
		} else {
			log.log(Level.SEVERE, "TMDB Meal object: " + mealResults);
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
