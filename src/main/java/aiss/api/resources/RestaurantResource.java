package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import aiss.api.resources.comparators.ComparatorNameRestaurant;
import aiss.api.resources.comparators.ComparatorNameRestaurantReversed;
import aiss.model.Restaurant;
import aiss.model.Dish;
import aiss.model.repository.MapRestaurantRepository;
import aiss.model.repository.RestaurantRepository;

@Path("/restaurant")
public class RestaurantResource {

	/* Singleton */
	private static RestaurantResource _instance = null;
	RestaurantRepository repository;

	private RestaurantResource() {
		repository = MapRestaurantRepository.getInstance();

	}

	public static RestaurantResource getInstance() {
		if (_instance == null)
			_instance = new RestaurantResource();
		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<Restaurant> getAll(@QueryParam("order") String order, @QueryParam("isEmpty") Boolean isEmpty,
			@QueryParam("name") String name) {
		List<Restaurant> result = new ArrayList<Restaurant>();

		for (Restaurant r : repository.getAllRestaurant()) {
			if (name == null || r.getName().equals(name)) { // Name filter
				if (isEmpty == null // Empty playlist filter
						|| (isEmpty && (r.getDish() == null || r.getDish().size() == 0))
						|| (!isEmpty && (r.getDish() != null && r.getDish().size() > 0))) {
					result.add(r);
				}
			}
		}

		if (order != null) { // Order results
			if (order.equals("name")) {
				Collections.sort(result, new ComparatorNameRestaurant());
			} else if (order.equals("-name")) {
				Collections.sort(result, new ComparatorNameRestaurantReversed());
			} else {
				throw new BadRequestException("The order parameter must be 'name' or '-name'.");
			}
		}

		return result;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Restaurant get(@PathParam("id") String id) {
		Restaurant list = repository.getRestaurant(id);

		if (list == null) {
			throw new NotFoundException("The restaurant with id=" + id + " was not found");
		}

		return list;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addRestaurant(@Context UriInfo uriInfo, Restaurant r) {
		if (r.getName() == null || "".equals(r.getName()))
			throw new BadRequestException("The name of the restaurant must not be null");

		if (r.getDish() != null)
			throw new BadRequestException("The dish property is not editable.");

		repository.addRestaurant(r);

		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(r.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(r);
		return resp.build();
	}

	@PUT
	@Consumes("application/json")
	public Response updateRestaurant(Restaurant r) {
		Restaurant oldR = repository.getRestaurant(r.getId());
		if (oldR == null) {
			throw new NotFoundException("The restaurant with id=" + r.getId() + " was not found");
		}

		if (r.getDish() != null)
			throw new BadRequestException("The dish property is not editable.");

		// Update name
		if (r.getName() != null)
			oldR.setName(r.getName());

		// Update description
		if (r.getDescription() != null)
			oldR.setDescription(r.getDescription());

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeRestaurant(@PathParam("id") String id) {
		Restaurant toberemoved = repository.getRestaurant(id);
		if (toberemoved == null)
			throw new NotFoundException("The restaurant with id=" + id + " was not found");
		else
			repository.deleteRestaurant(id);

		return Response.noContent().build();
	}

	@POST
	@Path("/{restaurantId}/{dishId}")
	@Produces("application/json")
	public Response addDishToRestaurant(@Context UriInfo uriInfo, @PathParam("restaurantId") String rId,
			@PathParam("dishId") String dId) {

		Restaurant r = repository.getRestaurant(rId);
		Dish d = repository.getDish(dId);

		if (r == null)
			throw new NotFoundException("The restaurant with id=" + rId + " was not found");

		if (d == null)
			throw new NotFoundException("The dish with id=" + dId + " was not found");

		if (r.getDish(dId) != null)
			throw new BadRequestException("The dish is already included in the restaurant.");

		repository.addDish(rId, dId);

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(rId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(r);
		return resp.build();
	}

	@DELETE
	@Path("/{restaurantId}/{dishId}")
	public Response removeDishFromRestaurant(@PathParam("restaurantId") String rId, @PathParam("dishId") String dId) {
		Restaurant r = repository.getRestaurant(rId);
		Dish d = repository.getDish(dId);

		if (r == null)
			throw new NotFoundException("The restaurant with id=" + rId + " was not found");

		if (d == null)
			throw new NotFoundException("The song with id=" + dId + " was not found");

		repository.removeDish(rId, dId);

		return Response.noContent().build();
	}
}
