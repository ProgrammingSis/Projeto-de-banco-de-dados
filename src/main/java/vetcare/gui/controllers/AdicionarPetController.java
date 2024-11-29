package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;
import vetcare.gui.BaseUserController;

public class AdicionarPetController extends BaseUserController {

    @FXML
    private TextField textNome;

    @FXML
    private TextField textPeso;

    @FXML
    private TextField textRaca;

    @FXML
    private TextField textId;

    @FXML
    private ComboBox<String> comboEspeciePet;

    @FXML
    private Button salvarButton;

    @FXML
    private Button cancelarButton;

    private String idPet;

    /**
     * Inicializa o controlador e define as opções do ComboBox.
     */
    @FXML
    public void initializePet(String cpfCliente) {
        comboEspeciePet.getItems().addAll("Iguana", "Cão", "Gato", "Coelho", "Hamster",
                "Porquinho-da-Índia", "Papagaio", "Calopsita","Tartaruga");
        salvarButton.setOnAction(event -> salvarPet(cpfCliente));
        cancelarButton.setOnAction(event -> fecharJanela());
    }

    /**
     * Salva uma nova fatura com os dados preenchidos no formulário.
     */
    private void salvarPet(String cpfCliente) {
        try {
            if (validarCampos()) {
                Animal novoAnimal = new Animal();
                novoAnimal.setNomePet(textNome.getText());
                novoAnimal.setPesoPet(Double.valueOf(textPeso.getText()));
                novoAnimal.setRacaPet(String.valueOf(textRaca.getText()));
                novoAnimal.setIdPet(Integer.valueOf((textId.getText())));

                // Salvar a fatura usando a API
                ApiApplication.pacientes.adicionaPet(novoAnimal);

                fecharJanela();
            }
        } catch (Exception e) {
            // Você pode adicionar um alerta ou log para tratar exceções.
            System.err.println("Erro ao salvar a fatura: " + e.getMessage());
        }
    }

    /**
     * Valida os campos do formulário para garantir que estão preenchidos corretamente.
     *
     * @return true se todos os campos forem válidos; false caso contrário.
     */
    private boolean validarCampos() {
        if (textPeso.getText().isEmpty() || textRaca.getText() == null
                || textId.getText() == null || textNome.getText() == null) {

            System.err.println("Preencha todos os campos!");
            return false;
        }
        try {
            Double.parseDouble(textPeso.getText());
        } catch (NumberFormatException e) {
            System.err.println("O valor total deve ser numérico!");
            return false;
        }
        return true;
    }

    /**
     * Fecha a janela atual.
     */
    @FXML
    private void fecharJanela() {
        Stage stage = (Stage) textId.getScene().getWindow();
        stage.close();
    }

}
