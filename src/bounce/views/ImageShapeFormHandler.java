package bounce.views;

/*
 import bounce.NestingShape;
 import bounce.ShapeModel;
 import bounce.forms.util.Form;
 import bounce.forms.util.FormHandler;

 public class ImageShapeFormHandler implements FormHandler{


 public ImageShapeFormHandler(ShapeModel model, NestingShape parent) {
 // TODO Auto-generated constructor stub
 }

 @Override
 public void processForm(Form form) {
 // TODO Auto-generated method stub

 }

 }
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import bounce.ImageRectangleShape;
import bounce.NestingShape;
import bounce.ShapeModel;
import bounce.forms.ImageFormElement;
import bounce.forms.ShapeFormElement;
import bounce.forms.util.Form;
import bounce.forms.util.FormHandler;

public class ImageShapeFormHandler implements FormHandler {

	private ShapeModel _model;
	private NestingShape _parentOfNewShape;

	public ImageShapeFormHandler(ShapeModel model, NestingShape parent) {
		_model = model;
		_parentOfNewShape = parent;
	}

	@Override
	public void processForm(Form form) {
		File imageFile = (File) form.getFieldValue(File.class,
				ImageFormElement.IMAGE);
		int width = form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
		int deltaX = form
				.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
		int deltaY = form
				.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);
		
		// anonymous inner SwingWorker class 
		SwingWorker<BufferedImage, Void> worker = new SwingWorker<BufferedImage, Void>() {

			@Override
			protected BufferedImage doInBackground() throws Exception {

				long startTime = System.currentTimeMillis();

				// Read field values from the form.

				// Load the original image (ImageIO.read() is a blocking call).
				BufferedImage fullImage = null;
				try {
					fullImage = ImageIO.read(imageFile);
				} catch (IOException e) {
					System.out.println("Error loading image.");
				}

				int fullImageWidth = fullImage.getWidth();
				int fullImageHeight = fullImage.getHeight();

				BufferedImage scaledImage = fullImage;

				// Scale the image if necessary.
				if (fullImageWidth > width) {
					double scaleFactor = (double) width
							/ (double) fullImageWidth;
					int height = (int) ((double) fullImageHeight * scaleFactor);

					scaledImage = new BufferedImage(width, height,
							BufferedImage.TYPE_INT_RGB);
					Graphics2D g = scaledImage.createGraphics();

					// Method drawImage() scales an already loaded image. The
					// ImageObserver argument is null because we don't need to
					// monitor
					// the scaling operation.
					g.drawImage(fullImage, 0, 0, width, height, null);

					// Create the new Shape and add it to the model.

				}

				long elapsedTime = System.currentTimeMillis() - startTime;
				System.out.println("Image loading ans scaling took "
						+ elapsedTime + "ms.");

				return scaledImage;
			}

			@Override
			public void done() {

				try {
					BufferedImage i = get();

					ImageRectangleShape imageShape = new ImageRectangleShape(
							deltaX, deltaY, i);
					_model.add(imageShape, _parentOfNewShape);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		worker.execute();

	}
}
