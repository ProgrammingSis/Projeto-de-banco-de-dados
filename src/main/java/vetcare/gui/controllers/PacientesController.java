package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.Cliente;
import vetcare.gui.BaseUserController;
import vetcare.gui.CirclePictureFrame;
import vetcare.gui.ListCard;
import vetcare.gui.VetCareApp;

import java.util.Stack;


public class PacientesController extends BaseUserController {
	@FXML private TextField searchField;
	@FXML private VBox pacientes;
	@FXML private BorderPane fichaPaciente;
	@FXML private Text animalName;
	@FXML private Text animalKind;
	@FXML private Text animalOwner;
	@FXML private ImageView petPhotoImage;

	@FXML private TextField petNameField;
	@FXML private TextField petWeightField;
	@FXML private TextField petRaceField;

	@FXML private GridPane tabVacinas;
	private Animal selectedPet;
	private Cliente selectedPetOwner;

	private static final String[] animalPictures = new String[] {
			"turtle.jpeg",  "cat1.png", "cat2.png", "dog1.png", "dog2.png", "fish.png"
	};

	public void initialize() {
		doSearch();
	}

	public static String getPetPicture(Animal pet) {

		//int index = (int)(Math.random() * animalPictures.length);
		int index = pet.getIdPet() % animalPictures.length;
		var pic = animalPictures[index];
		var resource = PacientesController.class.getResource("/vetcare/gui/Images/" + pic).toExternalForm();
		return resource;
	}

	public static String getOwnerString(Cliente owner) {
		var cpf = owner.getCpfCliente();
		cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
				cpf.substring(6, 9) + "-" + cpf.substring(9);
		return owner.getNomeCliente() + " (" + cpf + ")";
	}

	private void selecionarPet(Animal pet) {
		this.selectedPet = pet;
		var owner = ApiApplication.clientes.getClienteByCpf(pet.getCpfDonoPet());
		this.selectedPetOwner = owner;

		// Card do animal
		animalName.setText(pet.getNomePet());
		animalKind.setText(pet.getTipoPet() + " - " + pet.getRacaPet());
		animalOwner.setText(getOwnerString(owner));
		petPhotoImage.setImage(new Image(getPetPicture(pet)));

		// Propriedades editáveis
		petNameField.setText(pet.getNomePet());
		petWeightField.setText(pet.getPesoPet().toString());
		petRaceField.setText((pet.getRacaPet()));
		fichaPaciente.setVisible(true);

		// Cabeçalho da tabela de vacinas
		tabVacinas.getChildren().clear();
		var tipoCab = new StackPane(new Text("Descrição"));
		tipoCab.getStyleClass().add("table-header-cell");
		var dataCab = new StackPane(new Text("Data"));
		dataCab.getStyleClass().add("table-header-cell");
		var vetCab = new StackPane(new Text("Veterinário"));
		vetCab.getStyleClass().add("table-header-cell");
		tabVacinas.addRow(0, tipoCab, dataCab, vetCab);

		// Lista todos os atendimentos
		var atendimentos = ApiApplication.atendimentos.getAllAtendimentosPet(pet.getIdPet());
		int row = 1;

		for (var at : atendimentos) {
			var tipoText = new Text(at.getTipoAtendimento());
			tipoText.getStyleClass().add("link");
			var tipo = new StackPane(tipoText);
			tipo.getStyleClass().addAll("table-cell");
			tipo.setOnMouseClicked(ev -> {
				abrirAtendimento(at.getIdAtendimento());
			});

			var data = new StackPane(new Text(at.getDate().toString()));
			data.getStyleClass().add("table-cell");

			var vetText = new Text(at.getCrmvVet());
			vetText.getStyleClass().add("link");
			var vet = new StackPane(vetText);
			vet.getStyleClass().add("table-cell");

			tabVacinas.addRow(row++, tipo, data, vet);
		}
	}

	private Node criarPet(Animal pet) {
		var imageUrl = getPetPicture(pet);
		var name = pet.getNomePet();
		var desc = pet.getTipoPet() + " - " + pet.getRacaPet();

		var card = new ListCard(imageUrl, name, desc);
		card.setOnMouseClicked(ev -> {
			this.selecionarPet(pet);
		});
		card.getStyleClass().add("paciente");

		return card;
	}

	@FXML
	private void doSearch() {
		// Busca no banco de dados os pets com esse nome
		var query = searchField.getText();
		var pets = ApiApplication.pacientes.buscaPetNome(query);

		// Limpa a lista de pets, e para cada pet no resultado, crie um elemento na lista de pets
		pacientes.getChildren().clear();
		for (Animal pet : pets) {
			var elem = criarPet(pet);
			pacientes.getChildren().add(elem);
		}
	}

	@FXML
	private void abrirCarteirinha() {
		var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/carteirinha.fxml");

		try {
			Parent root = loader.load();
			CarteirinhaController controller = loader.getController();
			controller.setPet(selectedPet);

			Stage stage = new Stage();
			stage.setTitle("Carteirinha");
			Scene scene = new Scene(root, 300,400);
			VetCareApp.screens.addGlobalStyles(scene);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	private void abrirDono() {
		// Obtém o dono desse pet
		var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/dono.fxml");

		try {
			Parent root = loader.load();
			DonoController controller = loader.getController();
			controller.setCliente(this.selectedPetOwner);

			Stage stage = new Stage();
			stage.setTitle("Dono");
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

	@FXML
	private void saveData() {
		this.selectedPet.setNomePet(petNameField.getText());
		this.selectedPet.setPesoPet(Double.parseDouble(petWeightField.getText()));
		this.selectedPet.setRacaPet(petRaceField.getText());
		ApiApplication.pacientes.atualizaPet(this.selectedPet);
	}

	@FXML
	private void resetData() {
		selecionarPet(this.selectedPet);
	}

	@FXML
	void novoPet() {
		petNameField.setText("");
		petRaceField.setText("");
	}
}
