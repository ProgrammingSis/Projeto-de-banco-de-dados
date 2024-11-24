package vetcare.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartSceneController {
    @FXML
    private Button iniciarSessaoButton;

    public void initialize() {
        iniciarSessaoButton.setOnAction(event -> switchToAnotherScene());
    }

    //Switches the scene as soon as the button "Iniciar Sessão" is clicked
    @FXML
    public void switchToAnotherScene(){
        Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
        ScreenManager screenManager = new ScreenManager(stage);
        screenManager.switchScreen("/vetcare/gui/Scenes/mainMenu.fxml");
    }
}