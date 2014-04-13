package com.rajaalauddin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

	private int id;
	private String name;	
	private ProteinData proteinData = new ProteinData();
	private List<UserHistory> history = new ArrayList<UserHistory>();
	//private GoalAlert goalAlert;
	private Set<GoalAlert> goalAlerts = new HashSet<GoalAlert>();
	
	public User() {
		setProteinData(new ProteinData());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProteinData getProteinData() {
		return proteinData;
	}
	public void setProteinData(ProteinData proteinData) {
		
		this.proteinData = proteinData;
		proteinData.setUser(this);
	}
	public List<UserHistory> getHistory() {
		return history;
	}
	public void setHistory(List<UserHistory> history) {
		this.history = history;
	}
	
	public void addHistory(UserHistory historyItem) {
		historyItem.setUser(this);
		history.add(historyItem);
	}
//	public GoalAlert getGoalAlert() {
//		return goalAlert;
//	}
//	public void setGoalAlert(GoalAlert goalAlert) {
//		this.goalAlert = goalAlert;
//	}
	public Set<GoalAlert> getGoalAlerts() {
		return goalAlerts;
	}
	public void setGoalAlerts(Set<GoalAlert> goalAlerts) {
		this.goalAlerts = goalAlerts;
	}
	
}
