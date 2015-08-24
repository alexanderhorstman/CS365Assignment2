package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import control.AdminVisitor;

public class Admin implements AdminUser {
	
	private List<User> users = new ArrayList<User>(); //list of all users
	private List<UserGroup> groups = new ArrayList<UserGroup>(); //list of all groups
	private List<Message> messages = new ArrayList<Message>();  //list of all messages posted
	
	private String name = "Admin"; //singleton name
	private static Admin admin; //reference to the singleton
	
	private Admin() {
		//adds a group that serves as the root of all other groups and users
		groups.add(new UserGroup("Root"));
	}
	
	//accessor method to obtain an instance of the Admin class
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
		//if "arg" is a Message, add the message to the list of messages
		if(arg instanceof Message) {
			messages.add(0, (Message) arg);
		}
	}
	
	public void accept(AdminVisitor v) {
		v.visitAdmin(this);
	}
 
}
