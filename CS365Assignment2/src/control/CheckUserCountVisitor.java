package control;

import data.Admin;

//class that checks how many users an admin currently has
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
