package org.koushik.javabrains.messenger.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType
@XmlRootElement	
public class Message {

	private long id;
	private String message;
	private Date created;
	private String autor;
	private String image;
	File file = new File("/home/emad/Desktop/Desktop/img/emadooo.jpg");
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public File getFile() {
		return file;
	}


	
	 
	public Message(){}
	
	public Message(long id, String message, String autor,String image) {
		super();
		this.id = id;
		this.message = message;
		this.autor = autor;
		this.created = new Date();
		this.image = image;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
