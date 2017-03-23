package bounce;

/**
 * Class to represent a simple rectangle.
 * 
 * @author Ian Warren
 * 
 */
public class GemShape extends Shape {
	/**
	 * Default constructor that creates a GemShape instance whose instance
	 * variables are set to default values.
	 */
	public GemShape() {
		super();
	}

	/**
	 * Creates a GemShape instance with specified values for instance variables.
	 * 
	 * @param x
	 *            x position.
	 * @param y
	 *            y position.
	 * @param deltaX
	 *            speed and direction for horizontal axis.
	 * @param deltaY
	 *            speed and direction for vertical axis.
	 */
	public GemShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	/**
	 * Creates a GemShape instance with specified values for instance variables.
	 * 
	 * @param x
	 *            x position.
	 * @param y
	 *            y position.
	 * @param deltaX
	 *            speed (pixels per move call) and direction for horizontal
	 *            axis.
	 * @param deltaY
	 *            speed (pixels per move call) and direction for vertical axis.
	 * @param width
	 *            width in pixels.
	 * @param height
	 *            height in pixels.
	 */
	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, height, height, text);
	}

	/**
	 * Paints a diamond if the shape’s width is < 40 pixels, otherwise a hexagon
	 * is painted. using the supplied Painter object.
	 */
	public void doPaint(Painter painter) {
		// lines draw starting at the top of the hexagon and going around
		// clockwise
		if (_width >= 40) {
			painter.drawLine(_x + 20, _y, _x + _width - 20, _y); // draw the top
			painter.drawLine(_x + _width - 20, _y, _x + _width, _y + _height
					/ 2);
			painter.drawLine(_x + _width, _y + _height / 2, _x + _width - 20,
					_y + _height);
			painter.drawLine(_x + 20, _y + _height, _x + _width - 20, _y
					+ _height);
			painter.drawLine(_x + 20, _y + _height, _x, _y + _height / 2);
			painter.drawLine(_x, _y + _height / 2, _x + 20, _y);
		} else {
			painter.drawLine(_x + _width / 2, _y, _x + _width, _y + _height / 2);
			painter.drawLine(_x + _width, _y + _height / 2, _x + _width / 2, _y
					+ _height);
			painter.drawLine(_x + _width / 2, _y + _height, _x, _y + _height
					/ 2);
			painter.drawLine(_x, _y + _height / 2, _x + _width / 2, _y);
		}

	}
}
