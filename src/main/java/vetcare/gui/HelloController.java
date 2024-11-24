package vetcare.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Button iniciarSessaoButton;

    public void initialize() {
        iniciarSessaoButton.setOnAction(event -> switchToAnotherScene());
    }

    //Switches the scene as soon as the button "Iniciar Sessão" is clicked
    @FXML
    public void switchToAnotherScene(){
        ScreenManager screenManager = new ScreenManager(new Stage());
        screenManager.switchScreen("/vetcare/gui/hello-view.fxml");
    }
}