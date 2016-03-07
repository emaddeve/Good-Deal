package org.unicaen.dnr2i.GoodDealsWS.model;



import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;




/**
 * 
 * @author emad
 *
 */
@XmlAccessorType
@XmlRootElement	
@Entity
public class Offers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @GenericGenerator(name="id", strategy = "increment")
	private long id;
	
	@Type(type="org.hibernate.spatial.GeometryType")
    private Point location;
	
	

	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name = "image")
	private String imageString;
	@Column(name = "category")
	private String category;
	
	private double longitude;
	private double latitude;
	public Offers(){}
	
	public Offers(String name, String description, String imageString,String category,Point location) {
		super();
		
		this.name = name;
		this.description = description;
		this.imageString = imageString;
		this.location = location;
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
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	
	
}
