package bounce.views;

import java.util.ArrayList;


import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;

public class Task2 extends Task1 implements ShapeModelListener{
	private ArrayList<TreeModelListener> listenerList = new ArrayList<TreeModelListener>();
	public Task2(ShapeModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(ShapeModelEvent event) {
		TreeModelEvent treeModelEvent;
		
		switch(event.eventType()){
		
		case ShapeAdded:
			treeModelEvent = new TreeModelEvent((Object)event.source(), event.parent().path().toArray(), new int[]{event.index()} ,new Object[] {event.operand()});
			for (TreeModelListener listener : listenerList){
				listener.treeNodesInserted(treeModelEvent);
			}
			break;			
		case ShapeRemoved:
			treeModelEvent = new TreeModelEvent((Object)event.source(), event.parent().path().toArray(), new int[]{event.index()} ,new Object[] {event.operand()});
			for (TreeModelListener listener : listenerList){
				listener.treeNodesRemoved(treeModelEvent);
			}
			break;
		case ShapeMoved:
			//left blank
		}
		
		
	}
	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		listenerList.add(listener);
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		listenerList.remove(listener);
		
	}

	
}
