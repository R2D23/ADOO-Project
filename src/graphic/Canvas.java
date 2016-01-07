
package graphic;

import core.Action;
import static core.Action.*;
import core.Element;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Importing Static Project Variables
import java.awt.event.MouseAdapter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import core.Notificador;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author douxm_000
 */
public class Canvas {
    public static ArrayList<Element> elements; //The created Elements by the User.
    public static ArrayList<Element> compartidos; //Los elementos ya guardados y que son comunes a todos los colaboradores del archivo
    public static ArrayList<Integer> propios; /* Los elementos que se tienen bloqueados */
    public static JPanel panel; //The Panel representing the Canvas.
    
    public static Element seleccionadoTemporal;
    public static Element seleccionadoCompartidos;
    //private static int indiceZoom;
    public static double zoom = 1;
    public static Lock lockCompartidos;
    public static Dimension origSize;
    
 /* 
    El uso de este método estático es para inicializar el Canvas, su panel y
    otros componentes.
 */
    public static void initializeCanvas(){
	//pastelements = null;
        //futureelements = null;
        seleccionadoTemporal = null;
        seleccionadoCompartidos = null;
        elements = new ArrayList<>();
	compartidos = new ArrayList<>();
        propios = new ArrayList<>();
        lockCompartidos = new ReentrantLock(); 
        origSize = new Dimension();
	panel = new JPanel(){
	    @Override
	    public void paint(Graphics g){
		super.paint(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
                
                lockCompartidos.lock();
                try
                {
                    for(int i = 0; i < compartidos.size(); i++)
                        compartidos.get(i).paint(g);
                }
                finally
                {   lockCompartidos.unlock(); }
                
		for(int i = 0; i < elements.size(); i++)
		    elements.get(i).paint(g);//elements.get(i).draw(g);
                //((Graphics2D)g.create()).scale(zoom, zoom);
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
    

    
    
    //Agrega un elemento a la lista de Elementos temporales sobre el Lienzo.
    public static void addElement(Element e) {
	createAction(Action.CREATE, elements.size(),false);
        Action.undoStack.lastElement().setNext(e);
        elements.add(e);
        repaint();
    }

    public static void deleteElementTemporal(Element e)
    {                    
        elements.remove(e);
    }
    
    public static void deleteElementCompartido(Element e)
    {
        compartidos.remove(e);
    }
    
    public static void doZoom(double escala)
    {
        if(zoom + escala < 0)
            JOptionPane.showMessageDialog(null,"¡No se puede hacer más zoom!");
        else
        {
            zoom += escala;
            int w = (int)(origSize.width*zoom);
            int h = (int)(origSize.height*zoom);
            System.out.println("tamaño : " + origSize);
            panel.setSize(new java.awt.Dimension(w,h));
            panel.setPreferredSize(new java.awt.Dimension(w,h));
            System.out.println("tamaño : " + panel.getSize() + " indice : " + zoom);
            repaint();
        } 
           
        

    }
    
    public static void mouseClicked(MouseEvent e) 
    {
        GUI.closeAllMenus();
        System.out.println(seleccionadoCompartidos);
        if(!GUI.permiso && !GUI.getCursor().equals(java.awt.Cursor.getDefaultCursor()))//si no tiene permiso para editar y trata de hacerlo
        {JOptionPane.showMessageDialog(null, "Usted no tiene permiso para editar"); return;}
        
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            switch(GUI.getCursor().getName())
            {
                case "mano":
                    /* Si no hay ningún elemento seleccionado */
                    if(seleccionadoTemporal == null && seleccionadoCompartidos == null )
                    {
                        int sel = cualFiguraTemporales(e.getPoint());
                        /* Si se selecciono un elemento temporal*/
                        if(sel >= 0) 
                        {
                            seleccionadoTemporal = elements.get(sel);
                            seleccionadoTemporal.setState(Element.BUSY);
                            seleccionadoTemporal.repaint();
                            GUI.showSelectionMenu(e);
                            System.out.println("La figura es temporal");
                        }
                        else
                        {
                            lockCompartidos.lock();
                            /* El try es obligatorio */
                            try 
                            {
                                sel = cualFiguraCompartidos(e.getPoint());
                                /* Si se selecciono un elemento temporal */
                                if(sel >= 0)
                                {
                                    if(!(compartidos.get(sel).getState()== Element.AVAILABLE) && !propios.contains(sel))
                                    {
                                        JOptionPane.showMessageDialog(GUI.frame, "El elemento está bloqueado. \nNo puedes editarlo");
                                        return;
                                    }   
                                    seleccionadoCompartidos = compartidos.get(sel);
                                    seleccionadoCompartidos.setState(Element.BUSY);
                                    seleccionadoCompartidos.repaint();
                                    propios.add(sel);
                                    //seleccionadoCompartidos.setOwner(GUI.dibujante.nomUsuario);
                                    new Thread(new Notificador(compartidos.get(sel))).start();
                                    GUI.showSelectionMenu(e);
                                    System.out.println("La figura es compartida");
                                }
                            } 
                            finally 
                            { lockCompartidos.unlock();}
                            /*{new Thread(new Notificador()).start();}*/
                        }
                       
                        return;
                    }
                    /* Si hay un elemento seleccionado */
                    /* Si el elemento selccionado es temporal */
                    if(seleccionadoTemporal != null)
                    {
                        if(seleccionadoTemporal.getState() == Element.MOVING)
                            Action.undoStack.lastElement().setNext(seleccionadoTemporal.getPos());
                        else if(seleccionadoTemporal.getState() == Element.ROTATING)
                            Action.undoStack.lastElement().setNext(seleccionadoTemporal.getInclination());
                        
                        seleccionadoTemporal.setState(Element.AVAILABLE);
                        seleccionadoTemporal.paint(panel.getGraphics());
                        seleccionadoTemporal = null;
                        return;
                    }
                    
                    /* Si el elemento seleccionado es compartido */
                    lockCompartidos.lock();
                    System.out.println("Desde el desbloqueo1");
                        if(seleccionadoCompartidos != null)
                        {
                            if(seleccionadoCompartidos.getState() == Element.MOVING)
                                Action.undoStack.lastElement().setNext(seleccionadoCompartidos.getPos());
                            else if(seleccionadoCompartidos.getState() == Element.ROTATING)
                                Action.undoStack.lastElement().setNext(seleccionadoCompartidos.getInclination());
                            
                            seleccionadoCompartidos.setState(Element.OWNED);
                            seleccionadoCompartidos.paint(panel.getGraphics());
                            seleccionadoCompartidos = null;
                            System.out.println("Desde el desbloqueo2");
                        }
                    lockCompartidos.unlock();
                       
                break;
                case "lapiz" :
                    /* Se está dibujando Línea o Irregular*/
                    if((seleccionadoTemporal != null) && (seleccionadoTemporal.getState() == Element.GETTINGPOINTS))
                    {
                        seleccionadoTemporal.setLast(new Point(e.getX(), e.getY() + 30));
                        repaint();
                    }
                    else
                        GUI.showFigureMenu(e);
                break;
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            /* Dar clic derecho es para cancelar irregular, línea y desbloquear figura */
            /* si se está dibujando línea o irregular */
            if((seleccionadoTemporal != null) && (seleccionadoTemporal.getState() == Element.GETTINGPOINTS))
            {
                /* Se quita ese elemento*/
                elements.remove(seleccionadoTemporal);
                seleccionadoTemporal = null;
                return;
            }
            /* si se va a desbloquear figura */
            lockCompartidos.lock(); 
            /* se bloquea por que se van a hacer actividades sobre compartidos */
            int ind;
            try 
            {
                ind = cualFiguraCompartidos(e.getPoint());
                /* Si indice < 0 es porque se trata de un elemento compartido */
                if(ind >= 0)
                {    
                    Element aux = compartidos.get(ind);
                    //String owner = compartidos.get(ind).getOwner();
                    //if(owner != null && !owner.equals(GUI.dibujante.nomUsuario))
                    if(!propios.contains(ind))
                        return;
                    aux.setState(Element.AVAILABLE);
                    //aux.setOwner(null);
                    propios.remove(ind);
                    aux.paint(panel.getGraphics());
                    seleccionadoCompartidos = null;
                    new Thread(new Notificador(compartidos.get(ind))).start();
                    
                }
            } 
            finally 
            { lockCompartidos.unlock(); }
            //AQUI SE ENVIA LA NOTIFICACION DE DESBLOQUEO
            /*if(ind >= 0)
                new Thread(new Notificador()).start();*/

        }
    }
    
    public static void mouseMoved(MouseEvent e)
    {
        /* Si hay un temporal seleccionado */
        if(seleccionadoTemporal != null)
        {
            switch(seleccionadoTemporal.getState())
            {
                case Element.MOVING:
                    
                    seleccionadoTemporal.move(e.getX(), e.getY());                    
                break;
                case Element.ROTATING:
                    seleccionadoTemporal.rotate(e.getPoint());
                break;
                case Element.GETTINGPOINTS:
                    Point lastPoint = seleccionadoTemporal.getLast();
                    Graphics g = panel.getGraphics();
                    g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY()+30);
                break;
            }
            repaint();
            return;
        }        
        
        /* Si hay un compartido seleccionado */
        lockCompartidos.lock();
        try
        {
            if(seleccionadoCompartidos != null)
            {
                /* Un elemento compartido nunca puede estar en gettingPoints */
                switch(seleccionadoCompartidos.getState())
                {
                    case Element.MOVING:
                        seleccionadoCompartidos.move(e.getX(), e.getY());                    
                    break;
                    case Element.ROTATING:
                        seleccionadoCompartidos.rotate(e.getPoint());
                    break;
                }
                repaint();
            }        
        }
        finally
        {lockCompartidos.unlock();}
        
    }
    
    public static double getIndiceZoom()
    {return zoom;}
    
    public static void repaint()
    {
	panel.repaint();
        
        //for(Element e : elements)
        //    e.repaint();
        /*lockCompartidos.lock();
        try
        {
            for(Element e : compartidos)
                e.repaint();
        }
        finally
        {lockCompartidos.unlock();}*/
        
        
    }  
    
    public static int cualFiguraTemporales(Point p)
    {
        for(int i = 0; i < elements.size(); i++)
            if(elements.get(i).getArea().contains(p))
                return i;
        return -1;    
    }
    
    public static int cualFiguraCompartidos(Point p)
    {
        for(int i = 0; i < compartidos.size(); i++)
            if(compartidos.get(i).getArea().contains(p))
                return i;
        return -1;    
    }
    
    public static void liberarElementos()
    {
        lockCompartidos.lock();
        try
        {
            for(Element e : compartidos)
                e.setState(Element.AVAILABLE);
        }
        finally
        {lockCompartidos.unlock();}
            
    }
    
    public static void setOrigSize(Dimension origSize)
    {Canvas.origSize = origSize;}
            
}
