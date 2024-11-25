package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;

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
			var text = new Text(vec.getIdVacina());
			listaVacinas.getChildren().add(text);
		}
	}

	@FXML
	private void sendNotification() {
		ApiApplication.pacientes.notificarVacinasPendentes(pet.getIdPet());
	}
}
