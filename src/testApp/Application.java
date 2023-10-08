package testApp;

import boundary.Login_Gui;


public class Application {
	public static void main(String[] args) {
		try {
			Login_Gui frame = new Login_Gui();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
