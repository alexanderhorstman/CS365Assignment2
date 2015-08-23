package data;

import java.util.ArrayList;
import java.util.List;

public class UserGroup  implements User {
	
	private List<User> users = new ArrayList<User>();
	private String name;
	
	public UserGroup(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<User> getUser() {
		return users;
	}

}
