package control;

import data.*;
import ui.*;

public class Driver {

	public static void main(String[] args) {
		AdminUiWindow.getInstance(new Admin());
		NormalUser tim = new NormalUser("Tim");
		NormalUser john = new NormalUser("John");
		john.addObserver(tim);
		Message newMessage = new Message(tim, "here is a test");
		tim.post(newMessage);
		NormalUserUiWindow window = new NormalUserUiWindow(tim);
		Message secondMessage = new Message(john, "another test");
		john.post(secondMessage);
		for(int i = 0; i < 10000000; i++) {
			for(int i2 = 0; i2 < 1000; i2++) {
			}
		}
		System.out.println("test");
		window.redraw();
	}

}
