package org.unicaen.GoodDealsws.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import com.vividsolutions.jts.geom.Point;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.unicaen.GoodDealsws.model.Offers;
/**
 * 
 * @author emad
 *
 */
public class OffersService {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	byte[] bytearray;

/**
 * static method for creating an Instance of SessionFactory
 * @return instance of SessionFactory
 * @throws IOException 
 */
	
	
	
	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}


	/**
	 * method return list of offers depends on their category
	 * @param category category of the offer
	 * @return list of Offers
	 */
	public List<Offers> getOfferByCategory(String category){
		List<Offers> list=null;
		
		Session session = createSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Query query = session.createQuery("FROM Offers O WHERE O.category = :category ");
	         query.setParameter("category", category);
	          list = query.list();
	       //  list = (List<Offers>) session.createQuery("FROM Offers O WHERE O.category= "+category).list();
	         
	      }catch (HibernateException e) {
	          if (tx!=null) tx.rollback();
	          e.printStackTrace(); 
	       }finally {
	          session.close(); 
	       }
		return list;
	}
	
	
	/**
	 * method return specific offer corresponding to the id param
	 * @param id the identifier of the offer
	 * @return object of class Offers
	 */
	public Offers getOfferById(int id){
		Offers offer = null;
	
		Session session = createSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	        offer= (Offers) session.get(Offers.class, id) ;
	
	      }catch (HibernateException e) {
	          if (tx!=null) tx.rollback();
	          e.printStackTrace(); 
	       }finally {
	          session.close(); 
	       }
	      return offer;
		
	}
	
	/**
	 * method return all offers in the DB
	 * @return List of Offers
	 */
	public List<Offers> getAllOffers() {
		List<Offers> offer=null;
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx= session.beginTransaction();
			 offer = (List<Offers>) session.createQuery("From Offers").list();
		}catch (HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
			
		}finally{
			session.close();
		}

		return offer;
	}
	
	/**
	 * method to add offer to the DB
	 * @param offer the object to be insert to the DB
	 */
	public void  addOffer(Offers offer){
	
		//Offers f = new Offers("emad","sfsf","sfsff","sdfjsl",500.0,500.0);
		 Session session = createSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	          tx = session.beginTransaction();
	          session.save(offer); 
	          tx.commit();
	          
	          
	          
	          
	 }catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace(); 
     }finally {
        session.close(); 
     }
	     
	}
	
	public List<Offers> getNearOffers(double prefDistance,double longitude,double latitude){
		
		List<Offers> offer=new ArrayList<Offers>();
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx= session.beginTransaction();
		
			
			List<Offers> offers = (List<Offers>) session.createQuery("From Offers").list();
			 for(Offers f :offers){
				double dis= getDistance(latitude,longitude,f.getLatitude(),f.getLongitude());
				if(dis<=prefDistance){
					 offer.add(f);
				}
			 }
				}catch (HibernateException e){
					if(tx!=null) tx.rollback();
					e.printStackTrace();
					
				}finally{
					session.close();
				}
		return offer;
		
		
	}
	
	
	public double getDistance(double lat1, double lon1, double lat2, double lon2)
	{
		double R = 6371; // km
		double dLat = Math.toRadians((lat2-lat1));
		double dLon = Math.toRadians((lon2-lon1));
		 lat1 = Math.toRadians(lat1);
		 lat2 = Math.toRadians(lat2);
		 
		 double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			        Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
		 
		 double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		 
		double d = R * c;

	    return d;
	}
	
	public List<Offers> getOffersCD(double prefDistance,double longitude,double latitude,String category){
		List<Offers> list = new ArrayList<Offers>();
		List<Offers> dislist = getNearOffers(prefDistance,longitude,latitude);
		
		for(Offers f : dislist){
			if(f.getCategory().equalsIgnoreCase(category))
				list.add(f);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
