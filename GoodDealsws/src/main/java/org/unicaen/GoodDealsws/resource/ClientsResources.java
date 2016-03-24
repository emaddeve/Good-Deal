package org.unicaen.GoodDealsws.resource;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.unicaen.GoodDealsws.model.Base64;
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
	public static final String AUTHENTICATION_HEADER = "Authorization";
	ClientsService clientsService = new ClientsService();
	
	@POST
	@Path("/add")
	public Response addClient(Clients client) {

		clientsService.signup(client);
		return Response.status(Status.CREATED).build();

	}
	@GET
	public List<Clients> getClients(){
		return clientsService.getClients();
	}
	
	@GET 
	@Path("/loginInfo")
	public List<String> loginInfo(@QueryParam("token") String token){
		return clientsService.getclientinfo(token);
	}
	
	@GET
	@Path("/verify")
	public Response login(@Context HttpServletRequest request){
		int res=0;
	
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
		 if(clientsService.auth(email,password))
			res=200;
			
		 return Response.status(res).build();
	}
}
