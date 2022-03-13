import java.util.Scanner;

public class User {

	static Scanner sc = new Scanner(System.in);
	int userId;
	String userName;
	String password;
	
	
	public User() {}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}

	
	public void initialize()
	{
		
		System.out.println("Enter Username");
		userName = sc.nextLine();
		System.out.println("Enter password");
		password = sc.nextLine();
	}
	public User(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
