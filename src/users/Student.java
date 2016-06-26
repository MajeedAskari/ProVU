package users;

import application.Course;
import databaseFile.DataBaseController;

public class Student extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6369167455335521633L;

	public Student(String name, boolean isTeacher, int userId) {
		this.userId = userId;
		this.name = name;
		this.isTeacher = isTeacher;

	}

	public void addCourse(Course c){
		userCourses.add(c);
		DataBaseController.setUserAt(userId, this);		
	}
	
	
	
	
}
