package bounce;

import java.awt.Color;


/**
 * Class to represent a Dynamic rectangle.
 * 
 * @author 
 * 
 */
public class DynamicRectangleShape extends Shape {
	
	
	
	/**
	 * Default constructor that creates a DynamicRectangleShape instance whose instance
	 * variables are set to default values.
	 */
	public DynamicRectangleShape() {
		super();
	}
	
	public DynamicRectangleShape(String text) {
		super(text);
	}
	
	public DynamicRectangleShape(Color color) {
		super(color);
	}
	
	/**
	 * Creates a DynamicRectangleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, Color color) {
		super(x,y,deltaX,deltaY, color);
	}
	/**
	 * Creates a DynamicRectangleShape instance with specified values for instance 
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
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color, String text) {
		super(x,y,deltaX,deltaY,width,height, color, text);
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
		super(x,y,deltaX,deltaY,width,height, color, text);
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x, y, deltaX, deltaY, width, height, color);
	}

	private void colorChange(Painter painter){
		if (_bounceX){
			painter.fillRect(_x, _y, _width, _height);
		}
	}
	
	/**
	 * Paints this DynamicRectangleShape object using the supplied Painter object the color is dependent on which side the shape bounces off of.
	 */
	public void doPaint(Painter painter) { 
		Color originalColor = painter.getColor();
		painter.setColor(_color);
		painter.drawRect(_x,_y,_width,_height);
		colorChange(painter);
		painter.setColor(originalColor);
	}
}