package data;

import java.util.ArrayList;
import java.util.List;

//class that models a group of Users
public class UserGroup  implements User {
	
	private List<User> users = new ArrayList<User>(); //list of all user in the group
	private List<UserGroup> groups = new ArrayList<UserGroup>(); //list of all groups in this group
	private String name; //the name of the group
	
	public UserGroup(String name) {
		this.name = name;
	}
	
	//adds a User to the users list
	public void addUser(User user) {
		users.add(user);
	}
	
	//adds a UserGroup to the groups list
	public void addGroup(UserGroup group) {
		groups.add(group);
	}
	
	public String getName() {
		return name;
	}
	
	public List<User> getUsers() {
		return users;
	}

}
