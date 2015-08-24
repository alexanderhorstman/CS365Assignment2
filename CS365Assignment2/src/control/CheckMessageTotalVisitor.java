package control;

import data.Admin;

public class CheckMessageTotalVisitor extends AdminVisitor {

	private int messageCount;
	
	public void visitAdmin(Admin admin) {
		messageCount = admin.getMessages().size();
	}
	
	public int getMessageCount() {
		return messageCount;
	}
}
