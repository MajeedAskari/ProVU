package application;

import java.io.*;

public class Login {
	private static DataInputStream input;
	private static DataOutputStream output;

	private static File userDataBase = new File("UsersDataBase.dat");

	public static boolean fileExists() {
		return userDataBase.exists();
	}

	public static boolean userExists(String userName) {

		return false;
	}

	public static String signUp(String userName, String password, String RePassword, boolean isStudent) {
		if (!password.equals(RePassword)) {
			return "Passwords don't match";
		}
		try {
			output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(userDataBase)));
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(userDataBase)));
			try {
				while (true) {
					String un = input.readUTF();
					input.readUTF();
					if (un.equals(userName)) {
						return "User already exists";
					}
					// input.close();
				}
			} catch (IOException ex) {
				// ex.printStackTrace();
			}
		} catch (FileNotFoundException ex) {

		}
		try {
			output.writeUTF(userName);
			output.writeUTF(password);
			output.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "done";

	}

	public static boolean login(String userName, String Password) {
		try {
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(userDataBase)));
			try {
				while (true) {
					String un = input.readUTF();
					if (input.readUTF().equals(Password) && un.equals(userName)) {

						// return student
						return true;
					}
					// input.close();
				}
			} catch (IOException ex) {
				// ex.printStackTrace();
			}
		} catch (FileNotFoundException ex) {
			return false;
		}

		return false;
	}

}
