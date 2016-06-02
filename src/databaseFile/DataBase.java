package databaseFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import application.Course;
import users.User;

public class DataBase implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7023565521122538323L;

	private static DataBase D = new DataBase();

	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<String> userNames = new ArrayList<String>();
	private ArrayList<String> passwords = new ArrayList<String>();
	public char test;

	private DataBase() {

	}
	
//	public void fileExists(){
//		File f = new File("DataBase.dat");
//		if(!f.exists()){
//			try {
//				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DataBase.dat"));
//				output.writeObject(D);
//				output.flush();
//				output.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//	}
	
	public boolean saveToFile() {

		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DataBase.dat"));
			output.writeObject(D);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public DataBase loadFromFile() {

		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("DataBase.dat"));
			try {
				D = (DataBase) input.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			input.close();
		} catch (IOException e) {
			System.out.println("File not Found, Creating ...");
			//e.printStackTrace();
		}

		return D;
	}


	public static DataBase getDataBase() {
		return D;
	}

	public void deleteFile() {
		File F = new File("DataBase.dat");
		F.delete();
	}


	public ArrayList<String> getPasswords() {
		return passwords;
	}

	public void setPasswords(ArrayList<String> passwords) {
		this.passwords = passwords;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public char getTest() {
		return test;
	}

	public void setTest(char test) {
		this.test = test;
	}

	public ArrayList<String> getUserNames() {
		return userNames;
	}

	public void setUserNames(ArrayList<String> userNames) {
		this.userNames = userNames;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}
