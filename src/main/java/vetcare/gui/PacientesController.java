package vetcare.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vetcare.api.ApiApplication;

public class PacientesController {
	@FXML
	private TextField searchField;

	@FXML
	private void autoSearch() {
		var query = searchField.getText();
		//ApiApplication.pacientes.
		//System.out.println(searchField);
	}
}
