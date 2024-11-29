package vetcare.gui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class VetCareApp extends Application {

	public static ScreenManager screens;

	//Creates the scene using the screen manager
	@Override
	public void start(Stage stage) {
		stage.setTitle("VetCare");
		screens = new ScreenManager(stage);
		screens.switchScreen("/vetcare/gui/Scenes/startScreen.fxml");
		Image appIcon = new Image(getClass().getResourceAsStream("/vetcare/gui/Images/a.jpg"));
		stage.getIcons().add(appIcon);
	}

	//starts the gui application
	public static void init(String[] args) {
		launch(args);
	}
}
