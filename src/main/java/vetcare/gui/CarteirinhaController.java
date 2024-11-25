package vetcare.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;

public class CarteirinhaController {
	private Animal pet;

	@FXML private Text animalName;
	@FXML private Text animalKind;
	@FXML private Text animalOwner;
	@FXML private ImageView petPhotoImage;
	@FXML private Button sendNotificationBtn;
	@FXML private Text statusText;

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
	}

	@FXML
	private void sendNotification() {
		ApiApplication.pacientes.notificarVacinasPendentes(pet.getIdPet());
	}
}
