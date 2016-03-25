package org.unicaen.GoodDealsws.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.unicaen.GoodDealsws.model.Clients;

/**
 * ClientsService class determine all the method needed to make operation on the
 * clients entity in the DB
 * 
 * @author emad
 *
 */
public class ClientsService {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	/**
	 * method for generating a SessionFactory
	 * 
	 * @return SessionFactory object
	 */
	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	/**
	 * method to check if the client with specific username and password exist
	 * in the DB
	 * 
	 * @param email
	 *            of the client
	 * @param pass
	 *            password of the client
	 * @return boolean true if the client exist.
	 */
	public boolean auth(String email, String pass) {
		Session session = createSessionFactory().openSession();
		boolean b = false;
		List<Clients> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = (List<Clients>) session.createQuery("FROM Clients").list();
			for (Clients c : list) {
				if (c.getEmail().equalsIgnoreCase(email) && c.getPassword().equals(pass)) {
					b = true;
				}
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return b;
	}

	/**
	 * an method for registering a client entity in the DB
	 * 
	 * @param client
	 *            the client to be registered in the DB
	 * @return the id of the client in DB
	 */
	public boolean signup(Clients client) {
		Session session = createSessionFactory().openSession();
		int id = 0;
		Transaction tx = null;
		if(exist(client.getEmail())){
			return false;
		}else{
		try {
			tx = session.beginTransaction();
			id = (Integer) session.save(client);

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		}
		return true;
	}

	/**
	 * method returning the client id based on his username and password
	 * 
	 * @param username
	 *            of the client
	 * @param pass
	 *            of the client
	 * @return the id of the client
	 */
	public int getClientId(String email, String pass) {
		Session session = createSessionFactory().openSession();
		int id = 0;
		List<Clients> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = (List<Clients>) session.createQuery("FROM Clients").list();
			for (Clients c : list) {
				if (c.getEmail().equalsIgnoreCase(email) && c.getPassword().equals(pass)) {
					id = c.getId();
				}
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public List<Clients> getClients() {

		Session session = createSessionFactory().openSession();
		List<Clients> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = (List<Clients>) session.createQuery("FROM Clients").list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List<String> getclientinfo(String token) {
		Session session = createSessionFactory().openSession();
		String email;
		String password;
		List<Clients> list = null;
		List<String> info = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = (List<Clients>) session.createQuery("FROM Clients").list();
			for (Clients c : list) {
				if (c.getToken().equalsIgnoreCase(token)) {
					email = c.getEmail();
					password = c.getPassword();
					info.add(email);
					info.add(password);
				}
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return info;
	}
	
	public boolean exist(String email){
		Session session = createSessionFactory().openSession();
		List<Clients> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			list = (List<Clients>) session.createQuery("FROM Clients").list();
			for(Clients c : list){
				if( c.getEmail().equalsIgnoreCase(email))
				return true;
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
}
