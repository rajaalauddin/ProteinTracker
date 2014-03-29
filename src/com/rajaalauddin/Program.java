package com.rajaalauddin;

import org.hibernate.Session;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
		// create user
		User user = new User();
		user.setName("Torres");
		user.setGoal(250);
		session.save(user);
		
		session.getTransaction().commit();
		
		session.beginTransaction();
		
		User loadedUser = (User) session.get(User.class, 1);
		System.out.println(loadedUser.getName());
		System.out.println(loadedUser.getGoal());
		
		loadedUser.setTotal(loadedUser.getTotal() + 50);
		
		session.getTransaction().commit();
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}

}
