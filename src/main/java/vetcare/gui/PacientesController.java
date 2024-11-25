package vetcare.gui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.dto.AnimalClienteDTO;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.Cliente;


public class PacientesController {
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

	private Animal selectedPet;
	private Cliente selectedPetOwner;

	private static final String[] animalPictures = new String[] {"Images/turtle.jpeg", "Images/cat1.png", "Images/cat2.png", "Images/dog1.png", "Images/dog2.png", "Images/fish.png"};

	public void initialize() {
		doSearch();
	}

	public static String getPetPicture(Animal pet) {

		//int index = (int)(Math.random() * animalPictures.length);
		int index = pet.getIdPet() % animalPictures.length;
		var pic = animalPictures[index];
		var resource = PacientesController.class.getResource(pic).toExternalForm();
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

		petNameField.setText(pet.getNomePet());
		petWeightField.setText(pet.getPesoPet().toString());
		petRaceField.setText((pet.getRacaPet()));
		fichaPaciente.setVisible(true);
	}

	private Node criarPet(Animal pet) {
		var root = new HBox();
		root.setOnMouseClicked(ev -> {
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

		root.getChildren().add(desc);

		return root;
	}

	@FXML void goHome() {
		VetCareApp.screens.switchScreen("/vetcare/gui/Scenes/mainMenu.fxml");
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
}
