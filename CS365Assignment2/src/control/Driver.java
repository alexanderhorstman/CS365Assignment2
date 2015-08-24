package control;

import data.*;
import ui.*;

public class Driver {

	public static void main(String[] args) {
		AdminUser admin = Admin.getInstance();
		AdminUiWindow window = AdminUiWindow.getInstance(admin);
		window.prepopulate();
	}

}
