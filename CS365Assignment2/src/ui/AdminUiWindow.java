package ui;

import data.*;

import java.util.List;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import control.*;

import java.awt.*;
import java.awt.event.*;

public class AdminUiWindow extends JFrame implements UiWindow {
	
	private static AdminUiWindow adminWindow;
	private static Admin admin;
	private DefaultMutableTreeNode selectedNode;
	private JTree updateTree;
	final JFrame mainWindow = new JFrame();
	private List<NormalUserUiWindow> userWindows;
	
	private AdminUiWindow() {
		createWindow();
	}
	
	public static UiWindow getInstance(User user) {
		if(adminWindow == null) {
			admin = (Admin) user;
			adminWindow = new AdminUiWindow();
		}
		return adminWindow;
	}
	
	private void createWindow() {
		//create all ui elements for the admin
		mainWindow.setSize(600, 500);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setTitle("Admin Control Panel");
		mainWindow.setLayout(new BorderLayout());
		//create user tree
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		final JTree userTree = new JTree(root);
		updateTree = userTree;
		userTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		userTree.setPreferredSize(new Dimension((mainWindow.getWidth() / 2) - 30, mainWindow.getHeight()));
		userTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				selectedNode = (DefaultMutableTreeNode) userTree.getLastSelectedPathComponent();
			}		
		});
		JScrollPane treeScroller = new JScrollPane(userTree);
		
		//add list to left side of the main window
		mainWindow.add(treeScroller, BorderLayout.LINE_START);
		//create user and group add text boxes and buttons
		JPanel uiComponentPanel = new JPanel();
		uiComponentPanel.setLayout(new BoxLayout(uiComponentPanel, BoxLayout.Y_AXIS));
		
		JPanel addUserPanel = new JPanel();
		addUserPanel.setLayout(new FlowLayout());
		
		final JTextField userId = new JTextField();
		userId.setText("User ID");
		userId.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addUserPanel.add(userId);
		
		Button addUserButton = new Button("Add User");
		addUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(selectedNode != null) {
					//will always be the Root directory
					UserGroup groupToAddTo = admin.getGroups().get(0);
					boolean validUser = true;
					//check to see that the new user is not already in the list
					List<User> users = admin.getUsers();
					String newUser = userId.getText();
					for(User u: users) {
						if(newUser.equals(u.getName())) {
							validUser = false;
						}
					}
					//add the new user to the appropriate group
					if(validUser && selectedNode.toString().equals("Root")) {
						DefaultMutableTreeNode node = new DefaultMutableTreeNode(userId.getText());
						selectedNode.add(node);
						userTree.makeVisible(new TreePath(node.getPath()));
						admin.addUser(new NormalUser(userId.getText(), admin));
						groupToAddTo.addUser(new NormalUser(userId.getText(), admin));
					}
					else if(validUser && !selectedNode.toString().equals("Root")) {
						boolean addToSelected = false;
						//check to see if current selected is a group
						List<UserGroup> groups = admin.getGroups();
						for(UserGroup u: groups) {
							if(selectedNode.toString().equals(u.getName())) {
								addToSelected = true;
								groupToAddTo = u;								
							}
						}
						//if it is a group, add user to that group
						//if not add the user to the selected parent's group
						if(addToSelected) {
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(userId.getText());
							selectedNode.add(node);
							userTree.makeVisible(new TreePath(node.getPath()));
						}
						else {
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(userId.getText());
							((DefaultMutableTreeNode) selectedNode.getParent()).add(node);
							userTree.makeVisible(new TreePath(node.getPath()));
						}
						admin.addUser(new NormalUser(userId.getText(), admin));
						groupToAddTo.addUser(new NormalUser(userId.getText(), admin));
					}
					else {
						JOptionPane.showMessageDialog(mainWindow, 
								userId.getText() + " is already a user.");
					}
				}
				else {
					JOptionPane.showMessageDialog(mainWindow, "Select a group to add the user to.");
				}
				redraw();
			}			
		});
		addUserButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addUserPanel.add(addUserButton);
		uiComponentPanel.add(addUserPanel);
		
		JPanel addGroupPanel = new JPanel();
		addGroupPanel.setLayout(new FlowLayout());
		
		final JTextField groupId = new JTextField();
		groupId.setText("Group ID");
		groupId.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addGroupPanel.add(groupId);
		
		Button addGroupButton = new Button("Add Group");
		addGroupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selectedNode != null) {
					//will always be the Root directory
					UserGroup groupToAddTo = admin.getGroups().get(0);
					boolean validGroup = true;
					//check to see that the new group is not already in the list
					List<UserGroup> groups = admin.getGroups();
					String newGroup = groupId.getText();
					for(UserGroup u: groups) {
						if(newGroup.equals(u.getName())) {
							validGroup = false;
						}
					}
					//add the new group to the appropriate group
					if(validGroup && selectedNode.toString().equals("Root")) {
						DefaultMutableTreeNode node = new DefaultMutableTreeNode(groupId.getText());
						selectedNode.add(node);
						userTree.makeVisible(new TreePath(node.getPath()));
						admin.addGroup(new UserGroup(groupId.getText()));
						groupToAddTo.addGroup(new UserGroup(groupId.getText()));
					}
					else if(validGroup && !selectedNode.toString().equals("Root")) {
						boolean addToSelected = false;
						//check to see if current selected is a group
						for(UserGroup u: groups) {
							if(selectedNode.toString().equals(u.getName())) {
								addToSelected = true;
								groupToAddTo = u;
							}
						}
						//if it is a group, add new group to that group
						//if not add the new group to the selected parent's group
						if(addToSelected) {
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(groupId.getText());
							selectedNode.add(node);
							userTree.makeVisible(new TreePath(node.getPath()));
							
						}
						else {
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(groupId.getText());
							((DefaultMutableTreeNode) selectedNode.getParent()).add(node);
							userTree.makeVisible(new TreePath(node.getPath()));
						}
						admin.addGroup(new UserGroup(groupId.getText()));
						groupToAddTo.addGroup(new UserGroup(groupId.getText()));
					}
					else {
						JOptionPane.showMessageDialog(mainWindow, 
								groupId.getText() + " is already a group.");
					}
				}
				else {
					JOptionPane.showMessageDialog(mainWindow, "Select a group to add the user to.");
				}
				redraw();
			}			
		});
		addGroupButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		addGroupPanel.add(addGroupButton);
		uiComponentPanel.add(addGroupPanel);
		
		JPanel userViewPanel = new JPanel();
		userViewPanel.setLayout(new FlowLayout());
		
		Button openUserViewButton = new Button("Open User View");
		openUserViewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<User> users = admin.getUsers();
				for(User u: users) {
					if(u.getName().equals(selectedNode.toString())) {
						NormalUserUiWindow newWindow = new NormalUserUiWindow(u, admin);
						((NormalUser) u).setUi(newWindow);
						//userWindows.add(newWindow);
					}
				}
			}			
		});
		openUserViewButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 2 - 30, 30));
		userViewPanel.add(openUserViewButton);
		uiComponentPanel.add(userViewPanel);
		
		JPanel adminButtons1 = new JPanel();
		adminButtons1.setLayout(new FlowLayout());
		
		Button userTotalButton = new Button("Show User Total");
		userTotalButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		userTotalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CheckUserCountVisitor visitor = new CheckUserCountVisitor();
				admin.accept(visitor);
				JOptionPane.showMessageDialog(mainWindow, visitor.getUserCount() + " users total.");
			}			
		});
		adminButtons1.add(userTotalButton);
		
		Button groupTotalButton = new Button("Show Group Total");
		groupTotalButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		groupTotalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CheckGroupCountVisitor visitor = new CheckGroupCountVisitor();
				admin.accept(visitor);
				JOptionPane.showMessageDialog(mainWindow, visitor.getGroupCount() + " groups total.");
			}			
		});
		adminButtons1.add(groupTotalButton);
		uiComponentPanel.add(adminButtons1);
		
		JPanel adminButtons2 = new JPanel();
		adminButtons2.setLayout(new FlowLayout());
		
		Button messageTotalButton = new Button("Show Message Total");
		messageTotalButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		messageTotalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CheckMessageTotalVisitor visitor = new CheckMessageTotalVisitor();
				admin.accept(visitor);
				JOptionPane.showMessageDialog(mainWindow, visitor.getMessageCount() + " messages total.");
			}			
		});
		adminButtons2.add(messageTotalButton);
		
		Button positiveMessagePercentageButton = new Button("Positive Message %");
		positiveMessagePercentageButton.setPreferredSize(new Dimension(mainWindow.getWidth() / 4 - 20, 30));
		positiveMessagePercentageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CheckPositiveMessageVisitor visitor = new CheckPositiveMessageVisitor();
				admin.accept(visitor);
				JOptionPane.showMessageDialog(mainWindow, visitor.getPossitiveMessagePercentage() + "% positive messages.");
			}			
		});
		adminButtons2.add(positiveMessagePercentageButton);
		uiComponentPanel.add(adminButtons2);
		
		mainWindow.add(uiComponentPanel, BorderLayout.LINE_END);
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	public void redraw() {
		//update the user list
		updateTree.updateUI();
	}

}
