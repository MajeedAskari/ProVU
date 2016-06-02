package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {


		UiController uic = new UiController(primaryStage);
		uic.loginControl();
	}

	public static void main(String[] args) {
		System.out.println("run");
		launch(args);
		// DataBase.getDataBase().loadFromFile();
		// DataBase B = DataBase.getDataBase();
		// B.setTest('h');
		// B.saveToFile();
		// System.out.println(B.getTest());
	}
}