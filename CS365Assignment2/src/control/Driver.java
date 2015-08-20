package control;

import data.*;
import ui.*;

public class Driver {

	public static void main(String[] args) {
		AdminUiWindow.getInstance(new Admin());
	}

}
