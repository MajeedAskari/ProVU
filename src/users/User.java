package users;

import java.io.Serializable;
import java.util.ArrayList;

import application.Course;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8574223412729431641L;
	protected String name;
	protected ArrayList<Course> courses;
	protected boolean isTeacher;

	public boolean isTeacher() {
		return isTeacher;
	}

	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
