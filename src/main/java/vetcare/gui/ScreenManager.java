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

    public Scene getSceneFor(String fxml) {
        try {
            FXMLLoader loader = getLoaderFor(fxml);
            Parent root = loader.load();

            Scene scene = new Scene(root);
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
        Scene scene = getSceneFor(fxmlFile);
        stage.setScene(scene);
        stage.setWidth(1600);
        stage.setHeight(830);
        stage.show();
    }
}
