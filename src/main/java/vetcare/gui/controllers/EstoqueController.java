package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Insumo;
import vetcare.api.model.entities.Cliente;
import vetcare.gui.BaseUserController;
import vetcare.gui.ListCard;

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
}
