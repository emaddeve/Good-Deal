package org.unicaen.dnr2i.GoodDealsWS.model;

import java.awt.Point;



import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author emad
 *
 */
@XmlAccessorType
@XmlRootElement	
public class Offers {
	
	private long id;
	private String name;
	private String description;
	private String imageString;
	private String category;
	private double longitude;
	private double latitude;
	
	public Offers(){}
	
	public Offers(String name, String description, String imageString,String category,double longitude,double latitude) {
		super();
		this.name = name;
		this.description = description;
		this.imageString = imageString;
		this.longitude = longitude;
		this.latitude = latitude;
		this.category = category;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
