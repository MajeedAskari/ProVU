package ui;

import java.io.IOException;
import java.util.ArrayList;
import application.Course;
import application.Post;
import databaseFile.DataBaseController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.TeacherPageControl.classListHandler;
import users.Student;
import users.Teacher;
import users.User;
public class StudentPageControl {

	class classListHandler<ActionEvent> implements javafx.event.EventHandler<javafx.event.ActionEvent> {
		int i;

		public classListHandler(int i) {
			this.i = i;
		}

		@Override
		public void handle(javafx.event.ActionEvent arg0) {
			courseId = i;
			currentCourse = DataBaseController.getCourses().get(i);
			showPosts();
		}

	}

	class postHandler<ActionEvent> implements javafx.event.EventHandler<javafx.event.ActionEvent> {
		int i;

		public postHandler(int i) {
			this.i = i;
		}

		@Override
		public void handle(javafx.event.ActionEvent arg0) {
			courseId = i;
			currentCourse = DataBaseController.getCourses().get(i);
			showPosts();
		}

	}

	class postListHandler<Event> implements javafx.event.EventHandler<javafx.event.Event> {
		int i;

		public postListHandler(int i) {
			this.i = i;
		}

		@Override
		public void handle(javafx.event.Event arg0) {
			currentPost = DataBaseController.getCourses().get(courseId).getPosts().get(i);
			System.out.println("post clicked");
		}

	}

	private Stage primaryStage;
	private UiController uc;
	private Parent root = null;
	private int userId;
	private int courseId;
	private Course currentCourse;
	private User currentUser;
	private Post currentPost;

	public StudentPageControl(Stage primaryStage, UiController uc) {
		this.primaryStage = primaryStage;
		this.uc = uc;
	}

	public void postViewer() {
		TextFlow tf = (TextFlow) root.lookup("#textFlow");
		
		WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.loadContent(currentPost.getText());
        browser.autosize();
		tf.getChildren().clear();
		tf.getChildren().add(browser);
	}

	public void newPost() {
		Button sendPost = (Button) root.lookup("#sendPost");
		Button cancelPost = (Button) root.lookup("#cancelPost");
		Pane newPostPane = (Pane) root.lookup("#newPostPane");
		TextField postSubject = (TextField) root.lookup("#postSubject");
		HTMLEditor postText = (HTMLEditor) root.lookup("#postText");

		Timeline enterNewPostPane = new Timeline(new KeyFrame(Duration.millis(500),
				new javafx.animation.KeyValue(newPostPane.layoutYProperty(), 20, Interpolator.EASE_BOTH)));
		enterNewPostPane.play();

		sendPost.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				DataBaseController.getUserAt(userId).newPost(currentCourse, postSubject.getText(),
						postText.getHtmlText());
				currentCourse = DataBaseController.getCourses().get(courseId);
				showPosts();
				postSubject.clear();
				postText.setHtmlText("");
				Timeline enterNewPostPane = new Timeline(new KeyFrame(Duration.millis(500),
						new javafx.animation.KeyValue(newPostPane.layoutYProperty(), -473, Interpolator.EASE_BOTH)));
				enterNewPostPane.play();
			}
		});

		cancelPost.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Timeline enterNewPostPane = new Timeline(new KeyFrame(Duration.millis(500),
						new javafx.animation.KeyValue(newPostPane.layoutYProperty(), -473, Interpolator.EASE_BOTH)));
				enterNewPostPane.play();
			}
		});

	}

	private ObservableList<String> listItems = FXCollections.observableArrayList();

	public void showPosts() {
		ArrayList<Post> posts = DataBaseController.getCourses().get(courseId).getPosts();

		@SuppressWarnings("unchecked")
		ListView<String> listBox = (ListView<String>) root.lookup("#listBox");
		listBox.setItems(listItems);
		listBox.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				int index = listBox.getSelectionModel().getSelectedIndex();
				currentPost = posts.get(index);
				postViewer();
			}
		});
		listItems.clear();
		for (int j = 0; j < posts.size(); j++) {
			listItems.add(posts.get(j).getSubject());
		}
		listBox.setItems(listItems);
	}

	int i;

	public void studentPageControl(int userId) {
		this.userId = userId;
		Student student = (Student) DataBaseController.getUserAt(userId);

		try {
			root = FXMLLoader.load(getClass().getResource("StudentEnv.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// declare of buttons
		Button logoutBt = (Button) root.lookup("#logout");
		Button createClass = (Button) root.lookup("#createClass");
		Pane createClassPane = (Pane) root.lookup("#createClassPane");
		MenuBar mb = (MenuBar) root.lookup("#mb");
		MenuItem newPost = mb.getMenus().get(0).getItems().get(0);
		MenuItem addStu = mb.getMenus().get(0).getItems().get(1);

		MenuButton classes = (MenuButton) root.lookup("#classes");
		MenuItem add;

		// showing all classes in menu
		ArrayList<Course> crs = DataBaseController.getCourses();
		for (i = 0; i < crs.size(); i++) {
			add = new MenuItem(crs.get(i).getName());
			add.setOnAction(new classListHandler<ActionEvent>(i));
			classes.getItems().add(add);
		}

		// entering new class pane

		newPost.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				newPost();

			}
		});

		// log out
		logoutBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					uc.loginControl();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		Scene scene = new Scene(root, 1400, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
