package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.cglib.core.Local;
import vetcare.api.ApiApplication;
import vetcare.api.model.dto.ConsultaDTO;
import vetcare.api.model.entities.Veterinario;
import vetcare.gui.*;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ConsultasController extends BaseUserController {

	@FXML private TextField searchField;
	@FXML private VBox veterinarios;
	@FXML private BorderPane fichaVeterinario;
	@FXML ImageView vetPhotoImage;
	@FXML private Text vetName;
	@FXML private Text vetKind, vetCRMV;
	@FXML TextField vetNameField, vetContato;
	Veterinario selectedVet;
	@FXML
	GridPane tabConsultas;
	@FXML DatePicker dataInicio;
	@FXML
	DatePicker dataFim;

	@FXML
	private Button cadastro;

	@FXML
	void cadastrar(){

		// chamar pop up
		var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/adicionarVeterinario.fxml");

		try {
			// Carregar o FXML e obter o controlador associado
			Parent root = loader.load();
			AdicionarVeterinarioController controller = loader.getController();

			// Configurar o CPF do cliente no controlador
			controller.initialize();

			// Configurar e exibir o pop-up
			Stage stage = new Stage();
			stage.setTitle("Adicionar Vet");
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao carregar o popup de Adicionar Vet", e);
		}
	}

	@FXML
	private void excluirVeterinario() {
		var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/scenes/excluirveterinario.fxml");

		try {
			Stage popupStage = new Stage();
			popupStage.setScene(new Scene(loader.load()));
			popupStage.setTitle("Confirmar Exclusão");

			ExcluirVeterinarioController controller = loader.getController();
			controller.initialize("Tem certeza que deseja excluir o veterinário \"" + selectedVet.getCrmvVet() + "\"?");


			popupStage.showAndWait();

			// Verificar a resposta do usuário
			if (controller.isConfirmado()) {
				// Lógica para excluir do banco de dados
				System.out.println("Excluindo veterinário: " + selectedVet.getNomeVet());
				ApiApplication.consultas.removerVeterinario(selectedVet.getCrmvVet());
			} else {
				System.out.println("Exclusão cancelada.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static final String[] animalPictures = new String[] {
			"vetA.jpeg", "vetAsi.jpg", "vetPro.jpg", "vetTatuada.jpeg", "vetB.jpg", "vetC.jpg", "vetD.jpg"
	};

	public void initialize() {
		doSearch();
		dataInicio.setOnAction(ev -> {
			selecionarVet(selectedVet);
		});
		dataFim.setOnAction(ev -> {
			selecionarVet(selectedVet);
		});
	}



	public static String getVetPicture(Veterinario vet) {

		//int index = (int)(Math.random() * animalPictures.length);
		String crmv = vet.getCrmvVet();
		int index = crmv.substring(crmv.length() - 1).charAt(0) % animalPictures.length;
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
		tabConsultas.getChildren().clear();
		this.selectedVet = vet;
		var crmv = vet.getCrmvVet();

		// Card do veterinário
		vetPhotoImage.setImage(new Image(getVetPicture(vet)));

		vetName.setText(vet.getNomeVet());
		vetCRMV.setText(vet.getCrmvVet());
		vetKind.setText(vet.getTipoVet());

		vetNameField.setText(vet.getNomeVet());
		String contato = vet.getContato();
		if (contato == null || contato.isEmpty()) {
			vetContato.setText("veterinario@gmail.com");
		} else {
			vetContato.setText(contato);
		}


		fichaVeterinario.setVisible(true);

		var inicio = dataInicio.getValue();
		var fim = dataFim.getValue();

		if (inicio == null) {
			inicio = LocalDate.parse("1970-01-01");
		}
		if (fim == null) {
			fim = LocalDate.parse("3000-01-01");
		}

		var consultas = ApiApplication.consultas.mostrarCalendarioVeterinario(crmv, inicio.toString(), fim.toString());

		// Cabeçalho da tabela de vacinas

		var tipoCab = new StackPane(new Text("Animal"));
		tipoCab.getStyleClass().add("table-header-cell");
		var dataCab = new StackPane(new Text("Data"));
		dataCab.getStyleClass().add("table-header-cell");
		var vetCab = new StackPane(new Text("Cliente"));
		vetCab.getStyleClass().add("table-header-cell");
		var empty = new StackPane();
		empty.getStyleClass().add("table-header-cell");
		tabConsultas.addRow(0, tipoCab, dataCab, vetCab, empty);

		// Lista todos os atendimentos
		int row = 1;

		for (var co : consultas) {
			var animalText = new Text(co.getNomeAnimal());
			animalText.getStyleClass().add("link");
			var tipo = new StackPane(animalText);
			tipo.getStyleClass().addAll("table-cell");
			tipo.setOnMouseClicked(ev -> {
				//abrirAtendimento(co.getIdAtendimento());
			});

			var data = new StackPane(new Text(co.getData().toString()));
			data.getStyleClass().add("table-cell");

			var vetText = new Text(co.getNomeCliente());
			vetText.getStyleClass().add("link");
			var cli = new StackPane(vetText);
			cli.getStyleClass().add("table-cell");

			var notifT = new Button("Notificar");
			var notif = new StackPane(notifT);
			notif.getStyleClass().add("table-cell");
			tabConsultas.addRow(row++, tipo, data, cli, notif);

			notifT.setOnAction(ev -> {
				ApiApplication.atendimentos.agendarNotificacoes();
			});
		}
	}

	@FXML
	private void doSearch() {
		// Busca no banco de dados os pets com esse nome
		var query = searchField.getText();
		var vets = ApiApplication.consultas.listarTodosVeterinarios(query);

		// Limpa a lista de pets, e para cada pet no resultado, crie um elemento na lista de pets
		veterinarios.getChildren().clear();
		for (Veterinario vet : vets) {
			var elem = criarVet(vet);
			veterinarios.getChildren().add(elem);
		}
	}

	@FXML
	void saveData() {
		selectedVet.setNomeVet(vetNameField.getText());
		selectedVet.setContato(vetContato.getText());
		ApiApplication.consultas.atualizarVeterinario(selectedVet);
	}

	@FXML
	void resetData() {

	}
}



