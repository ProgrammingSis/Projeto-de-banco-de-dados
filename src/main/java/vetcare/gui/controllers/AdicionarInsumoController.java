package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Insumo;
import vetcare.gui.BaseUserController;

import java.sql.Date;

public class AdicionarInsumoController extends BaseUserController {
    @FXML private TextField nomeInsumoField;
    @FXML private TextField cdInsumoField;
    @FXML private TextField valorInsumoField;
    @FXML private ComboBox<String> tipoInsumoBox;
    @FXML private TextField quantInsumoField;
    @FXML private DatePicker datePicker;
    @FXML private Button salvarButton;
    @FXML private Button cancelarButton;

    private Insumo insumo;

    public void initialize(Insumo insumo) {
        // Configurar ComboBox com opções para tipo de insumo
        tipoInsumoBox.getItems().clear();
        tipoInsumoBox.getItems().addAll(
                "Administrativo",
                "Limpeza",
                "Acessórios para Pets",
                "Alimentos para Pets",
                "Higiene para Pets",
                "Exames e Diagnósticos",
                "Médicos"
        );

        // Configurar ações dos botões
        salvarButton.setOnAction(e -> salvarInsumo(insumo));
        cancelarButton.setOnAction(e -> fecharJanela());
    }

    private void salvarInsumo(Insumo insumo) {
        try {
            String nomeInsumo = nomeInsumoField.getText();
            String cdInsumo = cdInsumoField.getText();
            Double valorInsumo = Double.parseDouble(valorInsumoField.getText());
            String tipoInsumo = tipoInsumoBox.getValue();
            Long quantInsumo = Long.parseLong(quantInsumoField.getText());
            Date date = Date.valueOf(datePicker.getValue());

            // Criar o objeto Insumo e preencher os dados
            insumo = new Insumo();
            insumo.setNomeInsumo(nomeInsumo);
            insumo.setCdInsumo(cdInsumo);
            insumo.setValorInsumo(valorInsumo);
            insumo.setTipoInsumo(tipoInsumo);
            insumo.setQuantInsumo(quantInsumo);
            insumo.setDate(date);

            ApiApplication.financeiro.addInsumo(insumo);

            fecharJanela();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Preencha todos os campos corretamente.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void fecharJanela() {
        Stage stage = (Stage) salvarButton.getScene().getWindow();
        stage.close();
    }

}
