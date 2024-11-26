package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.VacinaPet;
import vetcare.gui.VetCareApp;

import java.time.LocalDate;
import java.time.Period;

public class CarteirinhaController {
	Animal pet;

	@FXML Text animalName;
	@FXML Text animalKind;
	@FXML Text animalOwner;
	@FXML ImageView petPhotoImage;
	@FXML Button sendNotificationBtn;
	@FXML Text statusText;
	@FXML VBox listaVacinas;

	void setPet(Animal pet) {
		this.pet = pet;

		animalName.setText(pet.getNomePet());
		animalKind.setText(pet.getTipoPet() + " - " + pet.getRacaPet());
		animalOwner.setText(pet.getCpfDonoPet());
		petPhotoImage.setImage(new Image(PacientesController.getPetPicture(pet)));

		var pendencias = ApiApplication.pacientes.vacinasPendentes(pet.getIdPet());
		sendNotificationBtn.setDisable(!pendencias);

		if (pendencias) {
			statusText.setText("Esse animal possui vacinas pendentes!");
		} else {
			statusText.setText("Esse animal não possui vacinas pendentes.");
		}

		// Preenche a lista de vacinas
		var vacinas = ApiApplication.pacientes.buscarVacinasPet(pet.getIdPet());
		for (var vec : vacinas) {
			var data = vec.getDataVacina();
			var text = new Text(vec.getIdVacina() + " - " + data.toString());
			text.getStyleClass().add("link");
			text.setOnMouseClicked(ev -> {
				abrirAtendimento(vec.getIdAtendimento());
			});

			if (vacinaExpirada(vec)) {
				text.setStyle("-fx-fill: red");
			}

			listaVacinas.getChildren().add(text);
		}
	}

	private void abrirAtendimento(long atendimentoId) {
		var atendimento = ApiApplication.atendimentos.getAtendimentoById(atendimentoId);
		var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/atendimento.fxml");

		try {
			Parent root = loader.load();
			AtendimentoController controller = loader.getController();
			controller.defAtendimento(atendimento);

			Stage stage = new Stage();
			stage.setTitle("Atendimento");
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

	private boolean vacinaExpirada(VacinaPet vacina) {
		var data = vacina.getDataVacina();
		var pass = data.toLocalDate();
		var agora = LocalDate.now();
		var tempo = Period.between(pass, agora);
		var anos = tempo.getYears();
		System.out.println(anos);
		return anos >= 1;
	}

	@FXML
	private void sendNotification() {
		ApiApplication.pacientes.notificarVacinasPendentes(pet.getIdPet());
	}
}
