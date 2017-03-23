package bounce;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;



/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Ian Warren
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
	}

	/**
	 * @see bounce.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * @see bounce.Painter.fillRect
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
	}
	
	/**
	 * @see bounce.Painter.getColor
	 */
	public Color getColor() {
		return _g.getColor();
	}
	
	/**
	 * @see bounce.painter.setColor 
	 */
	public void setColor(Color color) {
		_g.setColor(color);
	}
	
	public void drawImage(Image img, int x, int y, int width, int height){
		_g.drawImage(img, x, y, width, height, null );
		
	}
	
	public void translate(int x, int y) {
		_g.translate(x, y);
	}
	
	public void drawCentredText(String str, int x, int y){
		
		FontMetrics fontMetrics = _g.getFontMetrics();
		
		if(fontMetrics.getAscent() > fontMetrics.getDescent()){
				y += (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
		}else if (fontMetrics.getAscent() > fontMetrics.getDescent()){
			y -= (fontMetrics.getDescent() - fontMetrics.getAscent()) / 2;
		}
		
		x -= fontMetrics.stringWidth(str) / 2;
		_g.drawString(str, x, y);
		
		/*
		FontMetrics fontMetrics = _g.getFontMetrics();
		int width = fontMetrics.stringWidth(str);
		int x_mod = (int) (x - width / 2);
		int ascent = fontMetrics.getAscent();
		int descent = fontMetrics.getDescent();
		int y_mod = (int) (y + (ascent - descent) / 2);
		_g.drawString(str, x_mod, y_mod);
		*/
	}
}
