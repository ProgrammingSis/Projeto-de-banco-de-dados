package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Veterinario;
import vetcare.gui.BaseUserController;

public class AdicionarVeterinarioController extends BaseUserController {

    @FXML
    private TextField textCrmv;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textContato;

    @FXML
    private Button salvarButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private ComboBox<String> especialidade;


    /**
     * Inicializa o controlador e define as ações dos botões.
     */
    @FXML
    public void initialize() {

        especialidade.getItems().clear();
        especialidade.getItems().addAll(
                "Clínica Geral",
                "Cirurgia Geral",
                "Dermatologia",
                "Anestesiologia",
                "Cardiologia",
                "Odontologia",
                "Ortopedia",
                "Radiologia Veterinária");
        salvarButton.setOnAction(event -> salvarVeterinario());
        cancelarButton.setOnAction(event -> fecharJanela());
    }

    /**
     * Salva um novo veterinário com os dados preenchidos no formulário.
     */
    private void salvarVeterinario() {
        try {
            if (validarCampos()) {
                Veterinario novoVeterinario = new Veterinario();
                novoVeterinario.setCrmvVet(textCrmv.getText());
                novoVeterinario.setNomeVet(textNome.getText());
                novoVeterinario.setTipoVet(especialidade.getValue());
                novoVeterinario.setContato(textContato.getText());

                // Salvar o veterinário usando a API
                boolean sucesso = ApiApplication.consultas.adicionarVeterinario(novoVeterinario);
            }
        } catch (Exception e) {
            mostrarErro("Erro ao salvar o veterinário: " + e.getMessage());
        }
    }



    /**
     * Valida os campos do formulário para garantir que estão preenchidos corretamente.
     *
     * @return true se todos os campos forem válidos; false caso contrário.
     */
    private boolean validarCampos() {
        if (textCrmv.getText().isEmpty() || textNome.getText().isEmpty() ||
                textContato.getText().isEmpty()) {
            mostrarErro("Preencha todos os campos!");
            return false;
        }
        return true;
    }

    /**
     * Exibe uma mensagem de erro em um alerta.
     *
     * @param mensagem Mensagem de erro a ser exibida.
     */
    private void mostrarErro(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Erro ao salvar veterinário");
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    /**
     * Fecha a janela atual.
     */
    private void fecharJanela() {
        Stage stage = (Stage) textCrmv.getScene().getWindow();
        stage.close();
    }
}
