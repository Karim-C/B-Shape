package bounce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Simple GUI program to show an animation of shapes. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * AnimationViewer can be added to a JFrame object. A JFrame object is a 
 * window that can be closed, minimised, and maximised. The state of an
 * AnimationViewer object comprises a list of Shapes and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates 
 * through a list of Shapes requesting that each Shape paints and moves itself.
 * 
 * @author Ian Warren
 * 
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds for the Timer to generate events.
	private static final int DELAY = 20;

	// Collection of Shapes to animate.
	private List<Shape> _shapes;

	private Timer _timer = new Timer(DELAY, this);

	/**
	 * Creates an AnimationViewer instance with a list of Shape objects and 
	 * starts the animation.
	 */
	public AnimationViewer() {
		_shapes = new ArrayList<Shape>();
	
		// Populate the list of Shapes.
		
		//_shapes.add(new RectangleShape(0, 0, 2, 3));
		/*
		
		_shapes.add(new OvalShape(1, 17, 5, 2));
		_shapes.add(new GemShape(40, 40, 3, 3));
		_shapes.add(new GemShape(100, 50, 5, 2, 50, 50));
		//_shapes.add(new DynamicRectangleShape(null));
		//_shapes.add(new DynamicRectangleShape(10, 90, -12, 15, Color.red));
		
		_shapes.add(new DynamicRectangleShape(10, 20, 4, 5, Color.green));
		
		*/
		
		
		
		
		NestingShape topLevelNest = new NestingShape(0, 0, 2, 2, 100, 100);
		NestingShape midLevelNest = new NestingShape(0, 0, 2, 2, 50, 50);
		NestingShape bottomLevelNest = new NestingShape(5, 5, 2, 2, 10, 10);
		RectangleShape simpleShape = new RectangleShape(1, 1, 1, 1, 5, 5, "hello world");
		
	
		
		midLevelNest.add(bottomLevelNest);
		midLevelNest.add(simpleShape);
		topLevelNest.add(midLevelNest);
		
		
		_shapes.add(topLevelNest);
		
		//_shapes.add(new ImageShape(40, 40, 3, 3, 100, 100,"Kirby"));
		_shapes.add(new RectangleShape(50, 50, 1, 3, 100, 100,"hello world"));
		
		/*		
			int originalHeigth = 600;
			int originalWidth = 600;
			NestingShape nS = new NestingShape(0, 0, 2, 1, originalWidth, originalHeigth);
			_shapes.add(nS);
		for (int i = 0; i < 200; i++){
			NestingShape nS2 = new NestingShape(0, 0, 1, 2, originalWidth - 20*i, originalHeigth-20*i);
			nS.add(nS2);
			nS = nS2;
		}
		*/
	
		// Start the animation.
		_timer.start();
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		// Calculate bounds of animation screen area.
		int width = getSize().width;
		int height = getSize().height;
		
		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);
		
		// Progress the animation.
		for(Shape s : _shapes) {
			s.paint(painter);
			s.move(width, height);
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent. ActionEvents are
	 * received by the Timer.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to 
		// repaint() will cause the AnimationViewer's paintComponent() method 
		// to be called.
		repaint();
	}
	
	
	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Animation viewer");
				frame.add(new AnimationViewer());
		
				// Set window properties.
				frame.setSize(500, 500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
