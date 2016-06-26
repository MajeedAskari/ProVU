package ui;

import java.util.ArrayList;

import application.Post;
import databaseFile.DataBaseController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

public class PostViewer {

	public void showPosts() {
		int courseId = 0;
		ArrayList<Post> posts = DataBaseController.getCourses().get(courseId).getPosts();

		Parent root = null;
		@SuppressWarnings("unchecked")
		ListView<String> listBox = (ListView<String>) root.lookup("#listBox");
		listBox.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				int index = listBox.getSelectionModel().getSelectedIndex();
			}
		});
		ArrayList<Post> listItems = null;
		listItems.clear();
		for (int j = 0; j < posts.size(); j++) {
			//listItems.addAll(posts.get(j).getSubject());
		}
	}
	
	
}
