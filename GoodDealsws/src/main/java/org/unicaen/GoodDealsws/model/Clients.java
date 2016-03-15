package org.unicaen.GoodDealsws.model;
/**
 * the client entity
 * @author emad
 *
 */
public class Clients {

	
	private int id;
	private String name;
	private String Password;
	private String email;
	private String Token;
	//default constructor 
	public Clients(){}
	public Clients(int id, String name, String password, String token,String email) {
		super();
		this.id = id;
		this.name = name;
		Password = password;
		Token = token;
		this.setEmail(email);
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
