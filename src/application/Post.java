package application;

import java.io.Serializable;

public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7989404512597814436L;
	protected int id;
	protected String creator;
	protected String subject;
	protected String text;
	public Post(String subject, String instructor, String text, int indexOfCourse){
		this.subject = subject;
		setCreator(instructor);
		this.text = text;
	}
	
	
	
	
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getCreator() {
		return creator;
	}






	public void setCreator(String creator) {
		this.creator = creator;
	}

}
