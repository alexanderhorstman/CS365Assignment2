package data;

public interface AdminUser extends SingleUser {
	public void addUser(User user);
	public void addGroup(UserGroup group);
}
