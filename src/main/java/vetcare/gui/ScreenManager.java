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

    public void switchScreen(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 1200, 650));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela: " + fxmlFile);
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar a tela: " + fxmlFile, e);
        } catch (NullPointerException e) {
            System.err.println("Arquivo FXML não encontrado: " + fxmlFile);
            e.printStackTrace();
            throw new RuntimeException("Arquivo FXML não encontrado: " + fxmlFile, e);
        }
    }
}
