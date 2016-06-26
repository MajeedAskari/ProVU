package users;

import java.io.Serializable;
import java.util.ArrayList;

import application.Course;
import application.Post;
import databaseFile.DataBaseController;

public class User implements Serializable {

	/**
	 * 
	 */
	protected int userId;
	private static final long serialVersionUID = -8574223412729431641L;
	protected String name;
	protected ArrayList<Course> userCourses = new ArrayList<Course>();
	protected ArrayList<Post> posts = new ArrayList<Post>();

	protected boolean isTeacher;

	public void newPost(Course c, String subject, String text) {
		Post p = new Post(subject, this.name, text, c.getCourseId());
		posts.add(p);
		DataBaseController.setUserAt(userId, this);
		c.addPost(p);
		DataBaseController.setCourseAt(c.getCourseId(), c);
	}

	public void saveToFile() {

	}

	public void changePassword(String newPassword) {

	}

	public boolean isTeacher() {
		return isTeacher;
	}

	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}

	public ArrayList<Course> getCourses() {
		return userCourses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.userCourses = courses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
