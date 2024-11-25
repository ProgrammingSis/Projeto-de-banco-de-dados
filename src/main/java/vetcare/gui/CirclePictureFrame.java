package vetcare.gui;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class CirclePictureFrame extends StackPane {
	//public final Image image;
	private final StringProperty imageUrl = new SimpleStringProperty();
	private final IntegerProperty radius = new SimpleIntegerProperty();


	public CirclePictureFrame() {}

	public void setImageUrl(String img) {
		imageUrl.set(img);
		reinit();
	}

	public String getImageUrl() {
		return imageUrl.get();
	}

	public StringProperty imageUrlProperty() {
		return imageUrl;
	}

	public void setRadius(int r) {
		radius.set(r);
		reinit();
	}

	public int getRadius() {
		return radius.get();
	}

	public IntegerProperty radiusProperty() {
		return radius;
	}
	public void reinit() {
		if (imageUrl.get() == null) return;
		if (radius.get() == 0) return;

		var image = new Image(imageUrl.get());
		int r = radius.get();
		int h = r / 2;

		setPrefSize(r, r);
		setClip(new Circle(h, h, h));

		var imgView = new ImageView(image);

		imgView.setPreserveRatio(true);
		imgView.setFitWidth(r);
		imgView.setFitHeight(r);

		getChildren().add(imgView);

		fit(image, this, imgView);
	}

	private void fit(Image image, Pane imagePane, ImageView imageViewPrincipal) {
		double oldImageWidth = image.getWidth(), oldImageHeight = image.getHeight();            //saving the original image size and ratio
		double imageRatio = oldImageWidth / oldImageHeight;

		ChangeListener<Number> listener = (obs, ov, nv) -> {
			double paneWidth = imagePane.getWidth();
			double paneHeight = imagePane.getHeight();

			double paneRatio = paneWidth / paneHeight;                                          //calculating the new pane's ratio
			//after width or height changed
			double newImageWidth = oldImageWidth, newImageHeight = oldImageHeight;

			if (paneRatio > imageRatio) {
				newImageHeight = oldImageWidth / paneRatio;
			} else if (paneRatio < imageRatio) {
				newImageWidth = oldImageHeight * paneRatio;
			}

			imageViewPrincipal.setViewport(new Rectangle2D(                                     // The rectangle used to crop
					(oldImageWidth - newImageWidth) / 2, (oldImageHeight - newImageHeight) / 2, //MinX and MinY to crop from the center
					newImageWidth, newImageHeight)                                              // new width and height
			);

			imageViewPrincipal.setFitWidth(paneWidth);
		};

		imagePane.widthProperty().addListener(listener);
		imagePane.heightProperty().addListener(listener);
	}
}
