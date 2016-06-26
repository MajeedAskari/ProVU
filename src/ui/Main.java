package ui;

import ui.UiController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		UiController uic = new UiController(primaryStage);
		uic.loginControl();
	}

	public static void main(String[] args) {
		System.out.println("run");
		launch(args);

	}
}