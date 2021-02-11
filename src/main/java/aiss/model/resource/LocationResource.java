package aiss.model.resource;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.resource.ClientResource;

import aiss.model.ipstack.Ipstack;

public class LocationResource {

	private static final String APIXU_API_KEY = "d5136bedb8103ad7413d7d6886b75b76";
	private static final Logger log = Logger.getLogger(LocationResource.class.getName());

	public Ipstack getLocation() throws UnsupportedEncodingException {

		String uri = "http://api.ipstack.com/193.147.173.146?access_key=" + APIXU_API_KEY;
		log.log(Level.FINE, "Current location:" + uri);
		ClientResource cr = new ClientResource(uri);
		Ipstack result = cr.get(Ipstack.class);
		return result;
	}
}
