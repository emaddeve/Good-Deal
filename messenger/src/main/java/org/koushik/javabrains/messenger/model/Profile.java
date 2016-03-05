package org.koushik.javabrains.messenger.model;



import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Profile {
	
	private long id;

	private String firstName;


	
	public Profile(){}
	 
	
	public Profile(long id, String firstName) {
		super();
		this.id = id;
		
		this.firstName = firstName;
		
		
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	
	
	
}
