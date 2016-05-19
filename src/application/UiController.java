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

	public static void control(Parent root){
		Pane loginPane = (Pane) root.lookup("#loginPane");
		loginPane.setStyle("-fx-background-color: transparent;");
		Button loginBt = (Button) root.lookup("#loginBt");
		//Timeline wrongLoginMove = new Timeline(
		//		new KeyFrame(Duration.millis(100),new javafx.animation.KeyValue(loginPane.layoutXProperty(),480,Interpolator.EASE_BOTH))
		//		);
		//wrongLoginMove.setCycleCount(10000);

		loginBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TextField usernameField = (TextField) root.lookup("#username");
				PasswordField passwordField = (PasswordField) root.lookup("#password");
				if (!Login.login(usernameField.getText(), passwordField.getText())){	
					//wrongLoginMove.play();
					usernameField.clear();
					passwordField.clear();
				}
			}
		});
	}
	
	
	
}
