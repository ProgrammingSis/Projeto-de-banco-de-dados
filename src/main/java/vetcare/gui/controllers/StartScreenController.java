package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vetcare.gui.VetCareApp;

public class StartScreenController {
    @FXML
    private Button iniciarSessaoButton;

    public void initialize() {
        iniciarSessaoButton.setOnAction(event -> switchToAnotherScene());
    }

    //Switches the scene as soon as the button "Iniciar Sessão" is clicked
    @FXML
    public void switchToAnotherScene() {
        VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/mainMenu.fxml");
    }
}