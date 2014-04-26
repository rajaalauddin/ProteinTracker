package com.rajaalauddin;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtilities {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	static {
		try {
			Configuration conf = new Configuration().setInterceptor(new AuditInterceptor()).configure();

			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
  			sessionFactory = conf.buildSessionFactory(serviceRegistry);
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
