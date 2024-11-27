package vetcare.gui.controllers;

import javafx.fxml.FXML;
import vetcare.gui.VetCareApp;

public class MainMenuController {

	@FXML
	public void goPacientes() {
		VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/pacientes.fxml");
	}

	@FXML
	public void goConsultas() {
		VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/consultas.fxml");
	}

	@FXML
	public void goEstoque() {
		VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/estoque.fxml");
	}

	@FXML
	public void goFinanceiro() {
		VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/financeiro.fxml");
	}



}
