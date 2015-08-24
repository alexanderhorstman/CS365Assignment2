package data;

import java.util.Observable;
import java.util.Observer;

//interface that is used to model a single user, not a user group
//used in the Admin class and NormalUser class
public interface SingleUser extends User, Observer{
	
	public void update(Observable user, Object arg);

}
