package org.unicaen.GoodDealsws.model;

import java.util.Date;


/**
 * 
 * @author emad
 *
 */
public class Offers {
	private int id;	
	private String name;	
	private String description;	
	private String imageString;	
	private String category;	
	private double longitude;	
	private double latitude;
	private String magasin;
	private Date datefin;
	
	public Offers(){}
	
	public Offers(String name, String description, String imageString,String category,double longitude,double latitude,String magasin,Date datefin) {
		super();
		
		this.name = name;
		this.description = description;
		this.imageString = imageString;
		this.category = category;
		this.longitude=longitude;
		this.latitude=latitude;
		this.datefin = datefin;
		this.magasin = magasin;
	
		
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMagasin() {
		return magasin;
	}

	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
}
