package org.unicaen.GoodDealsws.model;
/**
 * the client entity
 * @author emad
 *
 */
public class Clients {

	
	private int id;

	private String firstName;
	private String lastName;
	private String Password;
	private String email;
	private String token;
	//default constructor 
	public Clients(){}

	
	public Clients(int id, String firstName, String password, String email, String token,String lastName) {
		super();
		this.id = id;
	
		this.firstName = firstName;
		Password = password;
		this.email = email;
		this.token = token;
		this.lastName=lastName;
	}


	//getters and setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
