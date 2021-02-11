package aiss.model.resourcetest;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.resource.MapResource;

public class MapResourceTest {

	@Test
	public void test() throws UnsupportedEncodingException {

		MapResource maps = new MapResource();
		assertNotNull("Error al recibir el JSON", maps.getPlaces(37.38383102416992, -5.980609893798828));
		System.out.println(maps.getPlaces(37.38383102416992, -5.980609893798828));
	}

}
