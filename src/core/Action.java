package core;
import static graphic.Canvas.elements;
import static graphic.Canvas.compartidos;
import graphic.GUI;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

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
    private boolean isShared;
    
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
    
    private Action(int t, int id, boolean b){
	type = t;
        isShared = b;
        this.id = id;
	//prev = new ArrayList();
	ArrayList lista;
        if(!isShared)
            lista = elements;
        else
            lista = compartidos;
        
	switch (type){
//	    Crear no requiere un identificador, pero si que se le pase la
//	    dirección del objeto creado por medio de 'o'
	    case CREATE:
		break;
//	    Borrar requiere que se le pase el identificador del arreglo del
//	    objeto que será eliminado.
	    case DELETE:
		prev = lista.get(id);
		break;
//	    Mover un elemento requiere el identificador de este, más un
//	    objeto Point que servirá de referencia a las nuevas coordenadas.
	    case ELEMENT_MOVE:
		prev = ((Element) lista.get(id)).getPos();
		break;
	    case ELEMENT_ROTATE:
		prev = ((Element) lista.get(id)).getInclination();
		break;
	    case FIGURE_BCOLOR:
		prev = ((Figure) lista.get(id)).getBgColor();
		break;
	    case FIGURE_LCOLOR:
		prev = ((Figure) lista.get(id)).getLnColor();
		break;
	    case CIRCLE_RADIUS:
		prev = ((Circle) lista.get(id)).getRadius();
		break;
	    case TRIANGLE_TYPE:
		prev = ((Triangle) lista.get(id)).getType();
		break;
	    case SQUARE:
		prev = ((Rectangle) lista.get(id)).getSize();
		break;
	    case NO_SIDES:
		prev = ((RegularPolygon) lista.get(id)).getNumSides();
		break;
	    case SIDE_SIZE:
		prev = ((RegularPolygon) lista.get(id)).getLongSide();
		break;
	    case TRANSFORM:
		prev = ((Element) lista.get(id)).clone();
                ((Element) prev).setState(Element.AVAILABLE);
            break;
	}
    }
    
    public static void createAction(int t, int id,boolean b){
	undoStack.add(new Action(t, id, b));
	redoStack.clear();
    }
    
    public void make(){
        ArrayList<Element> lista;
        
        if(!isShared)
            lista = elements;
        else
            lista = compartidos;
        
	switch (type){
	    case CREATE: 
		lista.add((Element) next);
		break;
	    case DELETE: 
		lista.remove((Element)prev);
		break;
	    case ELEMENT_MOVE:
		lista.get(id).move((Point) next);
		break;
	    case ELEMENT_ROTATE:
		lista.get(id).setIncline((Double) next);
		break;
	    case FIGURE_BCOLOR:
		((Figure) lista.get(id)).setBgColor((Color) next);
		break;
	    case FIGURE_LCOLOR:
		((Figure) lista.get(id)).setLnColor((Color) next);
		break;
	    case CIRCLE_RADIUS:
		((Circle) lista.get(id)).setRadius((Double) next);
		break;
	    case TRIANGLE_TYPE:
		((Triangle) lista.get(id)).setType((Integer) next);
		break;
	    case SQUARE:
		((Rectangle) lista.get(id)).setSize((Point) next);
		break;
	    case NO_SIDES:
		((RegularPolygon) lista.get(id)).setNumSides((Integer) next);
		break;
	    case SIDE_SIZE:
		((RegularPolygon) lista.get(id)).setLongSide((Integer) next);
		break;
            case TRANSFORM :
                lista.set(id, (Element)next);
                break;
	}
    }
    
    public void rollback(){
        
        ArrayList<Element> lista;
        if(!isShared)
            lista = elements;
        else
            lista = compartidos;
        
	switch (type){
	    case CREATE: 
		lista.remove((Element)next);
		break;
	    case DELETE: 
		lista.add(id, (Element) prev);
		break;
	    case ELEMENT_MOVE:
		lista.get(id).move((Point) prev);
		break;
	    case ELEMENT_ROTATE:
		lista.get(id).setIncline((Double) prev);
		break;
	    case FIGURE_BCOLOR:
		((Figure) lista.get(id)).setBgColor((Color) prev);
		break;
	    case FIGURE_LCOLOR:
		((Figure) lista.get(id)).setLnColor((Color) prev);
		break;
	    case CIRCLE_RADIUS:
		((Circle) lista.get(id)).setRadius((Double) prev);
		break;
	    case TRIANGLE_TYPE:
		((Triangle) lista.get(id)).setType((Integer) prev);
		break;
	    case SQUARE:
		((Rectangle) lista.get(id)).setSize((Point) prev);
		break;
	    case NO_SIDES:
		((RegularPolygon) lista.get(id)).setNumSides((Integer) prev);
		break;
	    case SIDE_SIZE:
		((RegularPolygon) lista.get(id)).setLongSide((Integer) prev);
                break;
            case TRANSFORM :
                lista.set(id, (Element)prev);
                break;
            
	}
    }
    
    public static void undo(){
	try
        {            
            Action act;
            act = undoStack.pop();
            act.rollback();
            redoStack.add(act);
        }
        catch(Exception e)
        {JOptionPane.showMessageDialog(GUI.frame, "Aún no ha realizado una acción", "No se puede deshacer", JOptionPane.INFORMATION_MESSAGE);}
    }
    
    public static void redo(){
        try
        {
            Action act;
            act = redoStack.pop();
            act.make();
            undoStack.add(act);
        }catch(Exception e){}
    }
    
    public void setNext(Object o)
    {next = o;}
}