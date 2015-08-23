package data;

import java.util.Observable;
import java.util.Observer;

public interface SingleUser extends User, Observer{
	
	public void update(Observable user, Object arg);

}
