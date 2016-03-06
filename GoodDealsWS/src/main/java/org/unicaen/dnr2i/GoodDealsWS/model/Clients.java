package org.unicaen.dnr2i.GoodDealsWS.model;


/**
 * 
 * @author emad
 *
 */
public class Clients {

	private String name;
	private String lastName;
	private String password;
	
	public Clients(){}
	
	
	
	public Clients(String name, String lastName, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.password = password;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
