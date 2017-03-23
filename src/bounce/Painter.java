package bounce;

import java.awt.Color;
import java.awt.Image;

/**
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Ian Warren
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);

	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);

	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);

	/**
	 * Fills a rectangle with a given color.Parameters x and y specify the top
	 * left corner of the oval. Parameters width and height specify its width
	 * and height.
	 */
	public abstract void fillRect(int x, int y, int width, int height);
	
	/**
	 * Gets this graphics context's current color. Takes no parameters.
	 */
	public abstract Color getColor();
	
	/**
	 * Sets this graphics conntext's current color to the specified color. 
	 */
	public abstract void setColor(Color color);
	
	public abstract void drawImage(Image image, int x, int y, int width, int height);

	public void translate(int _x, int _y);
	
	public void drawCentredText(String text, int x, int y);
	
}
