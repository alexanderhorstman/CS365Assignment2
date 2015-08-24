package control;

import java.util.List;

import data.Admin;
import data.Message;

public class CheckPositiveMessageVisitor extends AdminVisitor { 
	
	private String[] positiveMessages = {"good", "great", "fantastic", "awesome"};
	private double positiveCount = 0;
	private double totalMessages;

	@Override
	public void visitAdmin(Admin admin) {
		positiveCount = 0;
		List<Message> messages = admin.getMessages();
		totalMessages = messages.size();
		for(Message m: messages) {
			for(String s: positiveMessages) {			
				if(m.getMessage().toLowerCase().contains(s)) {
					positiveCount++;
				}
			}
		}
	}
	
	public double getPossitiveMessagePercentage() {
		return positiveCount / totalMessages;
		
	}

}
