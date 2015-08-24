package control;

import data.Admin;

//class that is the super class for all of the visitor classes that will visit the Admin class
public abstract class AdminVisitor {
	
	public abstract void visitAdmin(Admin admin);

}
