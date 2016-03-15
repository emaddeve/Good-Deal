package org.unicaen.GoodDealsws.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.unicaen.GoodDealsws.model.Clients;
import org.unicaen.GoodDealsws.model.Offers;
import org.unicaen.GoodDealsws.service.ClientsService;
/**
 * 
 * @author emad
 *
 */
@Path("/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientsResources {
	ClientsService clientsService = new ClientsService();
	
	@POST
	@Path("/add")
	public Response addOffer(Clients client) {

		clientsService.signup(client);
		return Response.status(Status.CREATED).entity(client).build();

	}
	@GET
	public List<Clients> getClients(){
		return clientsService.getClients();
	}
}
