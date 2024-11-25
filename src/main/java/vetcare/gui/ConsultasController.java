package vetcare.gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.Veterinario;

public class ConsultasController {
	@FXML private TextField searchField;
	@FXML private VBox pacientes;

	public void initialize() {
		doSearch();
	}

	@FXML
	void goHome() {
		VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/mainMenu.fxml");
	}

	private Node criarVet(Veterinario vet) {
		var root = new HBox();
		/*root.setOnMouseClicked(ev -> {
			this.selecionarPet(pet);
		});
		root.getStyleClass().add("paciente");
		root.setAlignment(Pos.CENTER_LEFT);

		var image = new Image(getPetPicture(pet));
		var imgRoot = new CirclePictureFrame(image, 64);
		root.getChildren().add(imgRoot);

		var desc = new VBox();
		var nome = new Text(pet.getNomePet());
		nome.getStyleClass().add("paciente-nome");

		var obs = new Text(pet.getTipoPet() + " - " + pet.getRacaPet());
		obs.getStyleClass().add("paciente-obs");
		desc.getChildren().addAll(nome, obs);

		root.getChildren().add(desc);*/

		return root;
	}

	@FXML
	private void doSearch() {
		// Busca no banco de dados os pets com esse nome
		var query = searchField.getText();
		var pets = ApiApplication.consultas.buscarVeterinarioPorCrmv(query);

		// Limpa a lista de pets, e para cada pet no resultado, crie um elemento na lista de pets
		/*pacientes.getChildren().clear();
		for (Veterinario vet : vets) {
			var elem = criarVet(vet);
			pacientes.getChildren().add(elem);
		}*/
	}
}
