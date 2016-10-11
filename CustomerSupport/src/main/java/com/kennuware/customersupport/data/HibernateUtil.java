package com.kennuware.customersupport.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	// this static method is supposed to be run on initialization to create the session factory
	static {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	 public static SessionFactory getSessionFactory() {
		 return sessionFactory;
	 }
	 
	 public static void shutdown() {
		 // Close caches and connection pools
		 getSessionFactory().close();
	 }

}
