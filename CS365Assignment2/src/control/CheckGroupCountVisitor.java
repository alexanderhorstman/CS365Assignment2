package control;

import data.Admin;

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
