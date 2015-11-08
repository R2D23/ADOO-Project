
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
/**
 *
 * @author LuisArturo
 */

/*Esta es la clase que implementa el menú circular de la figuras*/
public class FigureMenu extends JComponent implements MouseListener{
    
    private static final int SIZE = 200;
    public Point location;
    public Point center;
    public ArrayList<Area> areas;
    public int areaActual;
    private Canvas canvas;
    
    public FigureMenu(Canvas canvas){
        
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
        this.canvas = canvas;
        this.setName("FigureMenu");
        this.setVisible(false);
        this.addMouseListener(new Escucha(areas, Escucha.FIGUREMENU, canvas));
        Area gen = new Area(new Ellipse2D.Double(0, 0, 200, 200));
	gen.subtract(new Area(new Ellipse2D.Double(SIZE/3, SIZE/3, SIZE/3, SIZE/3)));
        /*Cada una de las areas corresponde a cada boton,
        comenzando el area 0 con el boton del triangulo y continuando
        en orden ascendente en sentido contrario a las manecillas del reloj*/
        //AREA 0
            Polygon p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
            p.addPoint(SIZE, SIZE/2);
            p.addPoint(SIZE, 0);
            Area s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
        //AREA 1
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, 0);
	    p.addPoint(SIZE, 0);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 2
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, 0);
	    p.addPoint(0, 0);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 3
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE/2);
	    p.addPoint(0, 0);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 4
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(0, SIZE/2);
	    p.addPoint(0, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
        
        //AREA 5
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, SIZE);
	    p.addPoint(0, SIZE);
            s = ((Area)gen.clone());
            s.intersect(new Area(p));
            areas.add(s);
            
        //AREA 6
            p = new Polygon();
            p.addPoint(SIZE/2, SIZE/2);
	    p.addPoint(SIZE/2, SIZE);
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
        
        System.out.println(canvas);
        this.addMouseListener(new ConfigurarCirculo(areas, canvas, Escucha.FIGUREMENU));
        this.addMouseListener(new ConfigurarCuadrado(areas, canvas, Escucha.FIGUREMENU));
        this.addMouseListener(new ConfigurarTriangulo(areas, canvas, Escucha.FIGUREMENU));
        this.addMouseListener(new ConfigurarPoligono(areas, canvas, Escucha.FIGUREMENU));
        this.addMouseListener(new ConfigurarLinea(areas, canvas, Escucha.FIGUREMENU));
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
                       
        /*La clase menuDrawer es la que se encarga de dibujar todas las areas 
        y agregarles lo iconos correspondientes*/
        MenuDrawer md = new MenuDrawer();
        md.paint(MenuDrawer.TRIANGLE, g, areas.get(0));
        md.paint(MenuDrawer.CIRCLE, g, areas.get(1));
        md.paint(MenuDrawer.POLIGONE, g, areas.get(2));
        md.paint(MenuDrawer.RECTANGLE, g, areas.get(3));
        md.paint(MenuDrawer.LINE, g, areas.get(4));
        md.paint(MenuDrawer.IRREGULAR, g, areas.get(5));
        md.paint(MenuDrawer.EXIT, g, areas.get(6));
                
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
        int c[] = new int[8];
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
        g2.setColor(Color.black);
	g2.drawOval(SIZE/3, SIZE/3, SIZE/3, SIZE/3);
	g2.drawOval(0, 0, SIZE, SIZE);
    }

    /*Se agrega un Mouse Listener y se implementan sus métodos, esto para poder saber cuando se 
    ha dado clic sobre el lienzo y por lo tanto, se debe mostrar el menu*/
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(this.getCursor().getName());
        if(this.getCursor().getName().equals("lapiz"))
        {
            boolean drawIrreg = false;
            if(!canvas.elements.isEmpty()) {
                for(int i=0; i<canvas.elements.size(); i++) {
                    if(canvas.elements.get(i).state==Element.GETTINGPOINTS) {
                        Point nuevoPunto = new Point(e.getPoint().x, e.getPoint().y + 30);
                        System.out.println("Punto nuevo: "+nuevoPunto+" Primero: "+((Irregular)(canvas.elements.get(i))).first);
                        int myX, myY;
                        myX = ((Irregular)(canvas.elements.get(i))).first.x-5;
                        myY = ((Irregular)(canvas.elements.get(i))).first.y-5;
                        if(nuevoPunto.x>myX&&nuevoPunto.x<myX+10&&nuevoPunto.y>myY&&nuevoPunto.y<myY+10) {
                            //Finaliza Edicion
                            canvas.elements.get(i).state = Element.AVAILABLE;
                            canvas.elements.get(i).configure(canvas);
                            //Si se desea hacer un cambio en los colores aqui se realiza
                            ((Irregular)(canvas.elements.get(i))).bgColor = Color.CYAN;
                            ((Irregular)(canvas.elements.get(i))).lnColor = Color.BLUE;
                        } else {
                            //Agrega un vertice más
                            ((Irregular)(canvas.elements.get(i))).newPoint(nuevoPunto);
                        }
                        canvas.repaint();
                        drawIrreg = true;
                    }
                }
            }
            if(!drawIrreg) {
                switch(e.getButton())
                {
                    case MouseEvent.BUTTON3 :
                        this.setVisible(false);
                    break;
                    case MouseEvent.BUTTON1 :
                        System.out.println("Click");
                        this.setSize(SIZE, SIZE);
                        this.setVisible(true);
                        location.setLocation(e.getXOnScreen() - SIZE/2, e.getYOnScreen() - SIZE/2);
                        center.setLocation(e.getPoint().x/2,e.getPoint().y/2);
                        this.setLocation(location);
                        this.repaint();
                    break;
                }
            }
        } else if(this.getCursor().getName().equals("mano")) {
            if(!canvas.elements.isEmpty()) {
                for(int i=0; i<canvas.elements.size(); i++) {
                    int est = canvas.elements.get(i).state;
                    if(est==Element.MOVING||est==Element.ROTATING) {
                       canvas.elements.get(i).state=Element.AVAILABLE;
                    }
                }
                canvas.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //canvas.drawAll();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //canvas.drawAll()
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //canvas.drawAll();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //areaActual = -1;
    }
    
    public int whichArea(Point p)
    {
        for(int i = 0; i < areas.size(); i++)
            if(areas.get(i).contains(p.x, p.y))
                return i;
        return -1;
    }
    

}

