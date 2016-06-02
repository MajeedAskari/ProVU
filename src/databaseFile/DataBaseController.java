package databaseFile;

import java.util.ArrayList;

import users.*;

public class DataBaseController {
	{
		DataBase.getDataBase().loadFromFile();
	}
	private static DataBase A = DataBase.getDataBase();
	private static ArrayList<String> userNames = A.getUserNames();
	private static ArrayList<String> passwords = A.getPasswords();
	private static ArrayList<User> users = A.getUsers();

	public static int userExists(String userName) {
		load();
		//System.out.println(userNames.toString());
		return userNames.indexOf(userName);
	}

	private static void load(){
		DataBase.getDataBase().loadFromFile();
		A = DataBase.getDataBase();

		userNames = A.getUserNames();
		passwords = A.getPasswords();
		users = A.getUsers();
	}
	
	public static int signUp(String userName, String password, boolean isTeacher) {

		load();
		
		userNames.add(userName);
		passwords.add(password);
		if (isTeacher) {
			users.add(new Teacher(userName, isTeacher));
		}
		if (!isTeacher) {
			users.add(new Student(userName, isTeacher));
		}
		A.setUserNames(userNames);
		A.setPasswords(passwords);
		A.setUsers(users);
		
		A.saveToFile();
		return userNames.indexOf(userName);
	}

	public static int login(String userName, String password) {
		DataBase.getDataBase().loadFromFile();
		A = DataBase.getDataBase();

		userNames = A.getUserNames();
		passwords = A.getPasswords();
		int tmp = userExists(userName);
		if (tmp == -1 || !passwords.get(tmp).equals(password)) {
			return -1;
		}
		return tmp;
	}
	
	public static User getUserAt(int id){
		load();
		System.out.println(id);
		return users.get(id);
		
	}
	

}
