package org.unicaen.GoodDealsws.service;
import java.io.IOException;

import java.util.StringTokenizer;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import org.unicaen.GoodDealsws.model.Base64;

/**
 * 
 * @author emad
 *
 */
public class AuthenticationService {
	ClientsService s = new ClientsService();
	
	
	/**
	 * method return true or false depending on the Credentials who was entered by the client
	 * @param authCredentials
	 * @return
	 */
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String email = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		boolean authenticationStatus =s.auth(email, password);
		
		return authenticationStatus;
	}


}
