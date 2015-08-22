package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NormalUser extends Observable implements User, Observer{
	
	private String name;
	private List<User> followers = new ArrayList<User>();
	private List<User> following = new ArrayList<User>();
	private List<Message> newsFeed = new ArrayList<Message>();
	
	public NormalUser(String name) {
		this.name = name;
		addObserver(this);
	}

	public String getName() {
		return name;
	}

	@Override
	//updates the newsFeed list with the Message arg
	public void update(Observable user, Object arg) {
		if(arg instanceof Message) {
			newsFeed.add(0, (Message) arg);
		}
	}
	
	public void addObserver(NormalUser user) {
		followers.add(user);
		user.follow(this);
		super.addObserver(user);
	}

	public List<Message> getNewsFeed() {
		return newsFeed;
	}
	
	public List<User> getFollowing() {
		return following;
	}
	
	public void follow(NormalUser user) {
		following.add(user);
	}
	
	public void post(Message message) {
		setChanged();
		notifyObservers(message);
	}

}
