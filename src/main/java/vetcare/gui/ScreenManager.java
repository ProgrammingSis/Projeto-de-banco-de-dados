package vetcare.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private final Stage stage;

    public ScreenManager(Stage stage) {
        this.stage = stage;
    }

    public FXMLLoader getLoaderFor(String fxml) {
        return new FXMLLoader(getClass().getResource(fxml));
    }

    public Scene getSceneFor(String fxml, int w, int h) {
        try {
            FXMLLoader loader = getLoaderFor(fxml);
            Parent root = loader.load();

            Scene scene = new Scene(root, w, h);
            addGlobalStyles(scene);
            return scene;
        } catch(Exception err) {
            throw new RuntimeException(err);
        }
    }

    public void addGlobalStyles(Scene scene) {
        scene.getStylesheets().add(getClass().getResource("/vetcare/gui/styles.css").toExternalForm());
    }

    public void switchScreen(String fxmlFile) {
        Scene scene = getSceneFor(fxmlFile, 1200, 650);
        stage.setScene(scene);
        stage.show();
    }
}
