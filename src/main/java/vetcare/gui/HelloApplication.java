package vetcare.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

	//Creates the scene using the screen manager
	@Override
	public void start(Stage stage) throws Exception {
		ScreenManager screenManager = new ScreenManager(stage);
		screenManager.switchScreen("/vetcare/gui/initialScene.fxml");
	}
//starts the gui application
	public static void init(String[] args) {
		launch(args);
	}
}
