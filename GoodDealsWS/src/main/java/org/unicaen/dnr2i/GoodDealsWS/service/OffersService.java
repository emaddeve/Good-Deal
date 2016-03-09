package org.unicaen.dnr2i.GoodDealsWS.service;


import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;


import org.unicaen.dnr2i.GoodDealsWS.database.DatabaseClass;
import org.unicaen.dnr2i.GoodDealsWS.model.Offers;

//import com.vividsolutions.jts.geom.Point;


import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author emad
 *
 */
public class OffersService {
	private static SessionFactory factory; 
	double longitude=300;
	double latitude=500;

	

	Map<Long, Offers> offers = DatabaseClass.getOffers();

	public OffersService() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
			String encoded = Base64.encodeFromFile("/home/emad/Desktop/Desktop/img/emadooo.jpg");
			
			offers.put(1l, new Offers("RedBull", "50% off", encoded, "Drinks",longitude,latitude));

			offers.put(3l, new Offers("orange", "20% off", encoded, "Fruits",longitude,latitude));
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
	public Offers getOfferById(){
		Offers f = new Offers();
	
		Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	       List<Offers>   of= (List<Offers>) session.createQuery("FROM Offers").list(); 
	       for(Offers s:of){
	    	   if (true){
	    		   f=s;
	    	   }
	       }
	        
	      
	        	
	          
	         
	          
	      }catch (HibernateException e) {
	          if (tx!=null) tx.rollback();
	          e.printStackTrace(); 
	       }finally {
	          session.close(); 
	       }
	      return f;
		
	}
		/*
		Offers offer = new Offers();
		for(Offers of : offers.values())
			if(of.getId()==id)
				offer=of;
		return offer;
		
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
	public void  addOffer(){
	
		Offers f = new Offers("emad","sfsf","sfsff","sdfjsl",500.0,500.0);
		 Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	          tx = session.beginTransaction();
	          session.save(f); 
	          tx.commit();
	          
	          
	          
	          
	 }catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace(); 
     }finally {
        session.close(); 
     }
	     
	}
}
