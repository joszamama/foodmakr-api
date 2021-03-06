package aiss.model.resource;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.google.maps.GoogleMapsSearch;

public class MapResource {

	private static final String MAPS_API_KEY = "AIzaSyDZoKEdcapvOACB9iE95Qnxi8vukkqmjDM"; // TODO: Change this API KEY
																							// for your personal Key
	private static final Logger log = Logger.getLogger(MapResource.class.getName());

	public GoogleMapsSearch getPlaces(Double latitud, Double longitud) throws UnsupportedEncodingException {

		String uri = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + latitud.toString()
				+ "," + longitud.toString() + "&radius=1500&type=food&keyword=supermarket&key=" + MAPS_API_KEY;

		log.log(Level.FINE, "Places nearby:" + uri);
		ClientResource cr = new ClientResource(uri);
		GoogleMapsSearch result = cr.get(GoogleMapsSearch.class);
		return result;

	}

}
