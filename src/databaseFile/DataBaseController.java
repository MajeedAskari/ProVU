package databaseFile;

import java.util.ArrayList;

import application.Course;
import users.*;

public class DataBaseController {
	{
		DataBase.getDataBase().loadFromFile();
	}
	private static DataBase A = DataBase.getDataBase();
	private static ArrayList<String> userNames = A.getUserNames();
	private static ArrayList<String> passwords = A.getPasswords();
	private static ArrayList<User> users = A.getUsers();
	private static ArrayList<Course> courses = A.getCourses();

	public static int userExists(String userName) {
		load();
		return userNames.indexOf(userName);
	}

	private static void load() {
		DataBase.getDataBase().loadFromFile();
		A = DataBase.getDataBase();

		courses = A.getCourses();
		userNames = A.getUserNames();
		passwords = A.getPasswords();
		users = A.getUsers();
	}

	private static void save() {
		A.setUserNames(userNames);
		A.setPasswords(passwords);
		A.setUsers(users);
		A.setCourses(courses);
		A.saveToFile();
	}

	public static int signUp(String userName, String password) {

		load();

		userNames.add(userName);
		passwords.add(password);
		save();
		return userNames.indexOf(userName);
	}

	public static int login(String userName, String password) {
		load();
		int tmp = userExists(userName);
		if (tmp == -1 || !passwords.get(tmp).equals(password)) {
			return -1;
		}
		return tmp;
	}

	public static User getUserAt(int id) {
		load();
		return users.get(id);

	}

	public static void setUserAt(int userId, User A) {
		load();
		if(userId < users.size())
		users.set(userId, A);
		else users.add(A);
		save();
	}
	public static void setCourseAt(int index, Course C){
		load();
		courses.set(C.getCourseId(), C);
		save();
	}

	public static void addCourse(Course C) {
		load();
		courses.add(C);
		C.setCourseId(courses.indexOf(C));
		courses.set(C.getCourseId(), C);
		save();
	}

	public static ArrayList<Course> getCourses() {
		load();
		return courses;
	}

}
