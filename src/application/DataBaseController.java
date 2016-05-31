package application;

import java.util.ArrayList;


public class DataBaseController {
	{
		DataBase.getDataBase().loadFromFile();
	}
	private static DataBase A = DataBase.getDataBase();
	private static ArrayList<String> userNames = A.getUserNames();
	private static ArrayList<String> passwords = A.getPasswords();
	private static ArrayList<User> users = A.getUsers();

	public static int userExists(String userName) {
		return userNames.indexOf(userName);

	}

	public static void signUp(String userName, String password) {

		userNames.add(userName);
		passwords.add(password);
		A.saveToFile();
	}
	public static /*User*/ boolean login(String userName, String password) {
		A.loadFromFile();
		A = DataBase.getDataBase();
		int tmp = userExists(userName);
		if ( tmp == -1 ){
			System.out.println("ffff");
			return false;
			//return null;
		}
		//return users.get(tmp);
		System.out.println("ttttt");

		return true;
		
		
	}


}
