package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ui.NormalUserUiWindow;

//class that models the normal user of the "Twitter" app
public class NormalUser extends Observable implements SingleUser{
	
	private String name; //name of the user
	private List<User> followers = new ArrayList<User>(); //list of users that are following this user
	private List<User> following = new ArrayList<User>(); //list of users that this user is following
	private List<Message> newsFeed = new ArrayList<Message>(); //list of messages that will appear in the user's news feed
	private NormalUserUiWindow uiWindow; //the UI window that was created for this user
										 //needed to allow the UI window to update when a new message should be posted
										 //to the user's news feed
	
	public NormalUser(String name, AdminUser admin) {
		this.name = name;
		//adds itself as an observer to automatically receive updates to the news feed when something
		//is posted
		addObserver(this);
		//registers the admin as an observer to allow the admin to keep track of all posted messages
		addObserver(admin);
	}

	public String getName() {
		return name;
	}

	@Override
	//updates the newsFeed list with the Message arg
	public void update(Observable user, Object arg) {
		if(arg instanceof Message) {
			newsFeed.add(0, (Message) arg);
			if(uiWindow != null) {
				uiWindow.redraw();
			}			
		}
	}
	
	//adds the NormalUser to the list of followers and registers that user as an observer
	private void addObserver(NormalUser user) {
		followers.add(user);
		super.addObserver(user);
	}

	public List<Message> getNewsFeed() {
		return newsFeed;
	}
	
	public List<User> getFollowing() {
		return following;
	}
	
	//adds the NormalUser to the list of users that this user is following
	//also registers this user as an observer to the NormalUser that was passed
	public void follow(NormalUser user) {
		following.add(user);
		user.addObserver(this);
	}
	
	//posts a message, and notifies all of this user's observers (followers)
	public void post(Message message) {
		setChanged();
		notifyObservers(message);
	}
	
	//sets the UI window that is being used to show this users information
	public void setUi(NormalUserUiWindow window) {
		uiWindow = window;
	}

}
