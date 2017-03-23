package bounce.views;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;

public class Task1 implements TreeModel {
	ShapeModel model;
	public Task1(ShapeModel model){
		this.model = model;
	}

	@Override
	public Object getRoot() {
		return model.root();
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (model.root().shapeCount() <= index){
			return null;
		}
		if (parent instanceof NestingShape){
			return model.root().shapeAt(index);
		}
				
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		
		if (parent instanceof NestingShape) {
			return ((NestingShape)parent).shapeCount();
		}
		if (model.root().parent() == null) {
			return 0;
		}
		return model.root().parent().shapeCount();
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof NestingShape){
			return false;
		}
		if (node instanceof Shape){
			if (!(((Shape) node).parent() == null)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// deliberately left blank
		
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (!(parent instanceof NestingShape)){
			return -1;
		}
		return ((NestingShape) parent).indexOf((Shape)child); 
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		//model.removeShapeModelListener(1);
		
	}
}
