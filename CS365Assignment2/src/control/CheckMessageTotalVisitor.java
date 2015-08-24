package control;

import data.Admin;

//class that checks the total number of messages that an admin currently has
public class CheckMessageTotalVisitor extends AdminVisitor {

	private int messageCount;
	
	public void visitAdmin(Admin admin) {
		messageCount = admin.getMessages().size();
	}
	
	public int getMessageCount() {
		return messageCount;
	}
}
