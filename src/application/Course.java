package application;

import java.io.Serializable;
import java.util.ArrayList;

import users.Student;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6385793158202782560L;
	

	private String name;
	private int courseId; // index in courses array list
	private String classId;// user defined
	private String instructorName;
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Post> posts = new ArrayList<Post>();

	
	public Course(String name, String Instructor, String id){
		this.name = name;
		instructorName = Instructor;
		classId = id;
	}

	public void addPost(Post p){
		posts.add(p);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

//	public int getCourseId() {
//		return courseId;
//	}
//
//	public void setCourseId(int courseId) {
//		this.courseId = courseId;
//	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

}
