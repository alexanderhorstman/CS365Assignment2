package ui;

import data.User;

public class NormalUserUiWindow implements UiWindow {
	
	private NormalUserUiWindow() {
		createWindow();
	}
	
	public UiWindow getInstance(User user) {
		return new NormalUserUiWindow();
	}
	
	private void createWindow() {
		//create all ui elements for the user
	}

}
