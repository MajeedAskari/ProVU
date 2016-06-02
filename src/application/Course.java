package application;

import java.io.Serializable;
import java.util.ArrayList;

import users.Student;

public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6385793158202782560L;
	private String[] name;
	ArrayList<Student> students = new ArrayList<Student>();
	//ArrayList<E>

}
