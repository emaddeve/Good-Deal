package org.unicaen.dnr2i.GoodDealsWS.resources;

import java.awt.Point;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.unicaen.dnr2i.GoodDealsWS.model.OfferManager;
import org.unicaen.dnr2i.GoodDealsWS.model.Offers;
import org.unicaen.dnr2i.GoodDealsWS.service.OffersService;

@Path("/offers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OffersResources {
	OfferManager of = new OfferManager();
//	OffersService offerService = new OffersService();
	/*
	@GET
	public List<Offers> getOffers(@QueryParam("category") String category,
									@QueryParam("longitude") double longitude,
									@QueryParam("latitude") double latitude,
									 @QueryParam("desiredLoction") double desiredLocation) {
		/*
		if(category != null)
			return offerService.getAllOffersByCategory(category);
		if(desiredLocation >0 && longitude >0 && latitude >0)
			return offerService.getAllOffersByLocation(longitude,latitude, desiredLocation);
			
			
		return offerService.getAllOffers();
	}
	*/
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addOffer() throws IOException {
		of.createAndStoreOffer("POINT(-71.064544 42.28787)");
		return "done";
	}
	


}
