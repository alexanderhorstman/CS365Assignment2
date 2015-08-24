package data;

//class that contains a message and the user that posted the message
public class Message {
	
	private String message;
	private User user;
	
	public Message(User user, String message) {
		this.user = user;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public User getUser() {
		return user;
	}
	
	//returns a string in the form "-User: The message that was posted."
	public String toString() {
		return "- " + user.getName() + ": " + message;
	}
}
