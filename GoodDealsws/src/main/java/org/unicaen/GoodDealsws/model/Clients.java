package org.unicaen.GoodDealsws.model;

public class Clients {

	
	private int id;
	private String name;
	private String Password;
	private String Token;
	
	public Clients(int id, String name, String password, String token) {
		super();
		this.id = id;
		this.name = name;
		Password = password;
		Token = token;
	}
	
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
	
	
	
}
