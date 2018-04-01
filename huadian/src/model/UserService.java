package model;

public class UserService {
	public User queryUserId(User user) {
		UserControl userControl=new UserControl();
		user=userControl.queryUserId(user);
		return user;
	}
	public static void main(String[] args) {
		UserService userService = new UserService();
		User user=new User();
		user.setNameString("ÀîÐûØý");
		user=userService.queryUserId(user);
		System.out.println(user.getId());
	}
}
