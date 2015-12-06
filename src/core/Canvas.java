
package core;

import static core.Action.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Importing Static Project Variables
import java.awt.event.MouseAdapter;

/**
 *
 * @author douxm_000
 */
public class Canvas {
    public static ArrayList<Element> elements; //The created Elements by the User.
    public static JPanel panel; //The Panel representing the Canvas.
    public static ArrayList<Element> pastelements;
    public static ArrayList<Element> futureelements;
    public static Element seleccionado;
    public static double zoom = 1;
    
 /* 
    El uso de este método estático es para inicializar el Canvas, su panel y
    otros componentes.
 */
    public static void initializeCanvas(){
	pastelements=null;
        futureelements=null;
        seleccionado = null;
        elements = new ArrayList<>();
	
	panel = new JPanel(){
	    @Override
	    public void paint(Graphics g){
		super.paint(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		for(int i = 0; i < elements.size(); i++)
		    elements.get(i).paint(g);
	    }
	};
	
	MouseAdapter ma = new MouseAdapter(){
	    @Override
	    public void mouseClicked(MouseEvent me){Canvas.mouseClicked(me);}
	    
	    @Override
	    public void mouseMoved(MouseEvent me){Canvas.mouseMoved(me);}
	};
	
	panel.addMouseListener(ma);
	panel.addMouseMotionListener(ma);
    }
    
    public static void actElements(){
        pastelements = cloneList(elements);
    }
     
    public static void returnPast(){
        if(pastelements!=null){
            futureelements=cloneList(elements);
            elements=cloneList(pastelements);
            pastelements=null;
        }else{
            JOptionPane.showMessageDialog(null,"No hay pasos atras");
        }
    }
    
    public static void toFuture(){
        if(futureelements!=null){
            pastelements=cloneList(elements);
            elements=cloneList(futureelements);
            futureelements=null;
        }else{
             JOptionPane.showMessageDialog(null,"No hay pasos hacia adelante");
        }
    }
    
    private static ArrayList<Element> cloneList(ArrayList<Element> origen){
        ArrayList<Element> copia=new ArrayList<Element>();
        for(int i=0;i<origen.size();i++){
            copia.add((Element)origen.get(i).clone());
        }
        return copia;
    }
    
    //Agrega un elemento a la lista de Elementos sobre el Lienzo.
    public static void addElement(Element e) {
	createAction(Action.CREATE, 0, e);
        elements.add(e);
	e.getArea();
    }
    
    //Elimina un elemento siempre y cuando lo contenga
    public static void deleteElement(Element e) {
        if(elements.contains(e))
            deleteElement(elements.indexOf(e));
    }
    
    public static void deleteElement(int i){
	createAction(Action.DELETE, i, null);
	elements.remove(i);
    }
    
    public static void doZoom(double escala){
        if(zoom + escala <= 0)
            JOptionPane.showMessageDialog(null, "¡No se puede hacer más zoom!");
        else{
	    zoom += escala;
            repaint();
        }
    }
    
    public static void mouseClicked(MouseEvent e) {
	GUI.closeAllMenus();
        if(e.getButton() == MouseEvent.BUTTON1){
            switch(GUI.getCursor().getName())//if((this.getCursor().getName().equals("mano")) && (e.getButton() == MouseEvent.BUTTON1))
            {
                case "mano":
                    if(seleccionado == null){
                            GUI.showSelectionMenu(e);
                    }
                    else{
                        seleccionado.setState(Element.AVAILABLE);;
                        seleccionado.paint(panel.getGraphics());
                        seleccionado = null;
                    }
                       
                break;
                case "lapiz" :
                    if((seleccionado != null) && (seleccionado.getState() == Element.GETTINGPOINTS)){
                        System.out.println("desde1");
                        seleccionado.setLast(e.getPoint());
                        repaint();
                    }
		    else {
			GUI.showFigureMenu(e);
                    }
                break;
            }
        }
    }
    
    public static void mouseMoved(MouseEvent e){
        if(seleccionado != null){
            switch(seleccionado.getState())
            {
                case Element.MOVING:
                    seleccionado.move(e.getX(), e.getY());                    
                break;
                case Element.ROTATING:
                    seleccionado.rotate(e.getPoint());
                break;
                case Element.GETTINGPOINTS:
                    Point lastPoint = seleccionado.getLast();
                    Graphics g = panel.getGraphics();
                    g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY()+30);
                    //repaint();
                break;
            }
            repaint();
        }
    }
    
    public static double getIndiceZoom()
    {return zoom;}
    
    public static void repaint(){
	panel.repaint();
    }  
}
