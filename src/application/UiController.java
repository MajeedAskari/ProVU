package application;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class UiController {

	public static void control(Parent root) {
		Pane loginPane = (Pane) root.lookup("#loginPane");
		Pane signUpPane = (Pane) root.lookup("#signUpPane");
		loginPane.setStyle("-fx-background-color: transparent;");
		Button loginBt = (Button) root.lookup("#loginBt");
		Button signUpBt = (Button) root.lookup("#signUpBt");

		Timeline enterLoginPane = new Timeline(new KeyFrame(Duration.millis(700),
				new javafx.animation.KeyValue(loginPane.layoutYProperty(), 140, Interpolator.EASE_BOTH)));
		enterLoginPane.play();
		Timeline enterSignUpBt = new Timeline(new KeyFrame(Duration.millis(700),
				new javafx.animation.KeyValue(signUpBt.layoutYProperty(), 44, Interpolator.EASE_BOTH)));
		enterSignUpBt.play();
		signUpBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Timeline exitLoginPane = new Timeline(new KeyFrame(Duration.millis(300),
						new javafx.animation.KeyValue(loginPane.layoutYProperty(), -500, Interpolator.EASE_BOTH)));
				exitLoginPane.play();
				Timeline enterSignUpPane = new Timeline(new KeyFrame(Duration.millis(700),
						new javafx.animation.KeyValue(signUpPane.layoutYProperty(), 105, Interpolator.EASE_BOTH)));
				enterSignUpPane.setDelay(Duration.millis(500));
				enterSignUpPane.play();
			}
		});

		loginBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TextField usernameField = (TextField) root.lookup("#username");
				PasswordField passwordField = (PasswordField) root.lookup("#password");
				if (!Login.login(usernameField.getText(), passwordField.getText())) {
					Timeline wrongLoginMove = new Timeline(new KeyFrame(Duration.millis(100),
							new javafx.animation.KeyValue(loginPane.layoutXProperty(), 478, Interpolator.EASE_BOTH)));
					Timeline wrongLoginMoveB = new Timeline(new KeyFrame(Duration.millis(100),
							new javafx.animation.KeyValue(loginPane.layoutXProperty(), 468, Interpolator.EASE_BOTH)));
					wrongLoginMove.setCycleCount(7);
					wrongLoginMove.play();
					wrongLoginMoveB.play();
					usernameField.clear();
					passwordField.clear();
				}
			}
		});
	}

}
