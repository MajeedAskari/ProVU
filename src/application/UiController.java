package application;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class UiController {

	public static void control(Parent root) {
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
				if (!Login.login(usernameField.getText(), passwordField.getText())) {

					Timeline wrongLoginMove = new Timeline(new KeyFrame(Duration.millis(100),
							new javafx.animation.KeyValue(loginPane.layoutXProperty(), 512, Interpolator.EASE_BOTH)));

					Timeline wrongLoginMoveB = new Timeline(new KeyFrame(Duration.millis(100),
							new javafx.animation.KeyValue(loginPane.layoutXProperty(), 500, Interpolator.EASE_BOTH)));

					wrongLoginMove.setCycleCount(7);

					wrongLoginMove.play();
					wrongLoginMoveB.play();
					usernameField.clear();
					passwordField.clear();
				} else
					System.out.println("login succesful");
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
						sgPasswordFieldre.getText(), studnetRDBT.isSelected());
				System.out.println(s);

			}
		});
		// end of sign up button action >>

	}

}
