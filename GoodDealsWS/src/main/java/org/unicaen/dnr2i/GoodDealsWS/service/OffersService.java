package org.unicaen.dnr2i.GoodDealsWS.service;


import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;


import org.unicaen.dnr2i.GoodDealsWS.database.DatabaseClass;
import org.unicaen.dnr2i.GoodDealsWS.model.Offers;

import com.vividsolutions.jts.geom.Point;
/**
 * 
 * @author emad
 *
 */
public class OffersService {

	double longitude=300;
	double latitude=500;
	Point p1;
	Point p2;

	Map<Long, Offers> offers = DatabaseClass.getOffers();

	public OffersService() {
		try {

			String encoded = Base64.encodeFromFile("/home/emad/Desktop/Desktop/img/emadooo.jpg");
			offers.put(1l, new Offers("RedBull", "50% off", encoded, "Drinks",p1));

			offers.put(3l, new Offers("orange", "20% off", encoded, "Fruits",p2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Offers> getAllOffers() {

		return new ArrayList<Offers>(offers.values());
	}

	public List<Offers> getAllOffersByCategory(String category) {
		List<Offers> offersByCategory = new ArrayList<>();

		for (Offers offer : offers.values()) {

			if (offer.getCategory() == category)
				offersByCategory.add(offer);

		}
		return offersByCategory;

	}
/*
	public List<Offers> getAllOffersByLocation(double longitude,double latitude, double desiredLocation) {

		Point mylocation = new Point();
		Point desiredLocationPoint = new Point();
		mylocation.setLocation(longitude, latitude);
		desiredLocationPoint.setLocation(longitude+desiredLocation, latitude+desiredLocation);
		

		ArrayList<Offers> list = new ArrayList<Offers>();
		for (Offers f : offers.values()) {
			Point offerLocation = new Point();
			offerLocation.setLocation(f.getLongitude(), f.getLatitude());
			if (mylocation.distance(offerLocation.getLocation()) < desiredLocation) {
				list.add(f);
			}
			
		}
		return list;
	}
	*/
	public void addOffer(Offers offer){
		offer.setId(offers.size()+1);
		offers.put(offer.getId(), offer);
	}

}
