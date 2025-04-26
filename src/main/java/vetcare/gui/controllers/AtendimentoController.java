package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Atendimento;
import vetcare.api.model.entities.Cliente;

public class AtendimentoController {
	Atendimento atendimento;
	@FXML private TextField atTipo;
	@FXML private TextField atData;
	@FXML private TextField atHorario;

	public void defAtendimento(Atendimento at) {
		this.atendimento = at;

		atTipo.setText(at.getTipoAtendimento());
		atData.setText(at.getDate().toString());
		var hora = at.getHorario();
		if (hora != null) {
			atHorario.setText(at.getHorario().toString());
		}
	}

	@FXML
	private void salvarDados() {
		/*cliente.setNomeCliente(donoNome.getText());
		cliente.setContatoCliente(donoContato.getText());
		cliente.setEndCliente(donoEndereco.getText());
		ApiApplication.clientes.updateCliente(cliente);*/
	}
}
