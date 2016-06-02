package databaseFile;

public class Login {

	public static String signUp(String userName, String password, String RePassword, boolean isTeacher) {
		if (!password.equals(RePassword)) {
			return "Passwords don't match";
		}
		if (DataBaseController.userExists(userName) != -1) {
			return "User Already Exists";
		}

		DataBaseController.signUp(userName, password, isTeacher);
		return "done";
	}

	public static int login(String userName, String password) {
		int tmp = DataBaseController.login(userName, password);
		return tmp;
	}

}
