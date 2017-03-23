package bounce;

import java.util.ArrayList;
import java.util.List;

public class NestingShape extends Shape{
	
	private ArrayList<Shape> nestingShapes;

	/**
	* Creates a NestingShape object with default values for state. 
	* */ 
	public NestingShape() {
		super();
		nestingShapes = new ArrayList<Shape>();
	}
	
	/**
	* Creates a NestingShape object with specified location values,
	* default values for other state items. 
	*/ 
	public NestingShape( int x , int y){
		super(x, y);
		nestingShapes = new ArrayList<Shape>();
	}
			
	/**
	* Creates a NestingShape with specified values for location, velocity
    * and direction. Non-specified state items take on default values. 
    */
	public NestingShape( int x , int y , int deltaX , int deltaY ) {
		super(x, y, deltaX, deltaY);
		nestingShapes = new ArrayList<Shape>();
	}
	
	/** 
	 * Creates a NestingShape with specified values for location , velocity ,
	 * direction, width and height. 
	 */ 
	public NestingShape( int x , int y , int deltaX , int deltaY ,int width , int height ) {
		super(x, y, deltaX, deltaY, width, height);
		nestingShapes = new ArrayList<Shape>();
	}
	
	public NestingShape( int x , int y , int deltaX , int deltaY ,int width , int height, String text ) {
		super(x, y, deltaX, deltaY, width, height, text);
		nestingShapes = new ArrayList<Shape>();
	}
	/**
	 * Moves a NestingShape object (including its children) within the bounds 
	 * specified by arguments width and height. 
	 */
	public void move( int width , int height ) {
		super.move(width, height);
		for (Shape nS : nestingShapes ) {
			nS.move(_width, _height);
		}
	}
		
	 /**
	  * Paints a NestingShape object by drawing a rectangle around the edge of 
	  * its bounding box. The NestingShape object’s children are then painted. 
	  */ 
	 public void doPaint(Painter painter) {
		 painter.drawRect(_x, _y, _width, _height);
		 painter.translate(_x, _y);
		 for (Shape nS : nestingShapes) {
			 nS.paint(painter);
		 }
		 painter.translate(-_x, -_y);
	 }
	 
	/**
	* Attempts to add a Shape to a NestingShape object . If successful , a 
	* two-way link is established between the NestingShape and the newly 
	* added Shape. Note that this method has package visibility - for reasons 
	* that will become apparent in Bounce III. 
	* @param shape the shape to be added. 
	* @throws IllegalArgumentException if an attempt is made to add a Shape 
	* to a NestingShape instance where the Shape argument is already a child
	* within a NestingShape instance. An IllegalArgumentException is also 
	* thrown when an attempt is made to add a Shape that will not fit within 
	* the bounds of the proposed NestingShape object. 
	*/ 
	void add(Shape shape) throws IllegalArgumentException {
		
		if (shape._parent != null) {
			throw new IllegalArgumentException();
		}

		if ((shape._width + shape._x > this._width + this._x) || (shape._height + shape._x > this._height + this._y)){ // logic might need more
			throw new IllegalArgumentException();
		}
		shape._parent = this;
		nestingShapes.add(shape);
		
	}
	
	/**
	 * Removes a particular Shape from a NestingShape instance. Once removed, 
	 * the two-way link between the NestingShape and its former child is 
	 * destroyed. This method has no effect if the Shape specified to remove 
	 * is not a child of the NestingShape. Note that this method has package 
	 * visibility - for reasons that will become apparent in Bounce III. 
	 * @param shape the shape to be removed.
	 */ 
	void remove(Shape shape){
		for(Shape s : nestingShapes){
			if (s == shape){
				nestingShapes.remove(shape);
				shape._parent = null;
				return;
			}
		}
		return;
	}
	
	/**
	 * Returns the Shape at a specified position within a NestingShape. If 
	 * the position specified is less than zero or greater than the number of 
	 * children stored in the NestingShape less one this method throws an 
	 * IndexOutOfBoundsException . 
	 * @param index the specified index position. 
	 */ 
	public Shape shapeAt( int index) throws IndexOutOfBoundsException {
		return nestingShapes.get(index);
	}
	
	/**
	 * Returns the number of children contained within a NestingShape object. 
	 * Note this method is not recursive - it simply returns the number of 
	 * children at the top level within the callee NestingShape object. 
	 * */ 
	public int shapeCount() {
		return nestingShapes.size();
		/*
		int numShapes = 0;
		for (Shape s : nestingShapes) {
			numShapes += 1;
		}
		return numShapes;
		*/
	}
	
	/**
	 * Returns the index of a specified child within a NestingShape object. 
	 * If the Shape specified is not actually a child of the NestingShape 
	 * this method returns -1; otherwise the value returned is in the range 
	 * 0 .. shapeCount() - 1. 
	 * @param the shape whose index position within the NestingShape is 
	 * requested . 
	 */ 
	public int indexOf(Shape shape){
		return nestingShapes.indexOf(shape); 
	}
	
	/**
	 * Returns true if the Shape argument is a child of the NestingShape 
	 * object on which this method is called , false otherwise .
	 */
	public boolean contains(Shape shape) {
		for(Shape s : nestingShapes) {
			if (s == shape) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the NestingShape that contains the Shape that method parent 
	 * is called on. If the callee object is not a child within a 
	 * NestingShape instance this method returns null. 
	 */ 
	public NestingShape parent (){
		if (this._parent == null) {
			return null;
		}
		return this._parent;
	}
	
	/**
	 * Returns an ordered list of Shape objects. The first item within 
	 * the list is the root NestingShape of the containment hierarchy. 
	 * The last item within the list is the callee object (hence this 
	 * method always returns a list with at least one item). Any 
	 * intermediate items are NestingShapes that connect the root 
	 * NestingShape to the callee Shape. E.g., given: 
	 * 
	 * NestingShape root = new NestingShape(); 
	 * NestingShape intermediate = new NestingShape(); 
	 * Shape oval = new OvalShape(); 
	 * root.add(intermediate); 
	 * intermediate.add(oval); 
	 * 
	 * a call to oval.path() yields: [root,intermediate,oval] 
	 */
	
	public List <Shape> path (){
		return super.path();
	}
	

}
