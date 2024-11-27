package vetcare.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import vetcare.api.ApiApplication;
import vetcare.api.model.entities.Cliente;
import vetcare.gui.BaseUserController;
import vetcare.gui.ListCard;

public class FinanceiroController extends BaseUserController {
    @FXML private TextField searchField;
    @FXML private VBox clientes;
    @FXML private BorderPane fichaCliente;
    @FXML private Text clienteNome;
    @FXML private Text clienteContato;
    @FXML private Text clienteCpf;
    @FXML private ImageView clientePhotoImage;

    @FXML private TextField clienteNomeField;
    @FXML private TextField clienteId;
    @FXML private TextField contatoCliente;

    private Cliente cliente;

    private static final String[] clientesPictures = new String[] {
            "a.jpeg", "b.jpeg", "c.jpeg", "d.jpeg", "e.jpeg"
    };

    public void initialize() {
        doSearch();
    }

    public static String getPicture(Cliente cliente) {

        //int index = (int)(Math.random() * clientesPictures.length);
        int index = (int)(Math.random() % clientesPictures.length);
        var pic = clientesPictures[index];
        var resource = PacientesController.class.getResource("/vetcare/gui/Images/clientes/" + pic).toExternalForm();
        return resource;
    }

    private void selecionarCliente(Cliente cliente) {
        this.cliente = cliente;

        // Card do cliente
        clienteNome.setText(cliente.getNomeCliente());
        clienteContato.setText(cliente.getContatoCliente() + " - " + cliente.getEndCliente());
        clientePhotoImage.setImage(new Image(getPicture(cliente)));

        // Propriedades editáveis
        clienteNomeField.setText(cliente.getNomeCliente());
        clienteId.setText(cliente.getCpfCliente());
        contatoCliente.setText(String.valueOf((cliente.getContatoCliente())));
        fichaCliente.setVisible(true);

    }


    private Node criarCliente(Cliente cliente) {
        var imageUrl = getPicture(cliente);
        var name = cliente.getNomeCliente();
        var desc = cliente.getContatoCliente() + " - " + cliente.getCpfCliente();

        var card = new ListCard(imageUrl, name, desc);
        card.setOnMouseClicked(ev -> {
            this.selecionarCliente(cliente);
        });
        card.getStyleClass().add("cliente");
        return card;
    }

    @FXML
    private void doSearch() {
        // Busca no banco de dados
        var query = searchField.getText();
        var client = ApiApplication.financeiro.buscaClientePorNome(query);

        // Limpa a lista e cria elemento
        clientes.getChildren().clear();
        for (Cliente cliente : client) {
            var elem = criarCliente(cliente);
            clientes.getChildren().add(elem);
        }
    }

    @FXML
    private void saveData() {
        this.cliente.setNomeCliente(clienteNomeField.getText());
        this.cliente.setContatoCliente((clienteId.getText()));
        this.cliente.setCpfCliente(contatoCliente.getText());
        ApiApplication.clientes.updateCliente(this.cliente);
    }

    @FXML
    private void resetData() {
        selecionarCliente(this.cliente);
    }

    @FXML
    void novoPet() {
        clienteNomeField.setText("");
        contatoCliente.setText("");
    }
}