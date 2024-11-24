package vetcare.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class VetCareApp extends Application {

	public static ScreenManager screens;

	//Creates the scene using the screen manager
	@Override
	public void start(Stage stage) {
		screens = new ScreenManager(stage);
		screens.switchScreen("/vetcare/gui/Scenes/startScreen.fxml");
	}

	//starts the gui application
	public static void init(String[] args) {
		launch(args);
	}
}
