package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class UserGroup  implements User {
	
	private List<User> users = new ArrayList<User>();
	private String name;
	
	public UserGroup(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}

}
