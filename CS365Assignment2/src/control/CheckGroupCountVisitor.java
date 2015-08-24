package control;

import data.Admin;

//class that checks the number of groups that an admin currently has
public class CheckGroupCountVisitor extends AdminVisitor {
	
	private int groupCount;

	@Override
	public void visitAdmin(Admin admin) {
		groupCount = admin.getGroups().size();
		
	}
	
	public int getGroupCount() {
		return groupCount;
	}

}
