package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Admin implements User, Observer {
	
	private List<User> users = new ArrayList<User>();
	private List<UserGroup> groups = new ArrayList<UserGroup>();
	private List<Message> messages = new ArrayList<Message>(); 
	
	public String name = "Admin";
	
	public String getName() {
		return name;
	}

	@Override
	//updates the overall message list with the Message arg
	public void update(Observable user, Object arg) {
		
		
	}
 
}
