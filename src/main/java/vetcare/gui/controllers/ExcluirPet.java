package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vetcare.gui.BaseUserController;

public class ExcluirPet extends BaseUserController {
    @FXML
    private Label mensagemLabel;
    @FXML private Button confirmarButton;
    @FXML private Button cancelarButton;

    private boolean confirmado;

    public void initialize(String mensagem) {
        mensagemLabel.setText(mensagem);

        confirmarButton.setOnAction(e -> {
            confirmado = true;
            fecharJanela();
        });

        cancelarButton.setOnAction(e -> {
            confirmado = false;
            fecharJanela();
        });
    }

    private void fecharJanela() {
        Stage stage = (Stage) confirmarButton.getScene().getWindow();
        stage.close();
    }

    public boolean isConfirmado() {
        return confirmado;
    }
}
