import java.util.Scanner;
public class Menu {

	static Scanner sc = new Scanner(System.in);
	public int menu()
   {
		System.out.println("*******************************************************");
	    System.out.println("\t \tWelcome to jukebox");
	    System.out.println("*******************************************************");
	    System.out.println("Welcome to JukeBox. If you have not Signed Please signup and Get in");
	    System.out.println("Enter Your Choice  1. New User 	2.Existing user");
	    int choice = sc.nextInt();
	    
	    return choice;
   }
	
	public int afterlogging()
	{
		System.out.println("Enter Your Choice  1. View All Songs   2.View All Podcasts 	3.Search Song   4.Create User Playlist  5.Add Songs to Playlist  6.Play songs From Your Playlist 7.Add podcast to your playlist 8.Play podcast From Your Playlist 9.Exit");
		
		int val=sc.nextInt();
		
		return val;
	}
}
