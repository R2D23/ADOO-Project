package graphic;

/**
 *
 * @author Angeles
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;
import core.Element;
import core.Action;
import static graphic.Canvas.elements;
import static graphic.Canvas.compartidos;
import static graphic.Canvas.seleccionadoTemporal;
import static graphic.Canvas.seleccionadoCompartidos;

/*Esta es la clase que implementa el menú circular de seleccion*/
public class SelectionMenu extends JComponent{
    
    public static final int SIZE = 200;
    private Point location;
    private Point center;
    private final ArrayList<Area> areas;
    private int areaActual;    
    Canvas canvas;
    
    public static final int CONFIGURE = 0;
    public static final int MOVE = 1;
    public static final int ROTATE = 2;
    public static final int DELETE = 3;
    public static final int EXIT = 4;
    
    public SelectionMenu(){
        
	setSize(SIZE, SIZE);
	location = new Point();
	center = new Point();
	areas = new ArrayList<>();
        areaActual = -1;
        //this.addMouseListener(new Escucha(areas, Escucha.SELECTIONMENU,canvas));
        Area gen = new Area(new Ellipse2D.Double(0, 0, 200, 200));
	gen.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));
        
        //AREA CONFIGURAR
            Polygon p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
            p.addPoint(SIZE, SIZE/2);
            p.addPoint(SIZE, 0);
            p.addPoint(SIZE/2, 0);            
            Area s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);

        //AREA MOVER
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, 0);
	    p.addPoint(0, 0);
            p.addPoint(0, SIZE/2);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA ROTAR
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0,SIZE/2);
	    p.addPoint(0, SIZE);
            p.addPoint(SIZE/2, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA ELIMINAR
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
            p.addPoint(SIZE/2, SIZE);
            p.addPoint(SIZE, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA CERRAR
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE, SIZE);
	    p.addPoint(SIZE, SIZE/2);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
        
        /*Este MouseMotionListener es el que detecta sobre qué botón está 
            el cursor del mouse y así poder resaltarlo*/
        this.addMouseMotionListener(new MouseAdapter(){
	    @Override
            public void mouseMoved(MouseEvent me)
            {
                int area = whichArea(me.getPoint());
                updateMenu(area);
            }
        });
        
        this.addMouseListener(new MouseAdapter(){
	    @Override
            public void mouseClicked(MouseEvent me)
            {
                if(Canvas.seleccionadoTemporal != null)
                    realizarAccionTemporal(me.getPoint());
                else
                    realizarAccionCompartido(me.getPoint());
            }
        
        });
        

        this.setVisible(false);
    }

    /*Esta funcion es un intermedio entre el MouseMotionListener y la clase actual.
    Dado que desde una clase interior no se pueden modificar atributos de la clase
    exterior, se hizo enta funcion.
    
    El areaActual es el area donde el puntero del mouse esta en ese momento*/
    public void updateMenu(int noArea)
    {
        areaActual = noArea;
        this.repaint();
    }
    
    /*La funcion paint es llamada cada vez que la ventana se hace visible o 
    cambia de tamaño o se manda llamar a la funcion repaint*/
    @Override
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	Area s = new Area(new Ellipse2D.Double(0, 0, 200, 200));
	s.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));
	g2.translate(0, 0);
	g2.setColor(Util.normalColor);
	g2.fill(s);
	
        /*Es este if es donde se resalta el área seleccionada*/
	if(areaActual >= 0){
	    g2.setColor(Color.CYAN);
	    g2.fill(areas.get(areaActual));
	}
        g2.setColor(Color.BLACK);
        
        
        /*Esta parte del codigo es para dibujar las lineas que separan cada uno 
        de los botones*/
        int cX=getWidth()/2;
        int cY=getHeight()/2;
        int bX=getWidth()/3;
        int bY=getHeight()/3;
        int aX=getWidth();
        int aY=getHeight();
        double si = Math.sin(Math.PI/4);
        int c[] = new int[2];
        c[0]=(int) (SIZE/6*si) + SIZE/2;
        c[1]= (int) (SIZE/2*si) + SIZE/2;
        
        g2.drawLine(0, cY, bX, cY);//primer segmento de linea horizontal
        g2.drawLine(aX-bX, cY, aX, cY);//segundo segmento de linea horizontal
        g2.drawLine(cX, 0, cX, bY);//primer segmento de linea vertical
        g2.drawLine(cX, aY-bY, cX, aY);//segundo segmento de linea vertical
        g2.drawLine(c[0], c[0],c[1], c[1]);// linea inclinada
        
        
        g2.setColor(Color.black);
	g2.drawOval(SIZE/3, SIZE/3, SIZE/3, SIZE/3);
	g2.drawOval(0, 0, SIZE, SIZE);
        
        
        /*La clase menuDrawer es la que se encarga de dibujar todas las areas 
        y agregarles lo iconos correspondientes*/
        MenuDrawer md = new MenuDrawer();
        md.paint(MenuDrawer.CONFIGURE, g, areas.get(0));
        md.paint(MenuDrawer.MOVE, g, areas.get(1));
        md.paint(MenuDrawer.ROTATE, g, areas.get(2));
        md.paint(MenuDrawer.DISPOSE, g, areas.get(3));
        md.paint(MenuDrawer.EXIT, g, areas.get(4));
    }

    
    public void realizarAccionTemporal(Point p)
    {
        switch(areaActual)
        {
            case CONFIGURE:
                Action.createAction(Action.TRANSFORM, elements.indexOf(seleccionadoTemporal),false);
                Canvas.seleccionadoTemporal.configure();
                Canvas.seleccionadoTemporal.setState(Element.AVAILABLE);
                Action.undoStack.lastElement().setNext(seleccionadoTemporal);
                Canvas.seleccionadoTemporal = null;
                Canvas.repaint();
            break;
            case MOVE:
                Action.createAction(Action.ELEMENT_MOVE, elements.indexOf(seleccionadoTemporal),false);
                Canvas.seleccionadoTemporal.setState(Element.MOVING);
            break;
            case ROTATE:
                Action.createAction(Action.ELEMENT_ROTATE, elements.indexOf(seleccionadoTemporal),false);
                Canvas.seleccionadoTemporal.setState(Element.ROTATING);
            break;
            case DELETE:
                seleccionadoTemporal.setState(Element.AVAILABLE);
                Action.createAction(Action.DELETE, elements.indexOf(seleccionadoTemporal),false);
                Canvas.deleteElementTemporal(Canvas.seleccionadoTemporal);
                Canvas.seleccionadoTemporal = null;
                Canvas.repaint();
            break;
            case EXIT :
                Canvas.seleccionadoTemporal.setState(Element.AVAILABLE);
                Canvas.seleccionadoTemporal = null;
                Canvas.repaint();
            break;
        }
        this.setVisible(false);
    }
    
    public void realizarAccionCompartido(Point p)
    {
        switch(areaActual)
        {
            case CONFIGURE:
                //ConfFrame.showFrame(p);
                Action.createAction(Action.TRANSFORM, compartidos.indexOf(seleccionadoCompartidos), true);
                Canvas.lockCompartidos.lock();
                seleccionadoCompartidos.configure();
                Action.undoStack.lastElement().setNext(seleccionadoCompartidos);
                Canvas.lockCompartidos.unlock();
            break;
            case MOVE:
                Canvas.lockCompartidos.lock();
                    Action.createAction(Action.ELEMENT_MOVE, compartidos.indexOf(seleccionadoCompartidos),true);
                    seleccionadoCompartidos.setState(Element.MOVING);
                Canvas.lockCompartidos.unlock();
            break;
            case ROTATE:
                Canvas.lockCompartidos.lock();
                    Action.createAction(Action.ELEMENT_ROTATE, compartidos.indexOf(seleccionadoCompartidos),true);
                    seleccionadoCompartidos.setState(Element.ROTATING);
                Canvas.lockCompartidos.unlock();
            break;
            case DELETE:
                Canvas.lockCompartidos.lock();
                Action.createAction(Action.DELETE, compartidos.indexOf(Canvas.seleccionadoCompartidos) ,true);
                Canvas.deleteElementCompartido(seleccionadoCompartidos);
                seleccionadoCompartidos = null;
                Canvas.lockCompartidos.unlock();
                Canvas.repaint();
            break;
            case EXIT :
                Canvas.seleccionadoTemporal = null;
                Canvas.repaint();
            break;
        }
        this.setVisible(false);
    }
    
    public int whichArea(Point p)
    {
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
                return i;
        return -1;
    }
    
    
    public void setCenter(Point p)
    {this.center = p;}
    
    public Point obtLocation()
    {return this.location;}
    
    public final void setCanvas(Canvas c)
    {canvas = c;}
    
    public void setVisible(boolean v)
    {
        areaActual = -1;
        super.setVisible(v);
    }
}

