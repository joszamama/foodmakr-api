package aiss.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import aiss.api.resources.comparators.ComparatorNameDish;
import aiss.api.resources.comparators.ComparatorNameDishReversed;
import aiss.api.resources.comparators.ComparatorTypeDish;
import aiss.api.resources.comparators.ComparatorTypeDishReversed;
import aiss.model.Dish;
import aiss.model.repository.MapRestaurantRepository;
import aiss.model.repository.RestaurantRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Path("/dish")
public class DishResource {

	public static DishResource _instance = null;
	RestaurantRepository repository;

	private DishResource() {
		repository = MapRestaurantRepository.getInstance();
	}

	public static DishResource getInstance() {
		if (_instance == null)
			_instance = new DishResource();
		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<Dish> getAll(@QueryParam("order") String order, @QueryParam("q") String q) {
		List<Dish> result = new ArrayList<Dish>();

		for (Dish d : repository.getAllDishs()) {
			if (q == null || d.getType().toLowerCase().contains(q.toLowerCase())
					|| (d.getName() != null && d.getName().toLowerCase().contains(q.toLowerCase()))
					|| (d.getId() != null && d.getId().toLowerCase().contains(q.toLowerCase())))
				result.add(d);
		}

		if (order != null) { // Order results
			if (order.equals("name")) {
				Collections.sort(result, new ComparatorNameDish());
			} else if (order.equals("-name")) {
				Collections.sort(result, new ComparatorNameDishReversed());
			} else if (order.equals("type")) {
				Collections.sort(result, new ComparatorTypeDish());
			} else if (order.equals("-type")) {
				Collections.sort(result, new ComparatorTypeDishReversed());
			} else {
				throw new BadRequestException("The order parameter must be 'name', '-name, 'type' or '-type'.");
			}
		}

		return result;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Dish get(@PathParam("id") String dId) {
		Dish list = repository.getDish(dId);

		if (list == null) {
			throw new NotFoundException("The dish with id=" + dId + " was not found");
		}

		return list;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addDish(@Context UriInfo uriInfo, Dish d) {
		if (d.getName() == null || "".equals(d.getName()))
			throw new BadRequestException("The name of the dish must not be null");

		repository.addDish(d);

		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(d.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(d);
		return resp.build();
	}

	@PUT
	@Consumes("application/json")
	public Response updateDish(Dish d) {
		Dish oldD = repository.getDish(d.getId());
		if (oldD == null) {
			throw new NotFoundException("The dish with id=" + d.getId() + " was not found");
		}

		// Update title
		if (d.getName() != null)
			oldD.setName(d.getName());

		// Update album
		if (d.getType() != null)
			oldD.setType(d.getType());

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeDish(@PathParam("id") String dId) {
		Dish toberemoved = repository.getDish(dId);
		if (toberemoved == null)
			throw new NotFoundException("The dish with id=" + dId + " was not found");
		else
			repository.deleteDish(dId);

		return Response.noContent().build();
	}

}