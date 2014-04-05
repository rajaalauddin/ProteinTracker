package com.rajaalauddin;

import java.util.Date;

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
		user.addHistory(new UserHistory(new Date(), "Set name to Torres"));
		user.getProteinData().setGoal(250);
		user.addHistory(new UserHistory(new Date(), "Set goal to 250"));
		session.save(user);
		
		session.getTransaction().commit();
		
		session.beginTransaction();
		
		User loadedUser = (User) session.get(User.class, 1);
		System.out.println(loadedUser.getName());
		System.out.println(loadedUser.getProteinData().getGoal());
		
		for(UserHistory history: loadedUser.getHistory()) {
			System.out.println(history.getEntryTime() + " " + history.getEntry());
		}
		
		loadedUser.getProteinData().setTotal(loadedUser.getProteinData().getTotal() + 50);
		loadedUser.addHistory(new UserHistory(new Date(), "Added 50 protein"));
		
		session.getTransaction().commit();
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}

}
