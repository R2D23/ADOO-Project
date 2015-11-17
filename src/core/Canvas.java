
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
import static core.GUI.frame;
import static core.GUI.fm;
import static core.GUI.sm;
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
    private static int indiceZoom;
    
 /* 
    El uso de este método estático es para inicializar el Canvas, su panel y
    otros componentes.
 */
    public static void initializeCanvas(){
	pastelements=null;
        futureelements=null;
        seleccionado = null;
        indiceZoom = 100;
        elements = new ArrayList<>();
	
	panel = new JPanel(){
	    @Override
	    public void paint(Graphics g){
		super.paint(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		for(int i = 0; i < elements.size(); i++)
		    elements.get(i).draw(g);
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
    
    public static void doZoom(int escala)
    {
        System.out.println("escala : " + (indiceZoom + escala));
        if(indiceZoom + escala <= 0)
            JOptionPane.showMessageDialog(null, "No se puede hacer mas zoom");
        else
        {
            indiceZoom += escala;
            panel.setSize(  (int)(panel.getWidth()*indiceZoom/100),
			    (int)(panel.getHeight()*indiceZoom/100));
            for(Element e : elements)
               e.doZoom(escala/100);
            panel.repaint();
        }
    }
    
    public static void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            switch(frame.getCursor().getName())//if((this.getCursor().getName().equals("mano")) && (e.getButton() == MouseEvent.BUTTON1))
            {
                case "mano":
                    if(seleccionado == null){
                        int sel = sm.cualFigura(e.getPoint());
                        if(sel >= 0)
                        {   
                            seleccionado = elements.get(sel);
                            seleccionado.state = Element.BUSY;
                            elements.remove(seleccionado);//Esto es para que lo traiga al frente
                            elements.add(seleccionado);
                            sm.setCenter(e.getPoint());
                            sm.obtLocation().setLocation(e.getX() - sm.SIZE/2 + GUI.GAP, e.getY() - sm.SIZE/2 + GUI.GAP);
                            sm.setLocation(sm.obtLocation());
                            repaint();
                            sm.setVisible(!sm.isVisible());
                        }
                    }
                    else{
                        seleccionado.state = Element.AVAILABLE;
                        seleccionado.draw(panel.getGraphics());
                        seleccionado = null;
                    }
                       
                break;
                case "lapiz" :
                    if((seleccionado != null) && (seleccionado.state == Element.GETTINGPOINTS))
                    {
                        System.out.println("desde1");
                        seleccionado.setLast(e.getPoint());
                        repaint();
                    }
                    else
                    {
                        fm.setSize(fm.SIZE, fm.SIZE);
                        fm.obtLocation().setLocation(e.getX() - fm.SIZE/2 + GUI.GAP, e.getY() - fm.SIZE/2 + GUI.GAP);
                        fm.setCenter(new Point(e.getPoint().x,e.getPoint().y));
                        fm.setLocation(fm.obtLocation());
                        fm.setVisible(!fm.isVisible());
                    }
                break;
            }
        }
    }
    
    public static void mouseMoved(MouseEvent e){
        if(seleccionado != null){
            switch(seleccionado.state)
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
    
    
    public static int getIndiceZoom()
    {return indiceZoom;}
    
    public static void repaint(){
	panel.repaint();
    }  
}
