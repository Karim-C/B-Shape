package bounce;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Class to represent a simple rectangle.
 * 
 * @author Ian Warren
 * 
 */
//This class is no longer in use -------------------------------------------------------------------------------------------
public class ImageShape extends Shape {
	/**
	 * Default constructor that creates a RectangleShape instance whose instance
	 * variables are set to default values.
	 */
	public ImageShape() {
		super();
	}
	
	public ImageShape(String text) {
		super(text);
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public ImageShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	/**
	 * Creates a RectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public ImageShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
	
	public ImageShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height, text);
	}
	/**
	 * Paints this RectangleShape object using the supplied Painter object.
	 */
	public void doPaint(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
		Image img = null;
		try{
			img = ImageIO.read( new File("kirby.jpg" ));
		}catch(IOException ioe) {
			
		}
		painter.drawImage(img, _x, _y, _width, _height);
	}
}

