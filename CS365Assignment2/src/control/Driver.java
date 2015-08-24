package control;

import data.*;
import ui.*;

public class Driver {

	public static void main(String[] args) {
		AdminUser admin = Admin.getInstance();
		UiWindow window = AdminUiWindow.getInstance(admin);
	}

}
