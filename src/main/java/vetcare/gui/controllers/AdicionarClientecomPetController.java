package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.Cliente;
import vetcare.gui.BaseUserController;

public class AdicionarClientecomPetController extends BaseUserController {

    // Campos do Cliente
    @FXML
    private TextField cpfClienteField;
    @FXML
    private TextField nomeClienteField;
    @FXML
    private TextField enderecoClienteField;
    @FXML
    private TextField contatoClienteField;

    // Campos do Animal
    @FXML
    private TextField idPetField;
    @FXML
    private TextField nomePetField;
    @FXML
    private TextField racaPetField;
    @FXML
    private TextField pesoPetField;
    @FXML
    private ComboBox<String> tipoPetCombo;

    // Botões
    @FXML
    private Button salvarButton;
    @FXML
    private Button cancelarButton;

    @FXML
    void initialize() {
        // Preencher o ComboBox com opções de tipo de pet
        tipoPetCombo.getItems().addAll("Cachorro", "Gato", "Pássaro", "Outro");
        salvarButton.setOnAction(e -> salvarDados());
        cancelarButton.setOnAction(e -> fecharJanela());
    }

    @FXML
    private void salvarDados() {
        // Recuperar informações do cliente
        Cliente cliente = new Cliente();
        cliente.cpfCliente = cpfClienteField.getText();
        cliente.nomeCliente = nomeClienteField.getText();
        cliente.endCliente = enderecoClienteField.getText();
        cliente.contatoCliente = contatoClienteField.getText();

        // Recuperar informações do animal
        Animal animal = new Animal();
        animal.idPet = Integer.parseInt(idPetField.getText());
        animal.nomePet = nomePetField.getText();
        animal.racaPet = racaPetField.getText();
        animal.pesoPet = Double.parseDouble(pesoPetField.getText());
        animal.cpfDonoPet = cliente.cpfCliente; // Associar o animal ao dono
        animal.tipoPet = tipoPetCombo.getValue();

        // Exemplo de saída (substitua pelo método de persistência ou outra lógica)
        ApiApplication.clientes.addCliente(cliente);
        ApiApplication.pacientes.adicionaPet(animal);
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) salvarButton.getScene().getWindow();
        stage.close();
    }
}
