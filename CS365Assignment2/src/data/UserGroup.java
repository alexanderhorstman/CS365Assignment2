package data;

import java.util.ArrayList;
import java.util.List;

public class UserGroup  implements User {
	
	private List<User> users = new ArrayList<User>();
	private List<UserGroup> groups = new ArrayList<UserGroup>();
	private String name;
	
	public UserGroup(String name) {
		this.name = name;
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

}
