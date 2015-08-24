package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.List;

import data.Admin;
import data.Message;
import data.NormalUser;
import data.User;

//class that builds the UI window for a NormalUser
public class NormalUserUiWindow implements UiWindow {
	
	private static NormalUser user; //the user that the window is created for
	private DefaultListModel<String> followingModel; //list model that holds the information for the users 
													 //that this user is following
	private DefaultListModel<String> newsFeedModel; //list model that holds the information for the user's news feed
	private JFrame mainWindow; //the window that holds the UI elements for the User View
	private static Admin admin; //the admin that currently has the User View open
	
	public NormalUserUiWindow(User newUser, Admin admin1) {
		user = (NormalUser) newUser;
		admin = admin1;
		createWindow();
	}
	
	private void createWindow() {
		//create all ui elements for the user
		mainWindow = new JFrame();
		mainWindow.setSize(400, 400);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setTitle(user.getName() + "'s News Feed");
		mainWindow.setLayout(new FlowLayout());
		
		//create the text box and button to follow a user
		JPanel followUserPanel = new JPanel();
		followUserPanel.setLayout(new FlowLayout());
		
		final JTextField userId = new JTextField();
		userId.setText("User ID");
		userId.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 20, 30));
		followUserPanel.add(userId);
		
		Button followUserButton = new Button("Follow User");
		followUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean validUserName = false;
				List<User> users = admin.getUsers();
				for(User u: users) {
					if(u.getName().equals(userId.getText())) {
						validUserName = true;
						user.follow((NormalUser) u);
						userId.setText("");
					}
				}
				 if(!validUserName && !userId.getText().equals("")) {
					//display a error dialog 
					JOptionPane.showMessageDialog(mainWindow, 
							userId.getText() + " could not be found. Enter the name of a current user.");
				}
				redraw();
			}
		});
		followUserButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 20, 30));
		followUserPanel.add(followUserButton);
		mainWindow.add(followUserPanel);

		//create the list of users that this user is following
		JList<String> followingList = new JList<String>();
		followingModel = new DefaultListModel<String>();
		followingList.setModel(followingModel);
		followingModel.addElement("Currently Following: ");
		if(((NormalUser) user).getFollowing().size() > 0) {
			for(int i = 0; i < ((NormalUser) user).getFollowing().size(); i++) {
				//NOTE: each user is following themselves to allow for automatic updates to the UI window
				//the following line checks to make sure that the own user's name does not get displayed 
				//as a user that they are following
				if(!user.getName().equals(((NormalUser) user).getFollowing().get(i).getName())) {
					followingModel.addElement(((NormalUser) user).getFollowing().get(i).getName());
				}
			}
		}
		JScrollPane listScroller = new JScrollPane(followingList);
		listScroller.setPreferredSize(new Dimension(mainWindow.getWidth() - 20 , 120));
		mainWindow.add(listScroller);
		
		//create the text box and button for posting a message
		JPanel tweetMessagePanel = new JPanel();
		tweetMessagePanel.setLayout(new FlowLayout());
		
		final JTextField message = new JTextField();
		message.setText("TweetMessage");
		message.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 20, 30));
		tweetMessagePanel.add(message);
		
		Button tweetMessageButton = new Button("Post Tweet");
		tweetMessageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((NormalUser) user).post(new Message(user, message.getText()));
				redraw();
				System.out.println("Post test. User: " + user.getName());
			}			
		});
		tweetMessageButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 20, 30));
		tweetMessagePanel.add(tweetMessageButton);
		mainWindow.add(tweetMessagePanel);
		
		//creates the list for the user's news feed
		JList<String> newsFeedList = new JList<String>();
		newsFeedModel = new DefaultListModel<String>();
		newsFeedList.setModel(newsFeedModel);
		newsFeedModel.addElement("News Feed: ");
		for(int i = 0; i < ((NormalUser) user).getNewsFeed().size(); i++) {
			newsFeedModel.addElement(((NormalUser) user).getNewsFeed().get(i).toString());
		}
		JScrollPane newsFeedScroller = new JScrollPane(newsFeedList);
		newsFeedScroller.setPreferredSize(new Dimension(mainWindow.getWidth() - 20 , 120));
		mainWindow.add(newsFeedScroller);
		
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	public void redraw() {
		//repopulate the following list and the news feed
		followingModel.clear();
		newsFeedModel.clear();
		followingModel.addElement("Currently Following: ");
		if(((NormalUser) user).getFollowing().size() > 0) {
			for(int i = 0; i < ((NormalUser) user).getFollowing().size(); i++) {
				//NOTE: each user is following themselves to allow for automatic updates to the UI window
				//the following line checks to make sure that the own user's name does not get displayed 
				//as a user that they are following
				if(!user.getName().equals(((NormalUser) user).getFollowing().get(i).getName())) {
					followingModel.addElement(((NormalUser) user).getFollowing().get(i).getName());
				}
			}
		}
		newsFeedModel.addElement("News Feed: ");
		for(int i = 0; i < ((NormalUser) user).getNewsFeed().size(); i++) {
			newsFeedModel.addElement(((NormalUser) user).getNewsFeed().get(i).toString());
		}
		//refreshes the window with the updated information
		mainWindow.revalidate();
	}

}
