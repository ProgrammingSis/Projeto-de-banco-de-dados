package vetcare.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.gui.controllers.AtendimentoController;

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

	public final void abrirAtendimento(long atendimentoId) {
		var atendimento = ApiApplication.atendimentos.getAtendimentoById(atendimentoId);
		var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/atendimento.fxml");

		try {
			Parent root = loader.load();
			AtendimentoController controller = loader.getController();
			controller.defAtendimento(atendimento);

			Stage stage = new Stage();
			stage.setTitle("Atendimento");
			stage.setWidth(300);
			stage.setHeight(400);
			Scene scene = new Scene(root);
			VetCareApp.screens.addGlobalStyles(scene);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
