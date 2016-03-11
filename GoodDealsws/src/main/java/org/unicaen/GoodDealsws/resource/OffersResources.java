package org.unicaen.GoodDealsws.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unicaen.GoodDealsws.model.Offers;

import org.unicaen.GoodDealsws.service.OffersService;

@Path("/offers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OffersResources {

	OffersService offerService = new OffersService();

	@GET
	public List<Offers> getOffers(@QueryParam("category") String category,
			@QueryParam("prefDistance") double prefDistance, @NotNull @QueryParam("longitude") double longitude,
			@NotNull @QueryParam("latitude") double latitude, @QueryParam("desiredLoction") double desiredLocation) {

		if (category != null) {
			if (prefDistance > 0)
				return offerService.getOffersCD(prefDistance, longitude, latitude, category);
			return offerService.getOfferByCategory(category);
		}

		if (prefDistance > 0) {
			return offerService.getNearOffers(prefDistance, longitude, latitude);
		}

		return offerService.getAllOffers();
	}

	@GET
	@Path("/{offerId}")
	public Offers getOfferById(@PathParam("offerId") int id) {

		return offerService.getOfferById(id);
	}

	@POST
	public Response addOffer(Offers offer) throws IOException {

		offerService.addOffer(offer);
		return Response.ok("done").build();

	}

}
