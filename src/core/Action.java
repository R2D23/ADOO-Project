/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import static core.Canvas.elements;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author LuisArturo
 */
public class Action {
    public static Stack<Action> undoStack = new Stack();
    public static Stack<Action> redoStack = new Stack();
    
    private Object next;
    private Object prev;
    private int id;
    private int type;
    
    public static final int CREATE = 1;
    public static final int DELETE = 2;
    public static final int ELEMENT_MOVE = 3;
    public static final int ELEMENT_ROTATE = 4;
    public static final int FIGURE_BCOLOR = 5;
    public static final int FIGURE_LCOLOR = 6;
    
    public Action(int t, int id, Object o){
	this.next = o;
	type = t;
	prev = new ArrayList();
	
	switch (type){
	    case CREATE:
		break;
	    case DELETE:
		prev = o;
		break;
	    case ELEMENT_MOVE:
		prev = ((Element) elements.get(id)).getPos();
		break;
	    case ELEMENT_ROTATE:
		prev = ((Element) elements.get(id)).getInclination();
		break;
	    case FIGURE_BCOLOR:
		prev = ((Figure) elements.get(id)).getBgColor();
		break;
	    case FIGURE_LCOLOR:
		prev = ((Figure) elements.get(id)).getLgColor();
		break;
	}
    }
    
    public void make(){
	switch (type){
	    case CREATE: 
		elements.add(id, (Element) next);
		break;
	    case DELETE: 
		elements.remove(id);
		break;
	    case ELEMENT_MOVE:
		elements.get(id).move((Point) next);
		break;
	    case ELEMENT_ROTATE:
		elements.get(id).rotate((Float) next);
		break;
	    case FIGURE_BCOLOR:
		((Figure) elements.get(id)).setBgColor((Color) next);
		break;
	    case FIGURE_LCOLOR:
		((Figure) elements.get(id)).setLnColor((Color) next);
	}
    }
    
    public void rollback(){
	switch (type){
	    case CREATE: 
		elements.remove(id);
		break;
	    case DELETE: 
		elements.add(id, (Element)prev);
		break;
	    case ELEMENT_MOVE:
		elements.get(id).move((Point) prev);
		break;
	    case ELEMENT_ROTATE:
		elements.get(id).rotate((Float) prev);
		break;
	    case FIGURE_BCOLOR:
		((Figure) elements.get(id)).setBgColor((Color) prev);
		break;
	    case FIGURE_LCOLOR:
		((Figure) elements.get(id)).setLnColor((Color) prev);
	}
    }
    
    public static void undo(){
	Action act;
	act = undoStack.pop();
	act.rollback();
	redoStack.push(act);
    }
    
    public static void redo(){
	Action act;
	act = redoStack.pop();
	act.make();
	undoStack.push(act);
    }
}
