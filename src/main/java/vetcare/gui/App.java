package vetcare.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	//Creates the scene using the screen manager
	@Override
	public void start(Stage stage) {
		ScreenManager screenManager = new ScreenManager(stage);
		screenManager.switchScreen("/vetcare/gui/Scenes/startScreen.fxml");
	}

	// Starts the gui application
	public static void init(String[] args) {
		launch(args);
	}
}
