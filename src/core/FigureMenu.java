
package core;
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

import static core.Canvas.seleccionado;

/**
 *
 * @author LuisArturo
 */

/*Esta es la clase que implementa el menú circular de la figuras*/
public class FigureMenu extends JComponent{
    
    public static final int SIZE = 200;
    public Point location;
    public Point center;
    public ArrayList<Area> areas;
    public int areaActual;
    private Canvas canvas;
    private final int TRIANGLE = 0;
    private final int CIRCLE= 1;
    private final int POLYGON = 2;
    private final int RECTANGLE = 3;
    private final int LINE = 4;
    private final int IRREGULAR = 5;
    private final int TEXT = 6;
    private final int EXIT = 7;
    
    public FigureMenu(){
        
        /*Basicamente en este método se define un JComponent y 
        dentro de este se dibuja la figura de dona. El ArrayList
        areas es para definir los "botones" y que estos se coloreen 
        al momento de pasar el cursor del Mouse sobre ellos, además
        de poder detectar el momento en que se da clic sobre alguno
        de ellos.*/
	setSize(SIZE, SIZE);
	setOpaque(false);
        location = new Point();
	center = new Point();
	areas = new ArrayList<>();
        areaActual = -1;
        //this.canvas = ((GUI)getParent()).getCanvas();
        this.setName("FigureMenu");
        this.setVisible(false);
        //this.addMouseListener(new Escucha(areas, Escucha.FIGUREMENU, canvas));
        Area gen = new Area(new Ellipse2D.Double(0, 0, 200, 200));
	gen.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));
        /*Cada una de las areas corresponde a cada boton,
        comenzando el area 0 con el boton del triangulo y continuando
        en orden ascendente en sentido contrario a las manecillas del reloj*/
        //AREA 0 TRIANGLE
            Polygon p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
            p.addPoint(SIZE, SIZE/2);
            p.addPoint(SIZE, 0);
            Area s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
        //AREA 1 CIRCLE
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, 0);
	    p.addPoint(SIZE, 0);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 2 POLYGON
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, 0);
	    p.addPoint(0, 0);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 3 RECTANGLE
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE/2);
	    p.addPoint(0, 0);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 4 LINE
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE/2);
	    p.addPoint(0, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
        
        //AREA 5 IRREGULAR
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, SIZE);
	    p.addPoint(0, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 6 TEXT
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, SIZE);
	    p.addPoint(SIZE, SIZE);
	    //p.addPoint(SIZE, SIZE/2);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);    
            
        //AREA 7 EXIT
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE, SIZE);
	    p.addPoint(SIZE, SIZE/2);
	    //p.addPoint(SIZE, SIZE/2);
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
        
        this.addMouseListener(new MouseAdapter()
        {
           @Override
           public void mouseClicked(MouseEvent me) 
           {
               realizarAccion();
           }
        });
        
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
    
    public void realizarAccion(){
        switch(areaActual)
        {
            case TRIANGLE:
                ConfFrame.create(ConfFrame.TRIANGLE, center);
            break;
            case CIRCLE:
		ConfFrame.create(ConfFrame.CIRCLE, center);
            break;
            case POLYGON:
                ConfFrame.create(ConfFrame.RPOLYGON, center);
            break;
            case RECTANGLE:
                ConfFrame.create(ConfFrame.RECTANGLE, center);
            break;
            case LINE:
                setVisible(false);
                seleccionado = new Line();
                seleccionado.setFirst(center);
                seleccionado.setState(Element.GETTINGPOINTS);
            break;
            case IRREGULAR:
                setVisible(false);
                Irregular fig = new Irregular();
                fig.setFirst(center);
                Canvas.seleccionado = fig;
                fig.setState(Element.GETTINGPOINTS);
            break;
            case TEXT:
                canvas.elements.add(new Text(JOptionPane.showInputDialog("Introduzca el texto")));
                canvas.repaint();
            break;
            case EXIT :
                this.setVisible(false);
                return;
        }
        this.setVisible(false);
    }
    
    /*La funcion paint es llamada cada vez que la ventana se hace visible o 
    cambia de tamaño o se manda llamar a la funcion repaint*/
    @Override
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g.create();
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
                       
        /*La clase menuDrawer es la que se encarga de dibujar todas las areas 
        y agregarles lo iconos correspondientes*/
        MenuDrawer md = new MenuDrawer();
        md.paint(MenuDrawer.TRIANGLE, g, areas.get(TRIANGLE));
        md.paint(MenuDrawer.CIRCLE, g, areas.get(CIRCLE));
        md.paint(MenuDrawer.POLIGONE, g, areas.get(POLYGON));
        md.paint(MenuDrawer.RECTANGLE, g, areas.get(RECTANGLE));
        md.paint(MenuDrawer.LINE, g, areas.get(LINE));
        md.paint(MenuDrawer.IRREGULAR, g, areas.get(IRREGULAR));
        md.paint(MenuDrawer.TEXT, g, areas.get(TEXT));
        md.paint(MenuDrawer.EXIT, g, areas.get(EXIT));
                
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
        double se = 1-si;
        int c[] = new int[10];
        c[0]=(int)(cX*se);
        c[1]=(int)(cY*se);
        c[2]=cX-1-((int)(bX*si))/2;
        c[3]=cY-1-(int)((bY*si))/2;
        c[4]=aX-(int)(cX*se);
        c[5]=aY-(int)(cY*se);
        c[6]=cX+((int)(bX*si))/2;
        c[7]=cY+(int)((bY*si))/2;
        g2.drawLine(0, cY, bX, cY);
        g2.drawLine(aX-bX, cY, aX, cY);
        g2.drawLine(cX, 0, cX, bY);
        g2.drawLine(cX, aY-bY, cX, aY);
        g2.drawLine(c[0],c[1],c[2],c[3]);
        g2.drawLine(c[0], c[4], c[2], c[6]);
        g2.drawLine(c[7], c[3], c[5], c[1]);
        c[8]=(int) (SIZE/6*si) + SIZE/2;
        c[9]= (int) (SIZE/2*si) + SIZE/2;
        g2.drawLine(c[8], c[8],c[9], c[9]);
        g2.setColor(Color.black);
	g2.drawOval(SIZE/3, SIZE/3, SIZE/3, SIZE/3);
	g2.drawOval(0, 0, SIZE, SIZE);
    }

    /*Se agrega un Mouse Listener y se implementan sus métodos, esto para poder saber cuando se 
    ha dado clic sobre el lienzo y por lo tanto, se debe mostrar el menu*/   
    public int whichArea(Point p)
    {
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
                return i;
        return -1;
    }
    
    public void setCenter(Point p){
	center = p;
    }
    
    public Point obtLocation()
    {return this.location;}
    
    public final void setCanvas(Canvas c)
    {canvas = c;}
    
    @Override
    public void setVisible(boolean v)
    {
        areaActual = -1;
        super.setVisible(v);
    }
}

