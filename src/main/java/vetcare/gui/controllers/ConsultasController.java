package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Veterinario;
import vetcare.gui.BaseUserController;
import vetcare.gui.CirclePictureFrame;
import vetcare.gui.ListCard;
import vetcare.gui.VetCareApp;

public class ConsultasController extends BaseUserController {
	@FXML private TextField searchField;
	@FXML private VBox veterinarios;
	@FXML private BorderPane fichaVeterinario;
	@FXML ImageView vetPhotoImage;
	@FXML private Text vetName;
	@FXML private Text vetKind, vetCRMV;
	@FXML TextField vetNameField;
	Veterinario selectedVet;

	private static final String[] animalPictures = new String[] {
			"turtle.jpeg", "cat1.png", "cat2.png", "dog1.png", "dog2.png", "fish.png"
	};

	public void initialize() {
		doSearch();
	}

	public static String getVetPicture(Veterinario vet) {

		//int index = (int)(Math.random() * animalPictures.length);
		int index = vet.getCrmvVet().charAt(0) % animalPictures.length;
		var pic = animalPictures[index];
		var resource = PacientesController.class.getResource("/vetcare/gui/Images/" + pic).toExternalForm();
		return resource;
	}

	private Node criarVet(Veterinario vet) {
		var imageUrl = getVetPicture(vet);
		var name = vet.getNomeVet();
		var desc = vet.getTipoVet() + " - " + vet.getCrmvVet();

		var card = new ListCard(imageUrl, name, desc);
		card.setOnMouseClicked(ev -> {
			this.selecionarVet(vet);
		});
		card.getStyleClass().add("veterinario");
		return card;
	}

	private void selecionarVet(Veterinario vet) {
		this.selectedVet = vet;

		// Card do veterinário
		vetPhotoImage.setImage(new Image(getVetPicture(vet)));

		vetName.setText(vet.getNomeVet());
		vetCRMV.setText(vet.getCrmvVet());
		vetKind.setText(vet.getTipoVet());

		vetNameField.setText(vet.getNomeVet());

		fichaVeterinario.setVisible(true);
	}

	@FXML
	private void doSearch() {
		// Busca no banco de dados os pets com esse nome
		var query = searchField.getText();
		var vets = ApiApplication.consultas.listarTodosVeterinarios();

		// Limpa a lista de pets, e para cada pet no resultado, crie um elemento na lista de pets
		veterinarios.getChildren().clear();
		for (Veterinario vet : vets) {
			var elem = criarVet(vet);
			veterinarios.getChildren().add(elem);
		}
	}

	@FXML
	void saveData() {

	}

	@FXML
	void resetData() {

	}
}
