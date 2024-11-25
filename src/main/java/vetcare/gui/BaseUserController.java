package vetcare.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;

public class BaseUserController {
	@FXML private Node menuLateral;

	@FXML
	void goHome() {
		VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/mainMenu.fxml");
	}

	@FXML
	void abrirMenuLateral() {
		menuLateral.setVisible(true);
	}

	@FXML
	void fecharMenuLateral() {
		menuLateral.setVisible(false);
	}
}
