package ui;

import java.awt.*;
import javax.swing.*;

import data.NormalUser;
import data.User;

public class NormalUserUiWindow implements UiWindow {
	
	private  User user;
	private DefaultListModel<String> followingModel;
	private DefaultListModel<String> newsFeedModel;
	private JFrame mainWindow;
	
	public NormalUserUiWindow(User user) {
		this.user = user;
		createWindow();
	}
	
	public UiWindow getInstance(User user) {
		this.user = user;
		return new NormalUserUiWindow(user);
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
		
		JTextField userId = new JTextField();
		userId.setText("User ID");
		userId.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 20, 30));
		followUserPanel.add(userId);
		
		Button followUserButton = new Button("Follow User");
		followUserButton.addActionListener(new ButtonListener());
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
		
		JTextField message = new JTextField();
		message.setText("TweetMessage");
		message.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 20, 30));
		tweetMessagePanel.add(message);
		
		Button tweetMessageButton = new Button("Post Tweet");
		tweetMessageButton.addActionListener(new ButtonListener());
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
