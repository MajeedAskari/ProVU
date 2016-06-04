package ui;

import java.io.IOException;

import databaseFile.DataBaseController;
import databaseFile.Login;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import users.Student;
import users.Teacher;
import users.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import application.Course;
import javafx.fxml.FXML;

public class UiController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void initialize() {

	}

	private Stage primaryStage;

	public UiController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void loginControl(/* Parent root */) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		Scene scene = new Scene(root, 1400, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		Pane loginPane = (Pane) root.lookup("#loginPane");
		Pane signUpPane = (Pane) root.lookup("#signUpPane");
		Button loginBt = (Button) root.lookup("#loginBt");
		Button goSignUp = (Button) root.lookup("#goSignUp");
		Button goLogin = (Button) root.lookup("#goLogin");
		Button signUpBt = (Button) root.lookup("#signUpBt");
		RadioButton studnetRDBT = (RadioButton) root.lookup("#isStudent");
		loginPane.setStyle("-fx-background-color: transparent;");

		// Entrance Animation <<
		Timeline enterLoginPane = new Timeline(new KeyFrame(Duration.millis(700),
				new javafx.animation.KeyValue(loginPane.layoutYProperty(), 140, Interpolator.EASE_BOTH)));
		enterLoginPane.play();
		Timeline enterSignUpBt = new Timeline(new KeyFrame(Duration.millis(700),
				new javafx.animation.KeyValue(goSignUp.layoutYProperty(), 44, Interpolator.EASE_BOTH)));
		enterSignUpBt.play();
		// Entrance Animation >>

		// change to sign up pane <<
		goSignUp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Timeline exitLoginPane = new Timeline(new KeyFrame(Duration.millis(300),
						new javafx.animation.KeyValue(loginPane.layoutYProperty(), -460, Interpolator.EASE_BOTH)));
				exitLoginPane.play();
				Timeline exitGoSignUpBt = new Timeline(new KeyFrame(Duration.millis(300),
						new javafx.animation.KeyValue(goSignUp.layoutYProperty(), -84, Interpolator.EASE_BOTH)));
				exitLoginPane.play();
				exitGoSignUpBt.play();
				// enter sign up pane
				Timeline enterSignUpPane = new Timeline(new KeyFrame(Duration.millis(600),
						new javafx.animation.KeyValue(signUpPane.layoutYProperty(), 95, Interpolator.EASE_BOTH)));
				enterSignUpPane.setDelay(Duration.millis(500));
				// enter go login button
				Timeline enterGoLoginBt = new Timeline(new KeyFrame(Duration.millis(700),
						new javafx.animation.KeyValue(goLogin.layoutYProperty(), 44, Interpolator.EASE_BOTH)));
				enterGoLoginBt.play();
				enterSignUpPane.play();
			}
		});
		// change to sign up pane >>

		// change to login pane <<
		goLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Timeline exitSignUpPane = new Timeline(new KeyFrame(Duration.millis(300),
						new javafx.animation.KeyValue(signUpPane.layoutYProperty(), -490, Interpolator.EASE_BOTH)));
				exitSignUpPane.play();
				Timeline exitGoLoginBt = new Timeline(new KeyFrame(Duration.millis(300),
						new javafx.animation.KeyValue(goLogin.layoutYProperty(), -84, Interpolator.EASE_BOTH)));
				exitSignUpPane.play();
				exitGoLoginBt.play();
				// enter sign up pane
				Timeline enterLoginPane = new Timeline(new KeyFrame(Duration.millis(600),
						new javafx.animation.KeyValue(loginPane.layoutYProperty(), 100, Interpolator.EASE_BOTH)));
				enterLoginPane.setDelay(Duration.millis(500));
				// enter go login button
				Timeline enterGoSignUpBt = new Timeline(new KeyFrame(Duration.millis(700),
						new javafx.animation.KeyValue(goSignUp.layoutYProperty(), 44, Interpolator.EASE_BOTH)));
				enterGoSignUpBt.play();
				enterLoginPane.play();
			}
		});
		// change to login pane >>

		// loginBt.setOnAction <<
		loginBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TextField usernameField = (TextField) root.lookup("#username");
				PasswordField passwordField = (PasswordField) root.lookup("#password");
				int userId = Login.login(usernameField.getText(), passwordField.getText());
				if (userId == -1) {

					Timeline wrongLoginMove = new Timeline(new KeyFrame(Duration.millis(100),
							new javafx.animation.KeyValue(loginPane.layoutXProperty(), 512, Interpolator.EASE_BOTH)));

					Timeline wrongLoginMoveB = new Timeline(new KeyFrame(Duration.millis(100),
							new javafx.animation.KeyValue(loginPane.layoutXProperty(), 500, Interpolator.EASE_BOTH)));

					wrongLoginMove.setCycleCount(7);

					wrongLoginMove.play();
					wrongLoginMoveB.play();
					usernameField.clear();
					passwordField.clear();
				} else {

					System.out.println("login succesful");
					User tmp = DataBaseController.getUserAt(userId);
					if (tmp.isTeacher())
						teacherPageControl(userId);
					else
						studentPageControl(userId);
				}
			}
		});
		// end of loginBt.setOnAction >>
		signUpBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TextField sgUsernameField = (TextField) root.lookup("#sgUsername");
				PasswordField sgPasswordField = (PasswordField) root.lookup("#sgPassword");
				PasswordField sgPasswordFieldre = (PasswordField) root.lookup("#sgPasswordRe");

				String s = Login.signUp(sgUsernameField.getText(), sgPasswordField.getText(),
						sgPasswordFieldre.getText(), !studnetRDBT.isSelected());
				System.out.println(s);
				// login
			}
		});
		// end of sign up button action >>

	}

	public void studentPageControl(int userId) {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("StudentEnv.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Button logoutBt = (Button) root.lookup("#logout");

		logoutBt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					loginControl();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		Student stu = (Student) DataBaseController.getUserAt(userId);

		MenuBar mb = (MenuBar) root.lookup("#mb");

		javafx.scene.control.Menu ts = new javafx.scene.control.Menu(stu.getName());

		mb.getMenus().add(ts);
		MenuItem add = new MenuItem("mi test");
		ts.getItems().add(add);

		Scene scene = new Scene(root, 1400, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	int tmpCnt;
	Course curCours;

	public void teacherPageControl(int userId) {

		Teacher teacher = (Teacher) DataBaseController.getUserAt(userId);

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("TeacherEnv.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Button logoutBt = (Button) root.lookup("#logout");
		Button createClass = (Button) root.lookup("#createClass");
		Pane createClassPane = (Pane) root.lookup("#createClassPane");
		MenuBar mb = (MenuBar) root.lookup("#mb");
		TextField newClassName = (TextField) root.lookup("#newClassName");
		TextField newClassId = (TextField) root.lookup("#newClassId");
		javafx.scene.control.Menu newClass = mb.getMenus().get(0);
		MenuButton classes = (MenuButton) root.lookup("#classes");

		ArrayList<Course> crs = DataBaseController.getCourses();
		for (tmpCnt = 0; tmpCnt < crs.size(); tmpCnt++) {
			MenuItem add = new MenuItem(crs.get(tmpCnt).getName());
			classes.getItems().add(add);
		}
		
		
		for (int i = 0;i < classes.getItems().size(); i++){
		classes.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArrayList<Course> crs = DataBaseController.getCourses();
				curCours = crs.get(tmpCnt);
			}
		});
		}
		// entering new class pane
		newClass.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Timeline enterLoginPane = new Timeline(new KeyFrame(Duration.millis(500),
						new javafx.animation.KeyValue(createClassPane.layoutYProperty(), 10, Interpolator.EASE_BOTH)));
				enterLoginPane.play();
			}
		});

		createClass.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				teacher.addCourse(newClassName.getText(), newClassId.getText());
				MenuItem add = new MenuItem(newClassName.getText());
				classes.getItems().add(add);
				newClassId.clear();
				newClassName.clear();
				Timeline enterLoginPane = new Timeline(new KeyFrame(Duration.millis(400), new javafx.animation.KeyValue(
						createClassPane.layoutYProperty(), -111, Interpolator.EASE_BOTH)));
				enterLoginPane.play();
			}
		});
		// log out
		logoutBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					loginControl();
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
