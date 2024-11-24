package vetcare.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;

import java.util.Arrays;

public class Main extends Application {
  @Override
    public void start(Stage primaryStage) throws Exception {
        var res = getClass().getResource("hellofx.fxml");
        Parent root = FXMLLoader.load(res);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        ApiApplication.initApi(new String[]{});

        System.out.println(Arrays.toString(args));
        launch(args);
    }
}
