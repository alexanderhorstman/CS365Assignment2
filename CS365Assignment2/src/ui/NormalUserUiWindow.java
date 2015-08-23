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

public class NormalUserUiWindow implements UiWindow {
	
	private  User user;
	private DefaultListModel<String> followingModel;
	private DefaultListModel<String> newsFeedModel;
	private JFrame mainWindow;
	private final Admin admin;
	
	public NormalUserUiWindow(User user, Admin admin) {
		this.user = user;
		this.admin = admin;
		createWindow();
	}
	
	public UiWindow getInstance(User user) {
		this.user = user;
		return new NormalUserUiWindow(user, admin);
	}
	
	private void createWindow() {
		//create all ui elements for the user
		mainWindow = new JFrame();
		mainWindow.setSize(400, 400);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setTitle(user.getName() + "'s News Feed");
		mainWindow.setLayout(new FlowLayout());
		
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
					System.out.println(u.getName());
					if(u.getName().equals(userId.getText())) {
						validUserName = true;
					}
				}
				if(validUserName) {
					
				}
				else {
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

		JList<String> followingList = new JList<String>();
		followingModel = new DefaultListModel<String>();
		followingList.setModel(followingModel);
		followingModel.addElement("Currently Following: ");
		if(((NormalUser) user).getFollowing().size() > 0) {
			for(int i = 0; i < ((NormalUser) user).getFollowing().size(); i++) {
				if(!user.getName().equals(((NormalUser) user).getFollowing().get(i).getName())) {
					followingModel.addElement(((NormalUser) user).getFollowing().get(i).getName());
				}
			}
		}
		JScrollPane listScroller = new JScrollPane(followingList);
		listScroller.setPreferredSize(new Dimension(mainWindow.getWidth() - 20 , 120));
		mainWindow.add(listScroller);
		
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
			}			
		});
		tweetMessageButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 20, 30));
		tweetMessagePanel.add(tweetMessageButton);
		mainWindow.add(tweetMessagePanel);
		
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
		//repopulate the two lists
		followingModel.clear();
		newsFeedModel.clear();
		followingModel.addElement("Currently Following: ");
		if(((NormalUser) user).getFollowing().size() > 0) {
			for(int i = 0; i < ((NormalUser) user).getFollowing().size(); i++) {
				if(!user.getName().equals(((NormalUser) user).getFollowing().get(i).getName())) {
					followingModel.addElement(((NormalUser) user).getFollowing().get(i).getName());
				}
			}
		}
		newsFeedModel.addElement("News Feed: ");
		for(int i = 0; i < ((NormalUser) user).getNewsFeed().size(); i++) {
			newsFeedModel.addElement(((NormalUser) user).getNewsFeed().get(i).toString());
		}

		mainWindow.revalidate();
	}

}
