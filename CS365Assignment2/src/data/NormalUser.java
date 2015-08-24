package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ui.NormalUserUiWindow;

public class NormalUser extends Observable implements SingleUser{
	
	private String name;
	private List<User> followers = new ArrayList<User>();
	private List<User> following = new ArrayList<User>();
	private List<Message> newsFeed = new ArrayList<Message>();
	private NormalUserUiWindow uiWindow;
	
	public NormalUser(String name, AdminUser admin) {
		this.name = name;
		addObserver(this);
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
	
	public void addObserver(NormalUser user) {
		followers.add(user);
		//user.follow(this);
		super.addObserver(user);
	}

	public List<Message> getNewsFeed() {
		return newsFeed;
	}
	
	public List<User> getFollowing() {
		return following;
	}
	
	public void follow(NormalUser u) {
		following.add(u);
		u.addObserver(this);
	}
	
	public void post(Message message) {
		setChanged();
		notifyObservers(message);
	}
	
	public void setUi(NormalUserUiWindow window) {
		uiWindow = window;
	}

}
