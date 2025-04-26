package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Insumo;

public class InsumosVencidosController {

    @FXML
    private VBox insumosVBox; // VBox onde os itens serão listados

    @FXML
    public void visualizarProximosAVencer() {
        // Busca insumos no banco de dados que estão próximos a vencer
        var insumos = ApiApplication.estoque.buscarTodosInsumosAVencer();

        // Verifica se a lista de insumos é null ou vazia
        if (insumos == null) {
            var mensagemLabel = new javafx.scene.control.Label("Não existem insumos próximos a vencer nos próximos 7 dias.");
            insumosVBox.getChildren().clear(); // Limpa a lista para garantir que não há outros itens
            insumosVBox.getChildren().add(mensagemLabel); // Adiciona a mensagem
            return;
        }

        // Limpa a lista antes de preenchê-la
        insumosVBox.getChildren().clear();

        // Adiciona os insumos encontrados na lista
        for (Insumo insumo : insumos) {
            var elem = criarInsumo(insumo);
            insumosVBox.getChildren().add(elem);
        }
    }



    private VBox criarInsumo(Insumo insumo) {
        // Cria um elemento para exibir o insumo
        VBox insumoBox = new VBox();
        insumoBox.setSpacing(5);

        var nomeLabel = new javafx.scene.control.Label("Nome: " + insumo.getNomeInsumo());
        var dataLabel = new javafx.scene.control.Label("Data de validade: " + insumo.getDate());
        var tipoLabel = new javafx.scene.control.Label("Tipo: " + insumo.getTipoInsumo());

        insumoBox.getChildren().addAll(nomeLabel, dataLabel, tipoLabel);

        insumoBox.setStyle("-fx-padding: 10; -fx-border-color: gray; -fx-border-width: 1; -fx-border-radius: 5;");
        return insumoBox;
    }
}
