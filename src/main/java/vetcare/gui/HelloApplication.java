package vetcare.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ScreenManager screenManager = new ScreenManager(stage);
		screenManager.switchScreen("/vetcare/gui/telaInicio.fxml");
	}

	public static void init(String[] args) {
		launch(args);
	}
}
