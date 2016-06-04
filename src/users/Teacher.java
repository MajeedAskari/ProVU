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

	public void newPost(Course c, String subject, String text) {
		Post p = new Post(subject, this.name, text, c.getCourseId());
		posts.add(p);
		DataBaseController.setUserAt(userId, this);
		c.addPost(p);
		DataBaseController.setCourseAt(c.getCourseId(), c);
	}

}
