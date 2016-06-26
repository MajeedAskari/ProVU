package users;

import application.Course;
import application.Post;
import databaseFile.DataBaseController;

public class Teacher extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8161198101752081595L;

	public Teacher(String name, boolean isTeacher, int userId) {
		this.userId = userId;
		this.name = name;
		this.isTeacher = isTeacher;

	}

	public void addCourse(String courseName, String id) {
		Course C = new Course(courseName, this.name, id);
		userCourses.add(C);
		DataBaseController.setUserAt(userId, this);
		DataBaseController.addCourse(C);
	}

	public void addStudent(Course c,Student st){
		st.addCourse(c);
		c.addStudent(st);
	}


}
