package vetcare.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ListCard extends HBox {
	public ListCard(String imageUrl, String nameText, String descText) {
		getStyleClass().add("list-card");
		setSpacing(4);
		setAlignment(Pos.CENTER_LEFT);

		var imgRoot = new CirclePictureFrame();
		imgRoot.setImageUrl(imageUrl);
		imgRoot.setRadius(64);
		//imgRoot.initialize();

		var desc = new VBox();
		desc.setAlignment(Pos.CENTER_LEFT);
		var nome = new Text(nameText);
		nome.getStyleClass().add("list-card-name");

		var obs = new Text(descText);
		obs.getStyleClass().add("list-card-desc");
		desc.getChildren().addAll(nome, obs);

		getChildren().addAll(imgRoot, desc);
	}
}
