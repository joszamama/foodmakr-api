package aiss.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aiss.model.google.maps.Result;
import aiss.model.resource.LocationResource;
import aiss.model.resource.MapResource;

public class GetStartedController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(GetStartedController.class.getName());

	public GetStartedController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		log.log(Level.FINE, "Searching for current Location");

		LocationResource location = new LocationResource();
		String ciudad = location.getLocation().getCity();
		Double latitud = location.getLocation().getLatitude();
		Double longitud = location.getLocation().getLongitude();

		MapResource search = new MapResource();
		List<Result> places = search.getPlaces(latitud, longitud).getResults();
		String url = "https://www.google.com/maps/embed/v1/place?q=" + latitud + "," + longitud
				+ "&key=AIzaSyDZoKEdcapvOACB9iE95Qnxi8vukkqmjDM";

		if (ciudad != null) {
			request.setAttribute("region", ciudad);
			request.setAttribute("latitud", latitud);
			request.setAttribute("longitud", longitud);
			request.setAttribute("places", places);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("successMap.jsp");

		} else {
			log.log(Level.SEVERE, "Ipstack: " + latitud);
			log.log(Level.SEVERE, "Ipstack: " + longitud);
			log.log(Level.SEVERE, "Maps: " + places);
			rd = request.getRequestDispatcher("error.jsp");
		}

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
