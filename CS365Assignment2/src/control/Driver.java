package control;

import data.*;
import ui.*;

public class Driver {

	public static void main(String[] args) {
		AdminUser admin = Admin.getInstance();
		UiWindow window = AdminUiWindow.getInstance(admin);
		NormalUser tim = new NormalUser("Tim", admin);
		NormalUser john = new NormalUser("John", admin);
		admin.addUser(tim);
		admin.addUser(john);
		john.addObserver(tim);
		Message newMessage = new Message(tim, "here is a test");
		tim.post(newMessage);
		//NormalUserUiWindow window = new NormalUserUiWindow(tim, admin);
		Message secondMessage = new Message(john, "another test");
		for(int i = 0; i < 10000000; i++) {
			for(int i2 = 0; i2 < 1000; i2++) {
			}
		}
		john.post(secondMessage);
		System.out.println("test");
		window.redraw();
	}

}
