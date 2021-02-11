package aiss.model.resourcetest;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.resource.LocationResource;

public class LocationResourceTest {

	@Test
	public void test() throws UnsupportedEncodingException {

		LocationResource loc = new LocationResource();
		assertNotNull("Fallo en el resultado", loc.getLocation());
		
		System.out.println(loc.getLocation().getCity());
		System.out.println(loc.getLocation().getCountryName());
	}

}
