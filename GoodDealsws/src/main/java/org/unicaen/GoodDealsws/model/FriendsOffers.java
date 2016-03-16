package org.unicaen.GoodDealsws.model;
import java.util.List;

import org.unicaen.GoodDealsws.model.Friends;
public class FriendsOffers {

	private String name;
	private List<Offers> offers;
	public FriendsOffers(){}
	
	public FriendsOffers(String name, List<Offers> offers) {
		super();
		this.name = name;
		this.offers = offers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Offers> getOffers() {
		return offers;
	}

	public void setOffers(List<Offers> offers) {
		this.offers = offers;
	}
	
	
}
