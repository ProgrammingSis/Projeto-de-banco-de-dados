package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Cliente;

public class DonoController {
	Cliente cliente;
	@FXML private TextField donoNome;
	@FXML private TextField donoCPF;
	@FXML private TextField donoContato;
	@FXML private TextField donoEndereco;

	public void setCliente(Cliente dono) {
		this.cliente = dono;
		donoNome.setText(dono.getNomeCliente());
		donoCPF.setText(dono.getCpfCliente());
		donoContato.setText(dono.getContatoCliente());
		donoEndereco.setText(dono.getEndCliente());
	}

	@FXML
	private void saveData() {
		cliente.setNomeCliente(donoNome.getText());
		cliente.setContatoCliente(donoContato.getText());
		cliente.setEndCliente(donoEndereco.getText());
		ApiApplication.clientes.updateCliente(cliente);
	}
}
