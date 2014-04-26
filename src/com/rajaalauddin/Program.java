package com.rajaalauddin;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		
		/*
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
		// create user
		User user = new User();
		user.setName("Torres");
		user.addHistory(new UserHistory(new Date(), "Set name to Torres"));
		user.getProteinData().setGoal(250);
		user.addHistory(new UserHistory(new Date(), "Set goal to 250"));
		
		user.getGoalAlerts().add(new GoalAlert("Congratulations!"));
		user.getGoalAlerts().add(new GoalAlert("You did it!"));
		
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
		*/		
		PopulateSampleData();
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.getNamedQuery("AllGoalsAlert");
		Query queryCr = session.createQuery("update ProteinData pd set pd.total = 0");
		queryCr.executeUpdate();
		
		List<GoalAlert> alerts = query.list();
		
		for(GoalAlert alert : alerts) {
			System.out.println(alert.getMessage());
		}
		
		session.getTransaction().commit();	
		session.close();
		
		HibernateUtilities.getSessionFactory().close();
	}
	
	private static void PopulateSampleData() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
		User joe = CreateUser("Joe", 500, 50, "Good Job", "You made it");
		session.save(joe);
		
		User bob = CreateUser("Bob", 300, 20, "Taco time!");
		session.save(bob);
		
		User amy = CreateUser("Amy", 250, 200, "Yes !!");
		session.save(amy);
		session.getTransaction().commit();
		session.close();
	}
	
	private static User CreateUser(String name, int goal, int total, String ... alerts) {
		
		User user = new User();
		user.setName(name);
		user.getProteinData().setGoal(goal);
		user.addHistory(new UserHistory(new Date(), "Set goal to " + goal));
		user.getProteinData().setTotal(total);
		user.addHistory(new UserHistory(new Date(), "Set total to " + total));
		
		for(String alert : alerts) {
			user.getGoalAlerts().add(new GoalAlert(alert));
		}
		
		return user;
	}

}
