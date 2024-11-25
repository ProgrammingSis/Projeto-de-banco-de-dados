package vetcare.gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class CirclePictureFrame extends StackPane {
	public final Image image;

	public CirclePictureFrame(Image image, int r) {
		this.image = image;

		int h = r / 2;

		setPrefSize(r, r);
		setClip(new Circle(h, h, h));

		var img = new ImageView(image);

		img.setPreserveRatio(true);
		img.setFitWidth(r);
		img.setFitHeight(r);

		getChildren().add(img);

		fit(image, this, img);
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
