package org.unicaen.dnr2i.GoodDealsWS.database;

import java.util.HashMap;
import java.util.Map;

import org.unicaen.dnr2i.GoodDealsWS.model.Offers;
import org.unicaen.dnr2i.GoodDealsWS.model.Clients;

/**
 * 
 * @author emad
 *
 */
public class DatabaseClass {
	private static Map<Long,Offers> offers = new HashMap<>();
	private static Map<String,Clients> clients = new HashMap<>();
	
	public static Map<Long,Offers> getOffers(){
		return offers;
	}
	public static Map<String,Clients> getClients(){
		return clients;
	}
}
