package org.unicaen.GoodDealsws.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.*;
import javax.servlet.http.HttpServletRequest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.unicaen.GoodDealsws.model.Base64;
import org.unicaen.GoodDealsws.model.Friends;
import org.unicaen.GoodDealsws.model.FriendsOffers;
import org.unicaen.GoodDealsws.model.Offers;
import org.unicaen.GoodDealsws.service.ClientsService;
import org.unicaen.GoodDealsws.service.OffersService;

/**
 * offers resources determine the path and param for the requests from the clients
 * *path /offers 
 *return the result in json format
 *consume the request in json format
 *
 * @author emad
 *  
 */
@Path("/offers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OffersResources {
	public static final String AUTHENTICATION_HEADER = "Authorization";
	OffersService offerService = new OffersService();
	ClientsService clientsService = new ClientsService();

	/**
	 * method beeing called when make a GET request to the /offers path
	 * @param category category of the offer
	 * @param prefDistance the max distance between the offer and the client
	 * @param longitude longitude of the client
	 * @param latitude longitude of the client
	 * @return list of offers
	 */
	@GET
	public List<Offers> getOffers(@QueryParam("category") String category,
			@QueryParam("prefDistance") double prefDistance, @NotNull @QueryParam("longitude") double longitude
			, @QueryParam("latitude") double latitude) {

		if (category != null && !category.equalsIgnoreCase("All")) {
			if (prefDistance > 0)
				return offerService.getOffersCD(prefDistance, longitude, latitude, category);
			return offerService.getOfferByCategory(category);
		}

		else if (prefDistance > 0) {
			return offerService.getNearOffers(prefDistance, longitude, latitude);
		}
		else
		return offerService.getAllOffers();
	}

	@GET
	@Path("/{offerId}")
	public Offers getOfferById(@PathParam("offerId") int id) {

		return offerService.getOfferById(id);
	}
	
	@POST
	@Path("/friends")
	public List<FriendsOffers> getFriendsOffers(ArrayList<String> f) {

		return offerService.getface(f);
	}
/**
 * method to be called when making a Post request to the path /offers/add
 * @param request the HttpServletRequest 
 * @param offer the offer to be add to the DB
 * @return the status code of the entity offer
 * @throws IOException
 */
	@POST
	@Path("/add")
	public Response addOffer(@Context HttpServletRequest request, Offers offer) throws IOException {
		String d="false";
		String s = request.getHeader(AUTHENTICATION_HEADER);

		final String encodedUserPassword = s.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String email = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		offer.setUserid(clientsService.getClientId(email, password));
		offerService.addOffer(offer);
		 return Response.status(Status.CREATED).entity(offer).build();

	}
	

}
