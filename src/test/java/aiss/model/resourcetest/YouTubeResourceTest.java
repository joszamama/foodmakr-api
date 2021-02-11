package aiss.model.resourcetest;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.resource.YouTubeResource;
import aiss.model.youtube.SearchVideos;

public class YouTubeResourceTest {

	@Test
	public void test() throws UnsupportedEncodingException {
		String search = "Chicken";
		YouTubeResource ytresource = new YouTubeResource();
		SearchVideos videoResult = ytresource.getVideo(search);
		
		assertNotNull("The search returned null", videoResult);
		assertNotNull("The search returned null", videoResult.getItems());
		assertFalse("The number of videos with " + search + " is zero", videoResult.getItems().size() == 0);

		System.out.println("The search for " + search + " returned " + videoResult.getItems().size() + " videos.");
	}

}
