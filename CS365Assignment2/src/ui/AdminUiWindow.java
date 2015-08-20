package ui;

import data.User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AdminUiWindow extends JFrame implements UiWindow {
	
	private static AdminUiWindow adminWindow;
	
	private AdminUiWindow() {
		createWindow();
	}
	
	public static UiWindow getInstance(User user) {
		if(adminWindow == null) {
			adminWindow = new AdminUiWindow();
		}
		return adminWindow;
	}
	
	public void createWindow() {
		//create all ui elements for the admin
		JFrame mainWindow = new JFrame();
		mainWindow.setSize(600, 500);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setTitle("Admin Control Panel");
		mainWindow.setLayout(new BorderLayout());
		//create user list
		
		//temp///////////////////////////////////////////////////////////
		String[] tempArray = {"-Tim", "    -John",  "        -Ben"};	
		//temp///////////////////////////////////////////////////////////
		
		JList<String> userList = new JList<String>(tempArray);
		//make list scrollable
		JScrollPane listScroller = new JScrollPane(userList);
		listScroller.setPreferredSize(new Dimension((mainWindow.getWidth() / 2), mainWindow.getWidth() - 30));
		//add list to left side of the main window
		mainWindow.add(listScroller, BorderLayout.LINE_START);
		//create user and group add text boxes and buttons
		JPanel uiComponentPanel = new JPanel();
		uiComponentPanel.setLayout(new BoxLayout(uiComponentPanel, BoxLayout.Y_AXIS));
		
		JPanel addUserPanel = new JPanel();
		addUserPanel.setLayout(new FlowLayout());
		
		JTextField userId = new JTextField();
		userId.setText("User ID");
		userId.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addUserPanel.add(userId);
		
		Button addUserButton = new Button("Add User");
		addUserButton.addActionListener(new ButtonListener());
		addUserButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addUserPanel.add(addUserButton);
		uiComponentPanel.add(addUserPanel);
		
		JPanel addGroupPanel = new JPanel();
		addGroupPanel.setLayout(new FlowLayout());
		
		JTextField groupId = new JTextField();
		groupId.setText("Group ID");
		groupId.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addGroupPanel.add(groupId);
		
		Button addGroupButton = new Button("Add Group");
		addGroupButton.addActionListener(new ButtonListener());
		addGroupButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addGroupPanel.add(addGroupButton);
		uiComponentPanel.add(addGroupPanel);
		
		JPanel userViewPanel = new JPanel();
		userViewPanel.setLayout(new FlowLayout());
		
		Button openUserViewButton = new Button("Open User View");
		openUserViewButton.addActionListener(new ButtonListener());
		openUserViewButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 30, 30));
		userViewPanel.add(openUserViewButton);
		uiComponentPanel.add(userViewPanel);
		
		JPanel adminButtons1 = new JPanel();
		adminButtons1.setLayout(new FlowLayout());
		
		Button userTotalButton = new Button("Show User Total");
		userTotalButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		userTotalButton.addActionListener(new ButtonListener());
		adminButtons1.add(userTotalButton);
		
		Button groupTotalButton = new Button("Show Group Total");
		groupTotalButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		groupTotalButton.addActionListener(new ButtonListener());
		adminButtons1.add(groupTotalButton);
		uiComponentPanel.add(adminButtons1);
		
		JPanel adminButtons2 = new JPanel();
		adminButtons2.setLayout(new FlowLayout());
		
		Button messageTotalButton = new Button("Show Message Total");
		messageTotalButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		messageTotalButton.addActionListener(new ButtonListener());
		adminButtons2.add(messageTotalButton);
		
		Button positiveMessagePercentageButton = new Button("Positive Message %");
		positiveMessagePercentageButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		positiveMessagePercentageButton.addActionListener(new ButtonListener());
		adminButtons2.add(positiveMessagePercentageButton);
		uiComponentPanel.add(adminButtons2);
		
		mainWindow.add(uiComponentPanel, BorderLayout.LINE_END);
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

}
