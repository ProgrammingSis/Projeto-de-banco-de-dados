package vetcare.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import vetcare.api.model.dto.AnimalClienteDTO;
import vetcare.api.model.entities.Cliente;

public class DonoController {
	AnimalClienteDTO cliente;
	@FXML private TextField donoNome;
	@FXML private TextField donoCPF;
	@FXML private TextField donoContato;

	public void setCliente(AnimalClienteDTO dono) {
		this.cliente = dono;
		donoNome.setText(dono.getNomeCliente());
		donoCPF.setText(dono.getCpfDonoPet());
		donoContato.setText(dono.getContatoCliente());
	}
}
