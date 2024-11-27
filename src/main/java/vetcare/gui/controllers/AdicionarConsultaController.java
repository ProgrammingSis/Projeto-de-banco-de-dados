package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.dto.AtendimentoPetDTO;
import vetcare.api.model.dto.ConsultaDTO;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class AdicionarConsultaController {
    @FXML private TextField idAtendimentoField;
    @FXML private DatePicker dataPicker;
    @FXML private TextField horarioField;
    @FXML private TextField idAnimalField;
    @FXML private TextField crmvVeterinarioField;
    @FXML private ComboBox<String> tipoAtendimentoBox;
    @FXML private Button salvarButton;
    @FXML private Button cancelarButton;

    private AtendimentoPetDTO atendimentoPetDTO;

    public void initialize(Integer animalId) {
        // Configurar ComboBox com opções para tipo de atendimento
        tipoAtendimentoBox.getItems().addAll("Consulta de rotina", "Vacinação", "Castração", "Procedimento Odontológico",
                "Exames laboratoriais", "Exames de imagem", "Enfermaria", "Desparasitação", "Cirurgia", "Atendimento de emergência", "Internação", "Compra");

        // Configurar ação do botão salvar
        salvarButton.setOnAction(e -> salvarConsulta(animalId));

        // Configurar ação do botão cancelar
        cancelarButton.setOnAction(e -> fecharJanela());
    }

    private void salvarConsulta(Integer animalId) {
        try {
            Integer idAtendimento = Integer.valueOf((idAtendimentoField.getText()));
            LocalDate data = dataPicker.getValue();
            LocalTime horario = LocalTime.parse(horarioField.getText());
//            int idAnimal = Integer.parseInt(idAnimalField.getText());
            String crmvVeterinario = crmvVeterinarioField.getText();
            String tipoAtendimento = tipoAtendimentoBox.getValue();

            // Criar a consulta e preencher os dados
            atendimentoPetDTO = new AtendimentoPetDTO();
            atendimentoPetDTO.setIdAtendimento(idAtendimento);
            atendimentoPetDTO.setDate(Date.valueOf(data));
            atendimentoPetDTO.setHorario(Time.valueOf(horario));
            atendimentoPetDTO.setIdPet(animalId);
            atendimentoPetDTO.setCrmvVet(crmvVeterinario);
            atendimentoPetDTO.setTipoAtendimento(tipoAtendimento);

            // Salvar a fatura usando a API
            ApiApplication.consultas.criarConsulta(atendimentoPetDTO);


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
