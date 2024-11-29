package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Insumo;
import vetcare.api.model.entities.Cliente;
import vetcare.gui.BaseUserController;
import vetcare.gui.ListCard;
import vetcare.gui.VetCareApp;

public class EstoqueController extends BaseUserController {
    @FXML private TextField searchField;
    @FXML private VBox insumos;
    @FXML private BorderPane fichaInsumo;
    @FXML private Text insumoName;
    @FXML private Text insumoKind;
    @FXML private Text insumoValue;
    @FXML private ImageView insumoPhotoImage;

    @FXML private TextField insumoNomeField;
    @FXML private TextField insumoTypeField;
    @FXML private TextField insumoQuantidadeField;

    private Insumo insumo;

    private static final String[] insumosPictures = new String[] {
            "a.png", "b.png"
    };

    public void initialize() {
        doSearch();
    }

    public static String getPicture(Insumo insumo) {

        //int index = (int)(Math.random() * insumosPictures.length);
        int index = (int)(Math.random() % insumosPictures.length);
        var pic = insumosPictures[index];
        var resource = PacientesController.class.getResource("/vetcare/gui/Images/insumos/" + pic).toExternalForm();
        return resource;
    }

    private void selecionarInsumo(Insumo insumo) {
        this.insumo = insumo;

        // Card do insumo
        insumoName.setText(insumo.getNomeInsumo());
        insumoKind.setText(insumo.getTipoInsumo() + " - " + insumo.getValorInsumo());
        insumoPhotoImage.setImage(new Image(getPicture(insumo)));

        // Propriedades editáveis
        insumoNomeField.setText(insumo.getNomeInsumo());
        insumoTypeField.setText(insumo.getTipoInsumo());
        insumoQuantidadeField.setText(String.valueOf((insumo.getQuantInsumo())));
        fichaInsumo.setVisible(true);

    }


    private Node criarInsumo(Insumo insumo) {
        var imageUrl = getPicture(insumo);
        var name = insumo.getNomeInsumo();
        var desc = insumo.getTipoInsumo() + " - " + insumo.getValorInsumo();

        var card = new ListCard(imageUrl, name, desc);
        card.setOnMouseClicked(ev -> {
            this.selecionarInsumo(insumo);
        });
        card.getStyleClass().add("insumo");
        return card;
    }

    @FXML
    private void doSearch() {
        // Busca no banco de dados
        var query = searchField.getText();
        var insum = ApiApplication.estoque.buscarInsumoPorNome(query);

        // Limpa a lista e cria elemento
        insumos.getChildren().clear();
        for (Insumo insumo : insum) {
            var elem = criarInsumo(insumo);
            insumos.getChildren().add(elem);
        }
    }

    @FXML
    private void saveData() {
        this.insumo.setNomeInsumo(insumoNomeField.getText());
        this.insumo.setTipoInsumo((insumoTypeField.getText()));
        this.insumo.setQuantInsumo(Long.valueOf(insumoQuantidadeField.getText()));
        ApiApplication.estoque.atualizarInsumo(this.insumo);
    }

    @FXML
    private void resetData() {
        selecionarInsumo(this.insumo);
    }

    @FXML
    void novoPet() {
        insumoNomeField.setText("");
        insumoQuantidadeField.setText("");
    }

    @FXML
    private void abrirAdicionarInsumo() {
        var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/adicionarinsumo.fxml");

        try {
            Parent root = loader.load();
            AdicionarInsumoController controller = loader.getController();

            controller.initialize(insumo);

            // Exibir o popup
            Stage stage = new Stage();
            stage.setTitle("Adicionar Insumo");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao abrir o popup de insumo.", e);
        }
    }

    @FXML
    private void excluirInsumo() {
        var loader = VetCareApp.screens.getLoaderFor("/vetcare/gui/Scenes/excluirinsumo.fxml");

        try {
            // Carregar o popup de confirmação
            Parent root = loader.load();
            ExcluirInsumoController controller = loader.getController();
            controller.initialize("Tem certeza que deseja excluir o insumo \"" + insumo.getNomeInsumo() + "\"?");

            // Exibir o popup
            Stage stage = new Stage();
            stage.setTitle("Confirmação de Exclusão");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Verificar a resposta do usuário
            if (controller.isConfirmado()) {
                // Lógica para excluir o insumo do banco de dados
                System.out.println("Excluindo insumo: " + insumo.getNomeInsumo());
                ApiApplication.estoque.deletarInsumo(insumo.getCdInsumo());
                // insumoDao.excluir(insumo.getCdInsumo());
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao abrir o popup de confirmação de exclusão.", e);
        }
    }
}