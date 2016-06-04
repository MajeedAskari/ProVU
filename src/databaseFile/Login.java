package databaseFile;

import users.Student;
import users.Teacher;

public class Login {

	public static String signUp(String userName, String password, String RePassword, boolean isTeacher) {
		if (!password.equals(RePassword)) {
			return "Passwords don't match";
		}
		if (DataBaseController.userExists(userName) != -1) {
			return "User Already Exists";
		}

		int userId = DataBaseController.signUp(userName, password);
		if (isTeacher) {
			DataBaseController.setUserAt(userId,new Teacher(userName, isTeacher, userId));
		}
		if (!isTeacher) {
			DataBaseController.setUserAt(userId,new Student(userName, isTeacher, userId));
		}
		return "done";
	}

	public static int login(String userName, String password) {
		int tmp = DataBaseController.login(userName, password);
		return tmp;
	}

}
