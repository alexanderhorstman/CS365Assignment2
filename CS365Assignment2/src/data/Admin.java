package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Admin implements AdminUser {
	
	private List<User> users = new ArrayList<User>();
	private List<UserGroup> groups = new ArrayList<UserGroup>();
	private List<Message> messages = new ArrayList<Message>(); 
	
	private String name = "Admin";
	private static Admin admin;
	
	private Admin() {
		groups.add(new UserGroup("Root"));
	}
	
	public static AdminUser getInstance() {
		if(admin == null) {
			admin = new Admin();
		}
		return admin;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void addGroup(UserGroup group) {
		groups.add(group);
	}
	
	public String getName() {
		return name;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public List<UserGroup> getGroups() {
		return groups;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	@Override
	//updates the overall message list with the Message arg
	public void update(Observable user, Object arg) {
		if(arg instanceof Message) {
			messages.add(0, (Message) arg);
		}
		
	}
 
}
