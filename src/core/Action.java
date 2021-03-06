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
    public static final int CIRCLE_RADIUS = 7;
    public static final int TRIANGLE_TYPE = 8;
    public static final int SQUARE = 9;
    public static final int NO_SIDES = 10;
    public static final int SIDE_SIZE = 11;
    public static final int TRANSFORM = 12;
    
//    La clase Action está diseñada para grabar las acciones del usuario
//    para volver a realizarlas 'make()' o para deshacerlas 'rollback'
//    requiere que le sea indicado que tipo de acción será, el identificador del
//    objeto modificado y un objeto que tenga los valores necesarios para repetir
//    una operación. Por ejemplo, digamos que queresmo volver un objeto a su 
//    lugar original, entonces, 'o' debe tener almacenado un objeto Point
    
    private Action(int t, int id, Object o){
	this.next = o;
	type = t;
	prev = new ArrayList();
	
	switch (type){
//	    Crear no requiere un identificador, pero si que se le pase la
//	    dirección del objeto creado por medio de 'o'
	    case CREATE:
		break;
//	    Borrar requiere que se le pase el identificador del arreglo del
//	    objeto que será eliminado.
	    case DELETE:
		prev = elements.get(id);
		break;
//	    Mover un elemento requiere el identificador de este, más un
//	    objeto Point que servirá de referencia a las nuevas coordenadas.
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
		prev = ((Figure) elements.get(id)).getLnColor();
		break;
	    case CIRCLE_RADIUS:
		prev = ((Circle) elements.get(id)).getRadius();
		break;
	    case TRIANGLE_TYPE:
		prev = ((Triangle) elements.get(id)).getType();
		break;
	    case SQUARE:
		prev = ((Rectangle) elements.get(id)).getSize();
		break;
	    case NO_SIDES:
		prev = ((RegularPolygon) elements.get(id)).getNumSides();
		break;
	    case SIDE_SIZE:
		prev = ((RegularPolygon) elements.get(id)).getLongSide();
		break;
	    case TRANSFORM:
		prev = ((Element) elements.get(id)).clone();
	}
    }
    
    public static void createAction(int t, int id, Object o){
	undoStack.add(new Action(t, id, o));
	redoStack.clear();
    }
    
    public void make(){
	switch (type){
	    case CREATE: 
		elements.add((Element) next);
		break;
	    case DELETE: 
		elements.remove(prev);
		break;
	    case ELEMENT_MOVE:
		elements.get(id).move((Point) next);
		break;
	    case ELEMENT_ROTATE:
		elements.get(id).setIncline((Double) next);
		break;
	    case FIGURE_BCOLOR:
		((Figure) elements.get(id)).setBgColor((Color) next);
		break;
	    case FIGURE_LCOLOR:
		((Figure) elements.get(id)).setLnColor((Color) next);
		break;
	    case CIRCLE_RADIUS:
		((Circle) elements.get(id)).setRadius((Double) next);
		break;
	    case TRIANGLE_TYPE:
		((Triangle) elements.get(id)).setType((Integer) next);
		break;
	    case SQUARE:
		((Rectangle) elements.get(id)).setSize((Point) next);
		break;
	    case NO_SIDES:
		((RegularPolygon) elements.get(id)).setNumSides((Integer) next);
		break;
	    case SIDE_SIZE:
		((RegularPolygon) elements.get(id)).setLongSide((Integer) next);
		break;
	}
    }
    
    public void rollback(){
	switch (type){
	    case CREATE: 
		elements.remove(next);
		break;
	    case DELETE: 
		elements.add(id, (Element) prev);
		break;
	    case ELEMENT_MOVE:
		elements.get(id).move((Point) prev);
		break;
	    case ELEMENT_ROTATE:
		elements.get(id).setIncline((Double) prev);
		break;
	    case FIGURE_BCOLOR:
		((Figure) elements.get(id)).setBgColor((Color) prev);
		break;
	    case FIGURE_LCOLOR:
		((Figure) elements.get(id)).setLnColor((Color) prev);
		break;
	    case CIRCLE_RADIUS:
		((Circle) elements.get(id)).setRadius((Double) prev);
		break;
	    case TRIANGLE_TYPE:
		((Triangle) elements.get(id)).setType((Integer) prev);
		break;
	    case SQUARE:
		((Rectangle) elements.get(id)).setSize((Point) prev);
		break;
	    case NO_SIDES:
		((RegularPolygon) elements.get(id)).setNumSides((Integer) prev);
		break;
	    case SIDE_SIZE:
		((RegularPolygon) elements.get(id)).setLongSide((Integer) prev);
		break;
	}
    }
    
    public static void undo(){
	Action act;
	act = undoStack.pop();
	act.rollback();
	redoStack.add(act);
    }
    
    public static void redo(){
	Action act;
	act = redoStack.pop();
	act.make();
	undoStack.add(act);
    }
}
