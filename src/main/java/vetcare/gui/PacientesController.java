package vetcare.gui;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;


public class PacientesController {
	@FXML private ImageView logoBtn;
	@FXML private TextField searchField;
	@FXML private VBox pacientes;
	@FXML private Text animalName;
	@FXML private Text animalKind;
	@FXML private Text animalOwner;
	@FXML private ImageView petPhotoImage;

	@FXML private TextField petNameField;
	@FXML private TextField petWeightField;
	@FXML private TextField petRaceField;

	private Animal selectedPet;

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

	private void selecionarPet(Animal pet) {
		this.selectedPet = pet;

		animalName.setText(pet.getNomePet());
		animalKind.setText(pet.getTipoPet() + " - " + pet.getRacaPet());
		animalOwner.setText(pet.getCpfDonoPet());
		petPhotoImage.setImage(new Image(getPetPicture(pet)));

		petNameField.setText(pet.getNomePet());
		petWeightField.setText(pet.getPesoPet().toString());
		petRaceField.setText((pet.getRacaPet()));
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
		var dono = ApiApplication.pacientes.buscaPetDetalhes(selectedPet.getIdPet());

		var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/dono.fxml");

		try {
			Parent root = loader.load();
			DonoController controller = loader.getController();
			controller.setCliente(dono);

			Stage stage = new Stage();
			Scene scene = new Scene(root, 300,400);
			VetCareApp.screens.addGlobalStyles(scene);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
