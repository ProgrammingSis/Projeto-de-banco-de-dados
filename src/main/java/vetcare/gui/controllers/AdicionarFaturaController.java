package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.Cliente;
import vetcare.api.model.entities.Fatura;
import vetcare.api.model.entities.VacinaPet;
import vetcare.gui.BaseUserController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class AdicionarFaturaController extends BaseUserController {

    @FXML
    private TextField textCpf;

    @FXML
    private TextField textValor;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField id;

    @FXML
    private ComboBox<String> comboPagamento;

    @FXML
    private Button salvarButton;

    @FXML
    private Button cancelarButton;

    private String clienteCpf;

    /**
     * Inicializa o controlador e define as opções do ComboBox.
     */
    @FXML
    public void initialize(String clienteCpf) {
        comboPagamento.getItems().addAll("Cartão de Crédito", "Cartão de Débito", "Pix", "Dinheiro");
        salvarButton.setOnAction(event -> salvarFatura(clienteCpf));
        cancelarButton.setOnAction(event -> fecharJanela());
    }

    /**
     * Salva uma nova fatura com os dados preenchidos no formulário.
     */
    private void salvarFatura(String clienteCpf) {
        try {
            if (validarCampos()) {
                Fatura novaFatura = new Fatura();
                novaFatura.setClienteCpf(clienteCpf);
                novaFatura.setValorTotal(Double.parseDouble(textValor.getText()));
                novaFatura.setDate(Date.valueOf(datePicker.getValue()));
                novaFatura.setIdFatura(Integer.valueOf((id.getText())));
                novaFatura.setFormaPagamento(comboPagamento.getValue());

                // Salvar a fatura usando a API
                ApiApplication.financeiro.adicionarFatura(novaFatura);

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
        if (textValor.getText().isEmpty() || datePicker.getValue() == null || comboPagamento.getValue() == null) {
            System.err.println("Preencha todos os campos!");
            return false;
        }
        try {
            Double.parseDouble(textValor.getText());
        } catch (NumberFormatException e) {
            System.err.println("O valor total deve ser numérico!");
            return false;
        }
        return true;
    }

    /**
     * Fecha a janela atual.
     */
    private void fecharJanela() {
        Stage stage = (Stage) textCpf.getScene().getWindow();
        stage.close();
    }

}
