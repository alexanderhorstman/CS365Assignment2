package control;

import data.Admin;

public class CheckUserCountVisitor extends AdminVisitor {

	private int userCount;

	@Override
	public void visitAdmin(Admin admin) {
		userCount = admin.getUsers().size();
	}
	
	public int getUserCount() {
		return userCount;
	}
}
